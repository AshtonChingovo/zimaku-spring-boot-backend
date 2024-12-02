package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import com.zimaku.zimaku.mapper.ChicksMapper;
import org.springframework.stereotype.Service;

@Service
public class ChicksService {

    private final ChicksRepository chicksRepository;
    private ChicksMapper chicksMapper;

    public ChicksService(ChicksRepository chicksRepository,
                         ChicksMapper chicksMapper) {
        this.chicksRepository = chicksRepository;
        this.chicksMapper = chicksMapper;
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
