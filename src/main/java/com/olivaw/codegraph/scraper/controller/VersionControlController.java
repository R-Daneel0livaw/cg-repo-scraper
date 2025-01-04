package com.olivaw.codegraph.scraper.controller;

import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlService;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlServiceFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vcs")
public class VersionControlController {

    @PostMapping("/latest")
    public ResponseEntity<?> fetchLatestFiles(@RequestBody VersionControlRequest request) {
        try {
            var service = getVersionControlService(request);
            var response = service.fetchLatestFiles(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @PostMapping("/history/full")
    public ResponseEntity<?> fetchFullHistory(@RequestBody VersionControlRequest request) {
        try {
            var service = getVersionControlService(request);
            var response = service.fetchLatestFiles(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

    @PostMapping("/history/date-range")
    public ResponseEntity<List<String>> fetchHistoryBetweenDates(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/diff")
    public ResponseEntity<String> fetchDiff(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    private VersionControlService getVersionControlService(VersionControlRequest request) {
        return VersionControlServiceFactory.getService(request.getVersionControlRepoIdentification().getRepoLocation());
    }
}