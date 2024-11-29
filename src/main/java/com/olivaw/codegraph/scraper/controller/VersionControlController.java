package com.olivaw.codegraph.scraper.controller;

import com.olivaw.codegraph.scraper.model.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.VersionControlService;
import com.olivaw.codegraph.scraper.service.VersionControlServiceFactory;
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
    public ResponseEntity<String> fetchLatestFiles(@RequestBody VersionControlRequest request) {
        try {
            VersionControlService service = VersionControlServiceFactory.getService(request.getRepoLocation());
            service.fetchLatestFiles(request);
            return ResponseEntity.ok("Fetched latest files successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/history/full")
    public ResponseEntity<String> fetchFullHistory() {
        try {
            return ResponseEntity.ok("Fetched full history successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/history/date-range")
    public ResponseEntity<List<String>> fetchHistoryBetweenDates() {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/diff")
    public ResponseEntity<String> fetchDiff() {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}