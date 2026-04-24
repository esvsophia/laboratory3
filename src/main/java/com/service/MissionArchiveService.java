package com.service;

import com.entity.MissionEntity;
import com.repository.MissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MissionArchiveService {
    private final MissionRepository repository;

    public MissionArchiveService(MissionRepository repository) {
        this.repository = repository;
    }

    public List<MissionEntity> findAll() {
        return repository.findAll();
    }

    public MissionEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}