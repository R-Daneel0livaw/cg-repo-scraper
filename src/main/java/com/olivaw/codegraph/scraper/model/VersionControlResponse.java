package com.olivaw.codegraph.scraper.model;

public class VersionControlResponse<T> {

    private String message;
    private T data;
    private VersionControlData versionControlData;

    public VersionControlResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public VersionControlResponse(String message) {
        this.message = message;
        this.versionControlData = new VersionControlData();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public VersionControlData getVersionControlData() {
        return versionControlData;
    }

    public void setVersionControlData(VersionControlData versionControlData) {
        this.versionControlData = versionControlData;
    }
}
