package com.controller;

import com.entity.MissionEntity;
import com.service.MissionImportService;
import com.service.MissionArchiveService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@CrossOrigin
public class MissionController {
    private final MissionImportService importService;
    private final MissionArchiveService archiveService;

    public MissionController(MissionImportService importService, MissionArchiveService archiveService) {
        this.importService = importService;
        this.archiveService = archiveService;
    }

    @PostMapping("/upload")
    public MissionEntity uploadMission(@RequestParam("file") MultipartFile file) {
        return importService.importMission(file);
    }

    @GetMapping
    public List<MissionEntity> getAllMissions() {
        return archiveService.findAll();
    }

    @GetMapping("/{id}")
    public MissionEntity getMission(@PathVariable Long id) {
        return archiveService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        archiveService.delete(id);
    }
}