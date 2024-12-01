package com.olivaw.codegraph.scraper.model;

import java.io.File;

public class GitActionConfig {

    private String repoLocation;
    private File targetDirectory;
    private int depth;

    public String getRepoLocation() {
        return repoLocation;
    }

    public void setRepoLocation(String repoLocation) {
        this.repoLocation = repoLocation;
    }

    public File getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(File targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
