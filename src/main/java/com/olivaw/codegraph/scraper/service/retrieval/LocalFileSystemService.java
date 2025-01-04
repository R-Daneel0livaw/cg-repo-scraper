package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.io.File;
import java.util.List;

public class LocalFileSystemService implements VersionControlService {
    @Override
    public VersionControlResponse<List<File>> fetchLatestFiles(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public VersionControlResponse<List<File>> fetchFullHistory(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public List<String> fetchHistoryBetweenDates(VersionControlRequest request) throws Exception {
        return null;
    }

    @Override
    public String fetchDiff(VersionControlRequest request) throws Exception {
        return null;
    }
}
