package com.controller;

import com.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;

@RestController // Важно: RestController для API
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadReport(
            @PathVariable Long id,
            @RequestParam(value = "type", defaultValue = "brief") String type) {

        String reportText = reportService.generateReport(id, type);

        byte[] reportData = reportText.getBytes(StandardCharsets.UTF_8);

        String fileName = "mission_report_" + id + ".txt";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(reportData);
    }
}