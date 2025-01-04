package com.olivaw.codegraph.scraper.model;

import java.io.File;
import java.util.List;

public class VersionControlData {

    private String location;
    private List<File> files;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
