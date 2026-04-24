package com.controller;

import com.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long id,
            @RequestParam(defaultValue = "full") String type) {
        byte[] reportData = reportService.generateReportFile(id, type);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mission_report_" + id + ".txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(reportData);
    }
}