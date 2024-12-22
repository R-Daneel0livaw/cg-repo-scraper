package com.olivaw.codegraph.scraper.model.request;

public class VersionControlRequest {

    private VersionControlRepoIdentification versionControlRepoIdentification;

    private VersionControlDestination versionControlDestination;

    public VersionControlRepoIdentification getVersionControlRepoIdentification() {
        return versionControlRepoIdentification;
    }

    public void setVersionControlRepoIdentification(VersionControlRepoIdentification versionControlRepoIdentification) {
        this.versionControlRepoIdentification = versionControlRepoIdentification;
    }

    public VersionControlDestination getVersionControlDestination() {
        return versionControlDestination;
    }

    public void setVersionControlDestination(VersionControlDestination versionControlDestination) {
        this.versionControlDestination = versionControlDestination;
    }
}
