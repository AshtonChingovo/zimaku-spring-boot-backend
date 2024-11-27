package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import org.springframework.stereotype.Service;

@Service
public class ChicksService {

    private final ChicksRepository chicksRepository;

    public ChicksService(ChicksRepository chicksRepository) {
        this.chicksRepository = chicksRepository;
    }

    public void saveChicks(ChicksDto chicksDto){
        chicksRepository.save(
                Chicks.builder()
                        .males(chicksDto.males())
                        .females(chicksDto.females())
                        .fatalities(chicksDto.fatalities())
                        .batchNumber(chicksDto.batch())
                        .build()
        );
    }

    public void getChicks(){

    }

}
