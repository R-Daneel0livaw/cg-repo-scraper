package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.GitActionConfig;
import com.olivaw.codegraph.scraper.model.GitActionResult;
import com.olivaw.codegraph.scraper.model.StorageData;
import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.storage.StorageService;
import com.olivaw.codegraph.scraper.service.storage.StorageServiceFactory;
import com.olivaw.codegraph.scraper.utils.GitUtils;

import java.io.File;
import java.util.List;

public class GitHubService implements VersionControlService {
    @Override
    public VersionControlResponse<List<File>> fetchLatestFiles(VersionControlRequest request) {
        var config = getGitActionConfig(request, 1);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config);
        var storageService = getStorageService(request);
        var storageResult = storageService.store(new StorageData(request.getVersionControlDestination().getLocalPath(),
                result.getData()));
        return new VersionControlResponse<>(storageResult.getMessage(), storageResult.getFiles());
    }

    @Override
    public  VersionControlResponse<List<File>>  fetchFullHistory(VersionControlRequest request) {
        var config = getGitActionConfig(request);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config);
        var storageService = getStorageService(request);
        var storageResult = storageService.store((new StorageData(request.getVersionControlDestination().getLocalPath(),
                result.getData())));
        return new VersionControlResponse<>(storageResult.getMessage(), storageResult.getFiles());
    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) {
        var config = getGitActionConfig(request);
        GitAction<List<File>> action = new FetchHistoryBetweenDatesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        var storageService = getStorageService(request);
        storageService.store((new StorageData(request.getVersionControlDestination().getLocalPath(), result.getData())));
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) {
        var config = getGitActionConfig(request, 1);
        GitAction<List<File>> action = new FetchDiffFilesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        var storageService = getStorageService(request);
        storageService.store((new StorageData(request.getVersionControlDestination().getLocalPath(), result.getData())));
        return null;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request) {
        var config = new GitActionConfig();
        config.setRepoLocation(request.getVersionControlRepoIdentification().getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        return config;
    }

    private GitActionConfig getGitActionConfig(VersionControlRequest request, int depth) {
        var config = new GitActionConfig();
        config.setRepoLocation(request.getVersionControlRepoIdentification().getRepoLocation());
        config.setTargetDirectory(new File("/tmp/temp-repo"));
        config.setDepth(depth);
        return config;
    }

    private StorageService getStorageService(VersionControlRequest request) {
        return StorageServiceFactory.getService(request.getVersionControlDestination().getDestinationType());
    }
}
