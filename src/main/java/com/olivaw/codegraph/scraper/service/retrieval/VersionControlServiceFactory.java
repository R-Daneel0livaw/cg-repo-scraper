package com.olivaw.codegraph.scraper.service.retrieval;

import org.springframework.beans.factory.annotation.Autowired;

public class VersionControlServiceFactory {


    private final GitHubService gitHubService;
    private final LocalFileSystemService localFileSystemService;

    @Autowired
    public VersionControlServiceFactory(GitHubService gitHubService, LocalFileSystemService localFileSystemService) {
        this.gitHubService = gitHubService;
        this.localFileSystemService = localFileSystemService;
    }

    public VersionControlService getService(String url) {
        if (url.contains("github.com")) {
            return gitHubService;
        } else if (url.startsWith("file://")) {
            return localFileSystemService;
        } else {
            throw new IllegalArgumentException("Unsupported version control system");
        }
    }
}
