package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.CorruptObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchAllFilesAction implements GitAction<List<File>> {

    private final String commitId;

    public FetchAllFilesAction(String commitId) {
        this.commitId = commitId;
    }

    @Override
    public GitActionResult<List<File>> execute(Git git) throws GitAPIException {
        List<File> files = new ArrayList<>();
        Repository repository = git.getRepository();

        ObjectId resolvedCommitId;
        try {
            resolvedCommitId = commitId != null
                    ? repository.resolve(commitId)
                    : repository.resolve("HEAD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (resolvedCommitId == null) {
            throw new IllegalArgumentException("Invalid commit ID or branch reference.");
        }

        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit commit = revWalk.parseCommit(resolvedCommitId);

            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(commit.getTree());
                treeWalk.setRecursive(true);

                while (treeWalk.next()) {
                    String filePath = treeWalk.getPathString();
                    files.add(new File(filePath));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new GitActionResult<>(files);
    }
}
