package com.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sorcerers")
@Data
public class SorcererEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rank;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private MissionEntity mission;
}