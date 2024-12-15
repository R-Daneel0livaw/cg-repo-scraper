package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.model.GitActionResult;
import com.olivaw.codegraph.scraper.model.VersionControlRequest;
import com.olivaw.codegraph.scraper.utils.GitUtils;

import java.io.File;
import java.util.List;

public class GitHubService implements VersionControlService {
    @Override
    public void fetchLatestFiles(VersionControlRequest request) {
        var config = getGitActionConfig(request, 1);
        GitUtils.performGitAction(config);
    }

    @Override
    public void fetchFullHistory(VersionControlRequest request) {
        var config = getGitActionConfig(request);
        GitUtils.performGitAction(config);
    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) {
        var config = getGitActionConfig(request);
        GitAction<List<File>> action = new FetchHistoryBetweenDatesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) {
        var config = getGitActionConfig(request, 1);
        GitAction<List<File>> action = new FetchDiffFilesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        return null;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request) {
        var config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        return config;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request, int depth) {
        var config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        config.setDepth(depth);
        return config;
    }
}
