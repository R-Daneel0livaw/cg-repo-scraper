package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.exception.GitActionException;
import com.olivaw.codegraph.scraper.model.GitActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchDiffFilesAction implements GitAction<List<File>> {

    private final String startCommitId;
    private final String endCommitId;

    public FetchDiffFilesAction(String startCommitId, String endCommitId) {
        this.startCommitId = startCommitId;
        this.endCommitId = endCommitId;
    }

    @Override
    public GitActionResult<List<File>> execute(Git git) throws GitActionException {
        List<File> changedFiles = new ArrayList<>();
        Repository repository = git.getRepository();

        ObjectId startCommitObj;
        ObjectId endCommitObj;
        try {
            startCommitObj = repository.resolve(startCommitId);
            endCommitObj = repository.resolve(endCommitId);
        } catch (IOException e) {
            throw new GitActionException("Error fetching start or end commit from repository.", e);
        }

        if (startCommitObj == null || endCommitObj == null) {
            throw new GitActionException("Invalid commit IDs provided.");
        }

        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit startCommit = revWalk.parseCommit(startCommitObj);
            RevCommit endCommit = revWalk.parseCommit(endCommitObj);

            try (DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE)) {
                diffFormatter.setRepository(repository);
                diffFormatter.setDetectRenames(true);

                List<DiffEntry> diffs = diffFormatter.scan(startCommit.getTree(), endCommit.getTree());
                for (DiffEntry diff : diffs) {
                    String filePath = diff.getNewPath();
                    if (!filePath.equals(DiffEntry.DEV_NULL)) {
                        changedFiles.add(new File(filePath));
                    }
                }
            }
        } catch (IOException e) {
            throw new GitActionException("Error fetching diffs in repository commit history.", e);
        }
        return new GitActionResult<>(changedFiles);
    }
}
