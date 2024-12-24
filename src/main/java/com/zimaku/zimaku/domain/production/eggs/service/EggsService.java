package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.EggsMapper;
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
        var eggs = eggsRepository.save(
                Eggs.builder()
                        .quantity(eggsDto.getQuantity())
                        .hatchable(eggsDto.getHatchable())
                        .rejects(eggsDto.getRejects())
                        .batchNumber(eggsDto.getBatchNumber())
                        .build()
        );

        return mapper.eggsToEggsDto(eggs);
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
        Eggs eggs = eggsRepository.findById(eggsDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        eggs.setQuantity(eggsDto.getQuantity());
        eggs.setHatchable(eggsDto.getHatchable());
        eggs.setRejects(eggsDto.getRejects());
        eggs.setBatchNumber(eggsDto.getBatchNumber());

        eggsRepository.save(eggs);
    }

    public void deleteEggs(Integer id) {
        eggsRepository.deleteById(Long.valueOf(id));
    }

}
