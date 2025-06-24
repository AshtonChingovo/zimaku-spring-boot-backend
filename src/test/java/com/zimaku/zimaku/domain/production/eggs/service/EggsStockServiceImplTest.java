package com.zimaku.zimaku.domain.production.eggs.service;

import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import com.zimaku.zimaku.domain.production.eggs.dto.EggsStockDto;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.EggsMapper;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EggsStockServiceTest {

    @Mock
    EggsStockRepository eggsStockRepository;
    @Mock
    EggsMapper mapper;

    @InjectMocks
    EggsStockService eggsStockService;

    EggsStock eggsStock;
    EggsStockDto eggsStockDto;

    @BeforeEach
    void setUp() {

        eggsStock = EggsStock.builder()
                .quantity(10)
                .hatchable(10)
                .rejects(0)
                .batchNumber("A500")
                .isDispatched(false)
                .build();

        eggsStockDto = EggsStockDto.builder()
                .id(1L)
                .quantity(10)
                .hatchable(10)
                .rejects(0)
                .batchNumber("A500")
                .isDispatched(false)
                .build();

    }

    @Test
    @DisplayName("testSaveEggsStock")
    void testSaveEggs_WhenSaveEggs_ShouldReturnEggsStockStockDto(){

        when(eggsStockRepository.save(any(EggsStock.class))).thenReturn(eggsStock);
        when(mapper.eggsStockToEggsStockDto(any(EggsStock.class))).thenReturn(eggsStockDto);

        var result = eggsStockService.saveEggsStock(eggsStockDto);

        assertEquals(eggsStock.getQuantity(), result.getQuantity());
        assertEquals(eggsStock.getHatchable(), result.getHatchable());
        assertEquals(eggsStock.getRejects(), result.getRejects());
        assertEquals(eggsStock.getBatchNumber(), result.getBatchNumber());
    }

    @Test
    void testGetEggsStock_WhenGetEggsStock_ShouldReturnPaginatedData(){

        Page<EggsStock> page = new PageImpl<>(List.of(eggsStock));

        when(eggsStockRepository.findAll(any(Pageable.class))).thenReturn(page);

        var pageResult = eggsStockService.getEggsStock(0, 10, "id");

        assertNotNull(pageResult);
        assertEquals(1, pageResult.getContent().size());
    }

    @Test
    void testPutEggsStock_WhenPutEggsStock_ShouldThrowExceptionWhenIdNotFound(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> eggsStockService.putEggsStock(eggsStockDto));

    }

}