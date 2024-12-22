package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.util.List;

public class LocalFileSystemService implements VersionControlService {
    @Override
    public void fetchLatestFiles(VersionControlRequest request) throws Exception {

    }

    @Override
    public void fetchFullHistory(VersionControlRequest request) throws Exception {

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
