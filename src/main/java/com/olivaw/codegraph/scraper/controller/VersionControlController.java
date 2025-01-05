package com.olivaw.codegraph.scraper.controller;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlService;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vcs")
public class VersionControlController {

    @PostMapping("/latest")
    public ResponseEntity<VersionControlResponse> fetchLatestFiles(@RequestBody VersionControlRequest request) {
        try {
            var service = getVersionControlService(request);
            var response = service.fetchLatestFiles(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred."));
        }
    }

    @PostMapping("/history/full")
    public ResponseEntity<VersionControlResponse> fetchFullHistory(@RequestBody VersionControlRequest request) {
        try {
            var service = getVersionControlService(request);
            var response = service.fetchLatestFiles(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred"));
        }
    }

    @PostMapping("/history/date-range")
    public ResponseEntity<VersionControlResponse> fetchHistoryBetweenDates(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred"));
        }
    }

    @PostMapping("/diff")
    public ResponseEntity<VersionControlResponse> fetchDiff(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred"));
        }
    }

    private VersionControlService getVersionControlService(VersionControlRequest request) {
        return VersionControlServiceFactory.getService(request.getVersionControlRepoIdentification().getRepoLocation());
    }

    private VersionControlResponse getErrorResponse(String message) {
        return new VersionControlResponse(message, null);
    }
}