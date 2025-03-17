package com.zimaku.zimaku.domain.hatchery.receiveStock.service;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.hatchery.receiveStock.repository.HatcheryRepository;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.hatchery.HatcheryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        dispatch.setId(1L);

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

    @Test
    @DisplayName("testEggStockIdNotFound_ThrowsResourceNotFoundException")
    void testSaveHatcheryStock_WhenEggsStockIdNotFound_ShouldThrowResourceNotFoundException(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> hatcheryService.saveHatcheryStock(hatcheryStockDto));

    }

    @Test
    @DisplayName("testDispatchIdNotFound_ThrowsResourceNotFoundException")
    void testSaveHatcheryStock_WhenDispatchIdNotFound_ShouldThrowResourceNotFoundException(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.of(eggsStock));
        when(dispatchRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> hatcheryService.saveHatcheryStock(hatcheryStockDto));

    }

    @Test
    @DisplayName("testGetHatcheryStock_ReturnsPagination")
    void testGetHatcheryStock_WhenGetHatcheryStock_ShouldReturnPaginationResult(){

        Page<HatcheryStock> page = new PageImpl<>(List.of(hatcheryStock));

        when(hatcheryRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(mapper.hatcheryStockToHatcheryDto(any(HatcheryStock.class))).thenReturn(hatcheryStockDto);

        var pageResult = hatcheryService.getHatcheryStock(0, 10, "id");

        assertNotNull(pageResult);
        assertEquals(1, pageResult.getSize());
        assertEquals(hatcheryStockDto, pageResult.getContent().get(0));

    }

    @Test
    @DisplayName("testPutHatchery_UpdateCalledOnce")
    void testPutHatchery_WhenPutHatcheryStockRecord_ShouldCallUpdateHatcheryOnce(){
        Integer breakages = 2;
        Long hatcheryStockId = 7L;

        when(hatcheryRepository.existsById(any(Long.class))).thenReturn(true);

        hatcheryService.putHatcheryStock(breakages, hatcheryStockId);

        verify(hatcheryRepository).updateHatcheryStockQuantity(breakages, hatcheryStockId);

    }

    @Test
    @DisplayName("testPutHatchery_ThrowsExceptionIfIdDoesNotExist")
     void testPutHatchery_WhenPutHatcheryStockRecordIdDoesNotExist_ShouldThrowResourceNotFoundException(){
        Integer breakages = 2;
        Long hatcheryStockId = 7L;

        when(hatcheryRepository.existsById(any(Long.class))).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> hatcheryService.putHatcheryStock(breakages, hatcheryStockId));

    }


}