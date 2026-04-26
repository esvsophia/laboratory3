package com.report;

import com.model.Mission;

public class DetailedReporter implements IMissionReporter {
    @Override
    public String generate(Mission mission) {
        MissionSummary summary = new BaseSummary(mission);
        summary = new ExtraFieldsDecorator(summary, mission.getExtraFields());
        return summary.getSummary();
    }
}