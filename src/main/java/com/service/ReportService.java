package com.service;

import com.entity.MissionEntity;
import com.repository.MissionRepository;
import com.report.*;
import com.model.Mission;
import com.model.Curse;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final MissionRepository repository;

    public ReportService(MissionRepository repository) {
        this.repository = repository;
    }

    public String generateReport(Long id, String type) {
        MissionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Миссия с ID " + id + " не найдена"));

        Mission model = mapToModel(entity);

        MissionSummary summary = new BaseSummary(model);

        if ("full".equalsIgnoreCase(type)) {
            summary = new ExtraFieldsDecorator(summary, model.getExtraFields());
        }

        return summary.getSummary();
    }

    private Mission mapToModel(MissionEntity entity) {
        Mission model = new Mission();
        model.setMissionId(entity.getMissionId());
        model.setDate(entity.getDate());
        model.setLocation(entity.getLocation());
        model.setOutcome(entity.getOutcome());

        if (entity.getExtraFields() != null) {
            entity.getExtraFields().forEach(model::addField);
        }

        if (entity.getCurse() != null) {
            Curse curse = new Curse();
            curse.setName(entity.getCurse().getName());
            curse.setThreatLevel(entity.getCurse().getDangerLevel());
            model.setCurse(curse);
        }
        return model;
    }
}