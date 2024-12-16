package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.ChicksMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChicksService {

    private final ChicksRepository chicksRepository;
    private ChicksMapper chicksMapper;

    public ChicksService(ChicksRepository chicksRepository, ChicksMapper chicksMapper) {
        this.chicksRepository = chicksRepository;
        this.chicksMapper = chicksMapper;
    }

    public ChicksDto saveChicks(ChicksDto chicksDto){
        var chicks = chicksRepository.save(
                Chicks.builder()
                        .males(chicksDto.getMales())
                        .females(chicksDto.getFemales())
                        .fatalities(chicksDto.getFatalities())
                        .batch(chicksDto.getBatch())
                        .build()
        );

        return chicksMapper.chicksToChicksDto(chicks);
    }

    public Page<ChicksDto> getChicks(Integer pageNumber, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNumber, pageSize);

        return chicksRepository.findAll(paging).map(chicksMapper::chicksToChicksDto);
    }

    public ChicksDto putChicks(ChicksDto chicksDto){
        Chicks chick = chicksRepository.findById(chicksDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        chick.setMales(chicksDto.getMales());
        chick.setFemales(chicksDto.getFemales());
        chick.setFatalities(chicksDto.getFatalities());
        chick.setBatch(chicksDto.getBatch());

        return chicksMapper.chicksToChicksDto(chicksRepository.save(chick));
    }

    public void deleteChicks(ChicksDto chicksDto){
        Chicks chick = chicksRepository.findById(chicksDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to delete"));
    }

}
