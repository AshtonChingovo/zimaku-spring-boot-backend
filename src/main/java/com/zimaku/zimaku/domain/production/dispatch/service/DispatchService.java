package com.zimaku.zimaku.domain.production.dispatch.service;

import com.zimaku.zimaku.domain.production.chicks.service.ChicksService;
import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import com.zimaku.zimaku.mapper.production.DispatchMapper;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

    private static final Logger logger = LogManager.getLogger(DispatchService.class);

    private DispatchRepository dispatchRepository;
    private EggsRepository eggsRepository;
    private DispatchMapper mapper;

    public DispatchService(DispatchRepository dispatchRepository, DispatchMapper mapper, EggsRepository eggsRepository) {
        this.dispatchRepository = dispatchRepository;
        this.eggsRepository = eggsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveDispatch(DispatchDto dispatchDto){
        try{
            var eggsStock = eggsRepository.findById(dispatchDto.getEggsStock().getId()).orElseThrow();
            eggsStock.setDispatched(true);
            eggsRepository.save(eggsStock);

            var dispatch = mapper.dispatchDtoToDispatch(dispatchDto);
            dispatch.setEggsStock(eggsStock);
            dispatchRepository.save(mapper.dispatchDtoToDispatch(dispatchDto));
        }
        catch (Exception e){
            logger.error("Failed to save dispatch", e);
        }
    }

    public Page<DispatchDto> getDispatches(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return dispatchRepository.findAll(page).map(mapper::dispatchToDispatchDto);
    }

    public void putDispatch() {

    }

    public void deleteDispatch(Integer id) {

    }
}
