package com.olivaw.codegraph.scraper.model;

import java.io.File;
import java.util.List;

public class StorageData<T> {

    private String targetPath;
    private List<File> files;

    private T data;


    public StorageData() {
    }

    public StorageData(String targetPath, T data) {
        this.targetPath = targetPath;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
