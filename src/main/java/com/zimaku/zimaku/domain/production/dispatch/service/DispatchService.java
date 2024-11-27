package com.zimaku.zimaku.domain.production.dispatch.service;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import org.springframework.stereotype.Service;

@Service
public class DispatchService {

    private DispatchRepository dispatchRepository;

    public DispatchService(DispatchRepository dispatchRepository) {
        this.dispatchRepository = dispatchRepository;
    }

    public void saveDispatch(DispatchDto dispatchDto){
        dispatchRepository.save(
                Dispatch.builder()
                        .totalDispatched(dispatchDto.totalDispatched())
                        .batchNumber(dispatchDto.batchNumber())
                        .build()
        );
    }

}
