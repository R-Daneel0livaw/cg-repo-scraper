package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FetchHistoryBetweenDatesAction implements GitAction<List<File>> {

    private final Date startDate;
    private final Date endDate;

    public FetchHistoryBetweenDatesAction(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public GitActionResult<List<File>> execute(Git git) throws GitAPIException {
        List<File> changedFiles = new ArrayList<>();

        Repository repository = git.getRepository();
        Iterable<RevCommit> commits;
        try {
            commits = git.log().all().call();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RevCommit previousCommit = null;
        for (RevCommit commit : commits) {
            Date commitDate = new Date(commit.getCommitTime() * 1000L);
            if ((startDate != null && commitDate.before(startDate)) ||
                    (endDate != null && commitDate.after(endDate))) {
                continue;
            }

            if (previousCommit != null) {
                try (DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE)) {
                    diffFormatter.setRepository(repository);
                    diffFormatter.setDetectRenames(true);

                    List<DiffEntry> diffs = diffFormatter.scan(previousCommit.getTree(), commit.getTree());
                    for (DiffEntry diff : diffs) {
                        String filePath = diff.getNewPath();
                        changedFiles.add(new File(filePath));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            previousCommit = commit;
        }
        return new GitActionResult<>(changedFiles);
    }
}
