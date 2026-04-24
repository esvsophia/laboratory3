package com.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "techniques")
@Data
public class TechniqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private MissionEntity mission;
}