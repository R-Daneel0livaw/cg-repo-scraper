package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.io.File;
import java.util.List;

public class LocalFileSystemService implements VersionControlService {
    @Override
    public VersionControlResponse fetchLatestFiles(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public VersionControlResponse fetchFullHistory(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public VersionControlResponse fetchHistoryBetweenDates(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public VersionControlResponse fetchDiff(VersionControlRequest request) throws Exception {
        return null;
    }
}
