package com.zimaku.zimaku.domain.production.dispatch.service;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.DispatchMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

    private DispatchRepository dispatchRepository;
    private EggsStockRepository eggsStockRepository;
    private DispatchMapper mapper;

    public DispatchService(DispatchRepository dispatchRepository, DispatchMapper mapper, EggsStockRepository eggsStockRepository) {
        this.dispatchRepository = dispatchRepository;
        this.eggsStockRepository = eggsStockRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveDispatch(DispatchDto dispatchDto){
        var eggsStock = eggsStockRepository.findById(dispatchDto.getEggsStockId()).orElseThrow(() -> new ResourceNotFoundException("Could not find Eggs Stock record connected to Dispatch"));

        eggsStock.setDispatched(true);
        eggsStockRepository.save(eggsStock);

        var dispatch = mapper.dispatchDtoToDispatch(dispatchDto);
        dispatch.setEggsStock(eggsStock);
        dispatchRepository.save(dispatch);
    }

    public Page<DispatchDto> getDispatches(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return dispatchRepository.findAll(page).map(mapper::dispatchToDispatchDto);
    }

    public Page<DispatchDto> getDispatchesNotInHatchery(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return dispatchRepository.findDispatchesNotInHatchery(page).map(mapper::dispatchToDispatchDto);
    }

}
