package com.zimaku.zimaku.domain.production.dispatch.service;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsRepository;
import com.zimaku.zimaku.mapper.DispatchMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

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
        eggsRepository.updateDispatchStatus(dispatchDto.getEggsId());
        dispatchRepository.save(mapper.dispatchDtoToDispatch(dispatchDto));
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
