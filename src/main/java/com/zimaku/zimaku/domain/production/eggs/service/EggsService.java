package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.EggsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EggsService {

    private EggsRepository eggsRepository;
    private EggsMapper mapper;

    public EggsService(EggsRepository eggsRepository, EggsMapper mapper) {
        this.eggsRepository = eggsRepository;
        this.mapper = mapper;
    }

    public EggsDto saveEggs(EggsDto eggsDto){
        var eggsStock = eggsRepository.save(
                EggsStock.builder()
                        .quantity(eggsDto.getQuantity())
                        .hatchable(eggsDto.getHatchable())
                        .rejects(eggsDto.getRejects())
                        .batchNumber(eggsDto.getBatchNumber())
                        .build()
        );

        return mapper.eggsToEggsDto(eggsStock);
    }

    public Page<EggsDto> getEggs(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return eggsRepository.findAll(paging).map(mapper::eggsToEggsDto);
    }

    public Page<EggsDto> getEggsNotDispatched(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return eggsRepository.findEggsNotDispatched(paging).map(mapper::eggsToEggsDto);
    }

    public void putEggs(EggsDto eggsDto) {
        EggsStock eggsStock = eggsRepository.findById(eggsDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        eggsStock.setQuantity(eggsDto.getQuantity());
        eggsStock.setHatchable(eggsDto.getHatchable());
        eggsStock.setRejects(eggsDto.getRejects());
        eggsStock.setBatchNumber(eggsDto.getBatchNumber());

        eggsRepository.save(eggsStock);
    }

    public void deleteEggs(Integer id) {
        eggsRepository.deleteById(Long.valueOf(id));
    }

}
