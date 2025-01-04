package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.io.File;
import java.util.List;

public interface VersionControlService {
    VersionControlResponse<List<File>> fetchLatestFiles(VersionControlRequest request) throws Exception;

    VersionControlResponse<List<File>> fetchFullHistory(VersionControlRequest request) throws Exception;

    List<String> fetchHistoryBetweenDates(VersionControlRequest request) throws Exception;

    String fetchDiff(VersionControlRequest request) throws Exception;
}
