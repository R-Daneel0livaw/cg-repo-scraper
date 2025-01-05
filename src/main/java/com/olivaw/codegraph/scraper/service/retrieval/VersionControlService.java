package com.olivaw.codegraph.scraper.service.retrieval;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;

import java.io.File;
import java.util.List;

public interface VersionControlService {
    VersionControlResponse fetchLatestFiles(VersionControlRequest request) throws Exception;

    VersionControlResponse fetchFullHistory(VersionControlRequest request) throws Exception;

    VersionControlResponse fetchHistoryBetweenDates(VersionControlRequest request) throws Exception;

    VersionControlResponse fetchDiff(VersionControlRequest request) throws Exception;
}
