package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsStockDto;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.EggsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EggsStockService {

    private EggsStockRepository eggsStockRepository;
    private EggsMapper mapper;

    public EggsStockService(EggsStockRepository eggsStockRepository, EggsMapper mapper) {
        this.eggsStockRepository = eggsStockRepository;
        this.mapper = mapper;
    }

    public EggsStockDto saveEggs(EggsStockDto eggsStockDto){
        var eggsStock = eggsStockRepository.save(
                EggsStock.builder()
                        .quantity(eggsStockDto.getQuantity())
                        .hatchable(eggsStockDto.getHatchable())
                        .rejects(eggsStockDto.getRejects())
                        .batchNumber(eggsStockDto.getBatchNumber())
                        .build()
        );

        return mapper.eggsToEggsDto(eggsStock);
    }

    public Page<EggsStockDto> getEggs(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return eggsStockRepository.findAll(paging).map(mapper::eggsToEggsDto);
    }

    public Page<EggsStockDto> getEggsNotDispatched(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return eggsStockRepository.findEggsNotDispatched(paging).map(mapper::eggsToEggsDto);
    }

    public void putEggs(EggsStockDto eggsStockDto) {
        EggsStock eggsStock = eggsStockRepository.findById(eggsStockDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        eggsStock.setQuantity(eggsStockDto.getQuantity());
        eggsStock.setHatchable(eggsStockDto.getHatchable());
        eggsStock.setRejects(eggsStockDto.getRejects());
        eggsStock.setBatchNumber(eggsStockDto.getBatchNumber());

        eggsStockRepository.save(eggsStock);
    }

    public void deleteEggs(Integer id) {
        eggsStockRepository.deleteById(Long.valueOf(id));
    }

}
