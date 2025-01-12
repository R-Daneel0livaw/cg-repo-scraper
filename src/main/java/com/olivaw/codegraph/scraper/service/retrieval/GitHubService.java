package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.*;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.VersionControlResponseBuilder;
import com.olivaw.codegraph.scraper.service.storage.StorageService;
import com.olivaw.codegraph.scraper.service.storage.StorageServiceFactory;
import com.olivaw.codegraph.scraper.utils.GitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Qualifier("gitHubService")
public class GitHubService implements VersionControlService {

    private final StorageServiceFactory storageServiceFactory;

    @Autowired
    public GitHubService(StorageServiceFactory storageServiceFactory) {
        this.storageServiceFactory = storageServiceFactory;
    }

    @Override
    public VersionControlResponse fetchLatestFiles(VersionControlRequest request) {
        return fetchResultAndStore(request, 1);
    }

    @Override
    public  VersionControlResponse  fetchFullHistory(VersionControlRequest request) {
        return fetchResultAndStore(request, 0);
    }

    @Override
    public VersionControlResponse fetchHistoryBetweenDates(VersionControlRequest request) {
        var config = getGitActionConfig(request);
        GitAction<List<File>> action = new FetchHistoryBetweenDatesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        var storageResult = storeData(request, result);
        return getVersionControlResponse(storageResult);
    }

    @Override
    public VersionControlResponse fetchDiff(VersionControlRequest request) {
        var config = getGitActionConfig(request, 1);
        GitAction<List<File>> action = new FetchDiffFilesAction(null, null);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config, action);
        var storageResult = storeData(request, result);
        return getVersionControlResponse(storageResult);
    }

    private VersionControlResponse fetchResultAndStore(VersionControlRequest request, int depth) {
        var config = getGitActionConfig(request, depth);
        GitActionResult<List<File>> result = GitUtils.performGitAction(config);
        var storageResult = storeData(request, result);
        return getVersionControlResponse(storageResult);
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

    private <T> StorageResult storeData(VersionControlRequest versionControlRequest, GitActionResult<T> gitActionResult) {
        StorageService<T> storageService = storageServiceFactory.getService(versionControlRequest.getVersionControlDestination().getDestinationType());
        StorageData<T> storageData = new StorageData<>(
                versionControlRequest.getVersionControlDestination().getLocalPath(),
                gitActionResult.getData()
        );
        return storageService.store(storageData);
    }

    private VersionControlResponse getVersionControlResponse(StorageResult storageResult) {
        return VersionControlResponseBuilder.build(storageResult.getMessage(), storageResult.getLocation(), storageResult.getFiles());
    }
}
