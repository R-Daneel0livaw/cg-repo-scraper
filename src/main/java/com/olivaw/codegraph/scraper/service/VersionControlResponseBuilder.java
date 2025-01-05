package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.VersionControlData;
import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class VersionControlResponseBuilder {

    public static VersionControlResponse build(String message, String location, List<File> files) {
        VersionControlData versionControlData = new VersionControlData.Builder()
                .setLocation(location)
                .setFiles(files)
                .build();
        return new VersionControlResponse(message, versionControlData);
    }
}
