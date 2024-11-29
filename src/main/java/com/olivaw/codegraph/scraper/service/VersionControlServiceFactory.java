package com.olivaw.codegraph.scraper.service;

public class VersionControlServiceFactory {

    public static VersionControlService getService(String url) {
        if (url.contains("github.com")) {
            return new GitHubService();
        } else if (url.contains("gitlab.com")) {
            return new GitLabService();
        } else if (url.startsWith("file://")) {
            return new LocalFileSystemService();
        } else {
            throw new IllegalArgumentException("Unsupported version control system");
        }
    }
}
