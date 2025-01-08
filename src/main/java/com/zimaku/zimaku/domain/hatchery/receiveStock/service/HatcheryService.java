package com.zimaku.zimaku.domain.hatchery.receiveStock.service;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.hatchery.receiveStock.repository.HatcheryRepository;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.hatchery.HatcheryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HatcheryService {

    private HatcheryRepository hatcheryRepository;
    private DispatchRepository dispatchRepository;
    private EggsRepository eggsRepository;

    private HatcheryMapper mapper;

    public HatcheryService(HatcheryRepository hatcheryRepository,
                           DispatchRepository dispatchRepository,
                           EggsRepository eggsRepository,
                           HatcheryMapper mapper) {
        this.hatcheryRepository = hatcheryRepository;
        this.dispatchRepository = dispatchRepository;
        this.eggsRepository = eggsRepository;
        this.mapper = mapper;
    }

    public void saveHatcheryStock(HatcheryStockDto hatcheryStockDto){

        var eggsStock = eggsRepository.findById(hatcheryStockDto.getEggsStockId()).orElseThrow(() -> new ResourceNotFoundException("Eggs stock not found"));
        var dispatch = dispatchRepository.findById(hatcheryStockDto.getDispatchId()).orElseThrow(() -> new ResourceNotFoundException("Dispatch stock not found"));

        hatcheryRepository.save(
                HatcheryStock.builder()
                        .breakages(hatcheryStockDto.getBreakages())
                        .batchNumber(hatcheryStockDto.getBatchNumber())
                        .totalDispatched(hatcheryStockDto.getTotalDispatched())
                        .eggsStock(eggsStock)
                        .dispatch(dispatch)
                        .build()
        );
    }

    public Page<HatcheryStockDto> getHatcheryStock(Integer pageNumber, Integer pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort).descending());
        return hatcheryRepository.findAll(page).map(mapper::hatcheryStockToHatcheryDto);
    }

    public void putHatcheryStock(Integer breakages, Long id){
        hatcheryRepository.updateHatcheryStockQuantity(breakages, id);
    }

}
