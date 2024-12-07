package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.model.VersionControlRequest;
import com.olivaw.codegraph.scraper.utils.GitUtils;

import java.io.File;
import java.util.List;

public class GitHubService implements VersionControlService {
    @Override
    public void fetchLatestFiles(VersionControlRequest request) {
        GitActionConfig config = getGitActionConfig(request, 1);
        GitUtils.performGitAction(config);
    }

    @Override
    public void fetchFullHistory(VersionControlRequest request) {
        GitActionConfig config = getGitActionConfig(request);
        GitUtils.performGitAction(config);
    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) {
        GitActionConfig config = getGitActionConfig(request, 1);
        GitUtils.performGitAction(config, git -> {});
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) {
        GitActionConfig config = getGitActionConfig(request, 1);
        GitUtils.performGitAction(config, git -> {});
        return null;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request) {
        GitActionConfig config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        return config;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request, int depth) {
        GitActionConfig config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        config.setDepth(depth);
        return config;
    }
}
