package com.report;

import java.util.List;
import java.util.Map;

public class ExtraFieldsDecorator implements MissionSummary {
    private final MissionSummary wrapped;
    private final Map<String, Object> extraFields;

    public ExtraFieldsDecorator(MissionSummary wrapped, Map<String, Object> extraFields) {
        this.wrapped = wrapped;
        this.extraFields = extraFields;
    }

    @Override
    public String getSummary() {
        StringBuilder sb = new StringBuilder(wrapped.getSummary());
        if (extraFields == null || extraFields.isEmpty()) {
            return sb.toString();
        }

        sb.append("\nДополнительная информация:\n");
        for (Map.Entry<String, Object> entry : extraFields.entrySet()) {
            sb.append("\n").append(entry.getKey()).append(":\n");
            sb.append(formatValue(entry.getValue(), "  "));
        }
        return sb.toString();
    }

    private String formatValue(Object value, String indent) {
        StringBuilder sb = new StringBuilder();
        if (value instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) value;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object val = entry.getValue();
                if (val instanceof List || val instanceof Map) {
                    sb.append(indent).append(entry.getKey()).append(":\n");
                    sb.append(formatValue(val, indent + "  "));
                } else {
                    sb.append(indent).append(entry.getKey()).append(": ").append(val).append("\n");
                }
            }
        } else if (value instanceof List) {
            for (Object item : (List<Object>) value) {
                if (item instanceof Map) {
                    sb.append(formatValue(item, indent));
                    sb.append("\n");
                } else {
                    sb.append(indent).append(item).append("\n");
                }
            }
        } else {
            sb.append(indent).append(value).append("\n");
        }
        return sb.toString();
    }
}