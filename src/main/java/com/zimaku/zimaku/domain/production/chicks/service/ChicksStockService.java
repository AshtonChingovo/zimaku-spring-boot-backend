package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksStockDto;
import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.AverageWeightMapper;
import com.zimaku.zimaku.mapper.production.ChicksMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ChicksStockService {

    private static final Logger logger = LogManager.getLogger(ChicksStockService.class);

    private final ChicksStockRepository chicksStockRepository;
    private ChicksMapper chicksStockMapper;
    private AverageWeightMapper averageWeightMapper;

    public ChicksStockService(ChicksStockRepository chicksStockRepository, ChicksMapper chicksStockMapper, AverageWeightMapper averageWeightMapper) {
        this.chicksStockRepository = chicksStockRepository;
        this.chicksStockMapper = chicksStockMapper;
        this.averageWeightMapper = averageWeightMapper;
    }

    public ChicksStockDto saveChicks(ChicksStockDto chicksStockDto){
        var chicks = chicksStockRepository.save(
                ChicksStock.builder()
                        .males(chicksStockDto.getMales())
                        .females(chicksStockDto.getFemales())
                        .fatalities(chicksStockDto.getFatalities())
                        .batchNumber(chicksStockDto.getBatchNumber())
                        .build()
        );

        return chicksStockMapper.chicksToChicksDto(chicks);
    }

    public Page<ChicksStockDto> getChicks(Integer pageNumber, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return chicksStockRepository.findAll(paging).map(chicksStockMapper::chicksToChicksDto);
    }

    public void saveAverageWeight(ChicksStockDto chicksStockDto){
        ChicksStock chick = chicksStockRepository.findById(chicksStockDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Chick with ID " + chicksStockDto.getId() + " not found for average weight update."));

        var averageWeights = chicksStockDto.getAverageWeight()
                .stream()
                .map(averageWeightMapper::averageWeightDtoToAverageWeight)
                .collect(Collectors.toSet());
        if(chick.getAverageWeight() != null)
            averageWeights.addAll(chick.getAverageWeight());

        chick.setAverageWeight(averageWeights);
        chicksStockRepository.save(chick);
    }

    public void putChicks(ChicksStockDto chicksStockDto){
        ChicksStock chick = chicksStockRepository.findById(chicksStockDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        chick.setMales(chicksStockDto.getMales());
        chick.setFemales(chicksStockDto.getFemales());
        chick.setFatalities(chicksStockDto.getFatalities());
        chick.setBatchNumber(chicksStockDto.getBatchNumber());

        chicksStockRepository.save(chick);
    }

    public void deleteChicks(Integer id){
        chicksStockRepository.deleteById(Long.valueOf(id));
    }

}
