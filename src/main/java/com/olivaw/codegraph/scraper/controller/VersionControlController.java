package com.olivaw.codegraph.scraper.controller;

import com.olivaw.codegraph.scraper.model.VersionControlResponse;
import com.olivaw.codegraph.scraper.model.request.VersionControlRequest;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlService;
import com.olivaw.codegraph.scraper.service.retrieval.VersionControlServiceFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vcs")
public class VersionControlController {

    private final VersionControlServiceFactory versionControlServiceFactory;

    @Autowired
    public VersionControlController(VersionControlServiceFactory versionControlServiceFactory) {
        this.versionControlServiceFactory = versionControlServiceFactory;
    }

    @Operation(summary = "Get all files at specified location.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the files")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Get all files and history at specified location.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the files")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Get all files for a date range at a specified location.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the files")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/history/date-range")
    public ResponseEntity<VersionControlResponse> fetchHistoryBetweenDates(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred"));
        }
    }

    @Operation(summary = "Get all files with differences at a specified location.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the files")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/diff")
    public ResponseEntity<VersionControlResponse> fetchDiff(@RequestBody VersionControlRequest request) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(getErrorResponse("An unexpected error occurred"));
        }
    }

    private VersionControlService getVersionControlService(VersionControlRequest request) {
        return versionControlServiceFactory.getService(request.getVersionControlRepoIdentification().getRepoLocation());
    }

    private VersionControlResponse getErrorResponse(String message) {
        return new VersionControlResponse(message, null);
    }
}