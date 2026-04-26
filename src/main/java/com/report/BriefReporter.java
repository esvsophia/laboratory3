package com.report;

import com.model.Mission;

public class BriefReporter implements IMissionReporter {
    @Override
    public String generate(Mission mission) {
        return new BaseSummary(mission).getSummary();
    }
}