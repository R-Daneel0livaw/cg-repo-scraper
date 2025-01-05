package com.olivaw.codegraph.scraper.model;

public class VersionControlResponse {

    private String message;
    private VersionControlData versionControlData;

    public VersionControlResponse(String message) {
        this.message = message;
        this.versionControlData = new VersionControlData();
    }

    public VersionControlResponse(String message, VersionControlData versionControlData) {
        this.message = message;
        this.versionControlData = versionControlData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VersionControlData getVersionControlData() {
        return versionControlData;
    }

    public void setVersionControlData(VersionControlData versionControlData) {
        this.versionControlData = versionControlData;
    }
}
