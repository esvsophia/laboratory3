package com.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;

@Entity
@Table(name = "missions")
@Data
public class MissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String missionId;
    private String date;
    private String location;
    private String outcome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curse_id")
    private CurseEntity curse;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> extraFields;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public void setCurse(CurseEntity curse) {
        this.curse = curse;
    }

    public void setExtraFields(Map<String, Object> extraFields) {
        this.extraFields = extraFields;
    }

    public Long getId() {
        return id;
    }

    public String getMissionId() {
        return missionId;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getOutcome() {
        return outcome;
    }

    public CurseEntity getCurse() {
        return curse;
    }

    public Map<String, Object> getExtraFields() {
        return extraFields;
    }
}