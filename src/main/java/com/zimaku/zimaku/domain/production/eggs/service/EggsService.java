package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
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

    public Page<EggsDto> getEggs(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        return eggsRepository.findAll(paging).map(mapper::eggsToEggsDto);
    }

    public void putEggs(EggsDto eggsDto) {
        Eggs eggs = eggsRepository.findById(eggsDto.id()).orElseThrow(() -> new ResourceNotFoundException("Failed to find resource you want to update"));

        eggs.setQuantity(eggsDto.quantity());
        eggs.setHatchable(eggsDto.hatchable());
        eggs.setRejects(eggsDto.rejects());
        eggs.setBatchNumber(eggsDto.batchNumber());

        eggsRepository.save(eggs);
    }

    public void deleteEggs(Integer id) {
        eggsRepository.deleteById(Long.valueOf(id));
    }

}
