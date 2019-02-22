package com.harmonycloud.service;

import com.harmonycloud.entity.Encounter;
import com.harmonycloud.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncouterService {
    @Autowired
    private EncounterRepository encounterRepository;

    public void save(Encounter encounter) {
        encounterRepository.save(encounter);
    }
}
