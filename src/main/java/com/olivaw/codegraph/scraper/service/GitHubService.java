package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.VersionControlRequest;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;


import java.util.List;

public class GitHubService implements VersionControlService {
    @Override
    public void fetchLatestFiles(VersionControlRequest request) throws Exception {
        try (Git git = Git.cloneRepository()
                .setURI(request.getRepoLocation())
                .setDirectory(null)
                .setDepth(1)
                .call()) {
        } catch (GitAPIException e) {

        }
    }

    @Override
    public void fetchFullHistory(VersionControlRequest request) throws Exception {

    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) throws Exception {
        return null;
    }
}
