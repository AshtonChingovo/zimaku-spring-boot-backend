package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.ChicksMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChicksService {

    private final ChicksRepository chicksRepository;
    private ChicksMapper mapper;

    public ChicksService(ChicksRepository chicksRepository, ChicksMapper mapper) {
        this.chicksRepository = chicksRepository;
        this.mapper = mapper;
    }

    public ChicksDto saveChicks(ChicksDto chicksDto){
        var chicks = chicksRepository.save(
                Chicks.builder()
                        .males(chicksDto.getMales())
                        .females(chicksDto.getFemales())
                        .fatalities(chicksDto.getFatalities())
                        .batchNumber(chicksDto.getBatchNumber())
                        .build()
        );

        return mapper.chicksToChicksDto(chicks);
    }

    public Page<ChicksDto> getChicks(Integer pageNumber, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        return chicksRepository.findAll(paging).map(mapper::chicksToChicksDto);
    }

    public void putChicks(ChicksDto chicksDto){
        Chicks chick = chicksRepository.findById(chicksDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        chick.setMales(chicksDto.getMales());
        chick.setFemales(chicksDto.getFemales());
        chick.setFatalities(chicksDto.getFatalities());
        chick.setBatchNumber(chicksDto.getBatchNumber());

        chicksRepository.save(chick);
    }

    public void deleteChicks(Integer id){
        chicksRepository.deleteById(Long.valueOf(id));
    }

}
