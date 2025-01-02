package com.zimaku.zimaku.domain.hatchery.receiveStock.service;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.hatchery.receiveStock.repository.HatcheryRepository;
import com.zimaku.zimaku.mapper.hatchery.HatcheryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HatcheryService {

    private HatcheryRepository hatcheryRepository;
    private HatcheryMapper mapper;

    public HatcheryService(HatcheryRepository hatcheryRepository, HatcheryMapper mapper) {
        this.hatcheryRepository = hatcheryRepository;
        this.mapper = mapper;
    }

    public void saveHatcheryStock(HatcheryStockDto hatcheryStockDto){
        hatcheryRepository.save(
                HatcheryStock.builder()
                        .quantity(hatcheryStockDto.getQuantity())
                        .batchNumber(hatcheryStockDto.getBatchNumber())
                        .totalDispatched(hatcheryStockDto.getTotalDispatched())
                        .eggsId(hatcheryStockDto.getEggsId())
                        .build()
        );
    }

    public Page<HatcheryStockDto> getHatcheryStock(Integer pageNumber, Integer pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort).descending());
        return hatcheryRepository.findAll(page).map(mapper::hatcheryStockToHatcheryDto);
    }

    public void putHatcheryStock(Integer quantity, Long id){
        hatcheryRepository.updateHatcheryStockQuantity(quantity, id);
    }

}
