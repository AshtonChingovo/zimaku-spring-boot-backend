package com.zimaku.zimaku.domain.hatchery.receiveStock.service;


import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.hatchery.receiveStock.repository.HatcheryRepository;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.mapper.hatchery.HatcheryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HatcheryServiceTest {

    @Mock
    HatcheryRepository hatcheryRepository;

    @Mock
    DispatchRepository dispatchRepository;

    @Mock
    EggsStockRepository eggsStockRepository;

    @InjectMocks
    HatcheryService hatcheryService;

    @Mock
    HatcheryMapper mapper;

    EggsStock eggsStock;
    Dispatch dispatch;
    HatcheryStock hatcheryStock;
    HatcheryStockDto hatcheryStockDto;

    @BeforeEach
    void setUp(){

        eggsStock =EggsStock.builder()
                .id(1L)
                .quantity(20)
                .hatchable(20)
                .rejects(20)
                .batchNumber("AD324")
                .isDispatched(false)
                .build();

        dispatch = Dispatch.builder()
                .id(1L)
                .quantity(7)
                .batchNumber("TY33")
                .totalStockReceived(7)
                .dateStockReceived("23 Dec 2024")
                .build();

        hatcheryStock = HatcheryStock.builder()
                .breakages(12)
                .batchNumber("BATCH 67")
                .totalDispatched(22)
                .eggsStock(eggsStock)
                .dispatch(dispatch)
                .build();

        hatcheryStockDto = HatcheryStockDto.builder()
                .date("12/02/2025")
                .batchNumber("BATCH 67")
                .breakages(12)
                .totalDispatched(22)
                .difference(10)
                .dispatchId(1L)
                .eggsStockId(1L)
                .build();

    }

    @Test
    @DisplayName("testSaveHatcheryStock")
    void testSaveHatcheryStock_WhenSaveHatcheryStock_ShouldCallSaveOnce(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.of(eggsStock));
        when(dispatchRepository.findById(any(Long.class))).thenReturn(Optional.of(dispatch));

        hatcheryService.saveHatcheryStock(hatcheryStockDto);

        verify(hatcheryRepository, times(1)).save(hatcheryStock);

    }

}