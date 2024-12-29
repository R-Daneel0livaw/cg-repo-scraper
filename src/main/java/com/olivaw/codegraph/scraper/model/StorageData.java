package com.olivaw.codegraph.scraper.model;

import java.io.File;
import java.util.List;

public class StorageData {

    private String targetPath;
    private List<File> files;

    public StorageData() {
    }

    public StorageData(String targetPath, List<File> files) {
        this.targetPath = targetPath;
        this.files = files;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
