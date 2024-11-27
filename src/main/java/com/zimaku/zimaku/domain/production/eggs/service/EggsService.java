package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import org.springframework.stereotype.Service;

@Service
public class EggsService {

    private EggsRepository eggsRepository;

    public EggsService(EggsRepository eggsRepository) {
        this.eggsRepository = eggsRepository;
    }

    public void saveEggs(EggsDto eggsDto){
        eggsRepository.save(
                Eggs.builder()
                        .quantity(eggsDto.quantity())
                        .hatchable(eggsDto.hatchable())
                        .rejects(eggsDto.rejects())
                        .batchNumber(eggsDto.batchNumber())
                        .build()
        );
    }

}
