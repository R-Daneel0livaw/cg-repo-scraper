package com.olivaw.codegraph.scraper.model;

import java.io.File;
import java.util.List;

public class VersionControlData {
    private List<File> files;
    private String location;

    private VersionControlData(Builder builder) {
        this.files = builder.files;
        this.location = builder.location;
    }

    public static class Builder {
        private List<File> files;
        private String location;

        public Builder setFiles(List<File> files) {
            this.files = files;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public VersionControlData build() {
            return new VersionControlData(this);
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public String getLocation() {
        return location;
    }
}