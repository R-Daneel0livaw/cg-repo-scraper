package com.olivaw.codegraph.scraper.model.request;

public class VersionControlDestination {

    private ResultDestinationType destinationType;

    private boolean maintainDirectoryStructure;
    private String localPath;
    private String s3BucketName;
    private String s3Key;

    public boolean isMaintainDirectoryStructure() {
        return maintainDirectoryStructure;
    }

    public void setMaintainDirectoryStructure(boolean maintainDirectoryStructure) {
        this.maintainDirectoryStructure = maintainDirectoryStructure;
    }

    public ResultDestinationType getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(ResultDestinationType destinationType) {
        this.destinationType = destinationType;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
}
