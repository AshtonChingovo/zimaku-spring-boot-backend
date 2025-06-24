package com.zimaku.zimaku.domain.production.chicks.service;

import com.zimaku.zimaku.domain.production.chicks.dto.AverageWeightDto;
import com.zimaku.zimaku.domain.production.chicks.dto.ChicksStockDto;
import com.zimaku.zimaku.domain.production.chicks.entity.AverageWeight;
import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.AverageWeightMapper;
import com.zimaku.zimaku.mapper.production.ChicksMapper;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChicksStockServiceImplTest {

    @Mock
    ChicksStockRepository chicksStockRepository;
    @Mock
    ChicksMapper chicksMapper;
    @Mock
    AverageWeightMapper averageWeightMapper;
    @Mock
    ChicksStock chicksStockMock;

    @InjectMocks
    ChicksStockServiceImpl chicksStockServiceImpl;

    ChicksStock chicksStock;
    ChicksStockDto chicksStockDto;
    AverageWeight averageWeight;
    AverageWeightDto averageWeightDto;

    @BeforeEach
    void setUp() {

        averageWeight = AverageWeight.builder()
                .id(0L)
                .week(1)
                .averageWeight(0.5)
                .build();

        averageWeightDto = AverageWeightDto.builder()
                .id(0L)
                .week(1)
                .averageWeight(0.5)
                .build();

        chicksStockDto = ChicksStockDto.builder()
                .id(0L)
                .males(10)
                .females(10)
                .fatalities(0)
                .batchNumber("BGE 564")
                .age("4")
                .averageWeight(Set.of(averageWeightDto))
                .build();

        chicksStock = ChicksStock.builder()
                .id(0L)
                .males(chicksStockDto.getMales())
                .females(chicksStockDto.getFemales())
                .fatalities(chicksStockDto.getFatalities())
                .batchNumber(chicksStockDto.getBatchNumber())
                .build();

    }

    @Test
    @DisplayName("testSaveChicks_ShouldReturnNoneNullObject")
    void testSaveChicks_WhenSaveChicks_ShouldReturnDTOObject(){

        when(chicksStockRepository.save(any(ChicksStock.class))).thenReturn(chicksStock);
        when(chicksMapper.chicksToChicksDto(any(ChicksStock.class))).thenReturn(chicksStockDto);

        ChicksStockDto chicksDto = chicksStockServiceImpl.saveChicks(chicksStockDto);

        verify(chicksMapper).chicksToChicksDto(any());
        assertNotNull(chicksDto);
    }

    @Test
    @DisplayName("testGetChicks_ShouldPaginationResult")
    void testGetChicks_WhenGetChicks_ShouldPaginationResult(){
        Page<ChicksStock> page = new PageImpl<>(List.of(chicksStock));

        when(chicksStockRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(chicksMapper.chicksToChicksDto(any(ChicksStock.class))).thenReturn(chicksStockDto);

        var pageResult = chicksStockServiceImpl.getChicks(0, 10, "id");

        assertNotNull(pageResult);
        assertEquals(1, pageResult.getContent().size());
    }

    @Test
    @DisplayName("testSaveAverageWeight_ShouldThrowResourceNotFoundException(")
    void testSaveAverageWeight_WhenChicksStockIDNotFound_ShouldThrowResourceNotFoundException(){

        when(chicksStockRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chicksStockServiceImpl.saveAverageWeight(chicksStockDto));
    }

    @Test
    void testSaveAverageWeight_WhenSaveAverageWeight_ShouldCallRepositorySave(){

        when(chicksStockRepository.findById(any(Long.class))).thenReturn(Optional.of(chicksStock));
        when(averageWeightMapper.averageWeightDtoToAverageWeight(any(AverageWeightDto.class))).thenReturn(averageWeight);

        chicksStockServiceImpl.saveAverageWeight(chicksStockDto);

        verify(chicksStockRepository).save(chicksStock);

    }

}