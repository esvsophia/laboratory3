package com.controller;

import com.entity.MissionEntity;
import com.service.MissionImportService;
import com.service.MissionArchiveService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    private final MissionImportService importService;
    private final MissionArchiveService archiveService;

    public MissionController(MissionImportService importService, MissionArchiveService archiveService) {
        this.importService = importService;
        this.archiveService = archiveService;
    }

    @Operation(summary = "Загрузить файл миссии (JSON, XML, YAML, TXT)")
    @PostMapping("/upload")
    public MissionEntity uploadMission(@RequestParam("file") MultipartFile file) {
        return importService.importMission(file);
    }

    @Operation(summary = "Получить все миссии из архива")
    @GetMapping
    public List<MissionEntity> getAllMissions() {
        return archiveService.findAll();
    }

    @Operation(summary = "Получить одну миссию из архива")
    @GetMapping("/{id}")
    public MissionEntity getOneMission(@PathVariable Long id) {
        return archiveService.findById(id);
    }

    @Operation(summary = "Удалить миссию по ID")
    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        archiveService.delete(id);
    }
}