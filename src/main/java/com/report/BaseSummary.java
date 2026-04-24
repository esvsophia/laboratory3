package com.report;

import com.model.Mission;
import com.model.Curse;

public class BaseSummary implements MissionSummary {
    private final Mission mission;

    public BaseSummary(Mission mission) {
        this.mission = mission;
    }

    @Override
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Отчет о миссии\n\n");
        sb.append("ID: ").append(mission.getMissionId()).append("\n");
        sb.append("Дата: ").append(mission.getDate()).append("\n");
        sb.append("Локация: ").append(mission.getLocation()).append("\n");
        sb.append("Результат: ").append(mission.getOutcome()).append("\n");
        Curse cur = mission.getCurse();
        if (cur != null) {
            sb.append("Проклятие: ").append(cur.getName())
                    .append(" (Уровень угрозы: ").append(cur.getThreatLevel()).append(")\n");
        }
        return sb.toString();
    }
}