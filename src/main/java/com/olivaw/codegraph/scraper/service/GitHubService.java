package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.model.VersionControlRequest;
import com.olivaw.codegraph.scraper.utils.GitUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;


import java.util.List;

public class GitHubService implements VersionControlService {
    @Override
    public void fetchLatestFiles(VersionControlRequest request) {
        GitActionConfig config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(null);
        config.setDepth(1);

        GitUtils.performGitAction(config);
    }

    @Override
    public void fetchFullHistory(VersionControlRequest request) {
        GitActionConfig config = new GitActionConfig();
        config.setRepoLocation(request.getRepoLocation());
        config.setTargetDirectory(null);

        GitUtils.performGitAction(config);
    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) throws Exception {
        try (Git git = Git.cloneRepository()
                .setURI(request.getRepoLocation())
                .setDirectory(null)
                .setDepth(1)
                .call()) {
        } catch (GitAPIException e) {

        }
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) throws Exception {
        try (Git git = Git.cloneRepository()
                .setURI(request.getRepoLocation())
                .setDirectory(null)
                .setDepth(1)
                .call()) {
        } catch (GitAPIException e) {

        }
        return null;
    }
}
