package com.service;

import com.entity.MissionEntity;
import com.entity.CurseEntity;
import com.model.Mission;
import com.parser.IMissionParser;
import com.parser.ParserFactory;
import com.repository.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MissionImportService {
    private final MissionRepository repository;

    public MissionImportService(MissionRepository repository) {
        this.repository = repository;
    }

    public MissionEntity importMission(MultipartFile multipartFile) {
        try {
            Path tempFile = Files.createTempFile("upload-", multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile.toFile());

            Mission model = ParserFactory.getParser(tempFile.toString()).loadMission(tempFile.toString());
            Files.delete(tempFile);

            if (model == null) {
                throw new RuntimeException("Не удалось распознать формат файла");
            }

            return repository.save(mapToEntity(model));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при импорте миссии: " + e.getMessage(), e);
        }
    }

    private MissionEntity mapToEntity(Mission model) {
        MissionEntity entity = new MissionEntity();
        entity.setMissionId(model.getMissionId());
        entity.setDate(model.getDate());
        entity.setLocation(model.getLocation());
        entity.setOutcome(model.getOutcome());
        entity.setExtraFields(model.getExtraFields());

        if (model.getCurse() != null) {
            CurseEntity curse = new CurseEntity();
            curse.setName(model.getCurse().getName());
            curse.setDangerLevel(model.getCurse().getThreatLevel());
            entity.setCurse(curse);
        }
        return entity;
    }
}