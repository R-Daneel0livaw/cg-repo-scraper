package com.olivaw.codegraph.scraper.service;

import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.util.List;

public interface VersionControlService {
    void fetchLatestFiles(VersionControlRequest request) throws Exception;

    void fetchFullHistory(VersionControlRequest request) throws Exception;

    List<String> fetchHistoryBetweenDates(VersionControlRequest request) throws Exception;

    String fetchDiff(VersionControlRequest request) throws Exception;
}
