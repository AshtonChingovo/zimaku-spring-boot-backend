package com.zimaku.zimaku.domain.production.dispatch.service;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.dispatch.repository.DispatchRepository;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.production.eggs.repository.EggsStockRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.production.DispatchMapper;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DispatchServiceImplTest {

    @Mock
    DispatchRepository dispatchRepository;
    @Mock
    EggsStockRepository eggsStockRepository;
    @Mock
    DispatchMapper mapper;

    @InjectMocks
    DispatchServiceImpl dispatchServiceImpl;

    Dispatch dispatch;
    DispatchDto dispatchDto;
    EggsStock eggsStock;

    @BeforeEach
    void setUp(){

        dispatchDto = DispatchDto.builder()
                .quantity(10)
                .totalStockReceived(10)
                .eggsStockId(1L)
                .batchNumber("AB500")
                .build();

        eggsStock = EggsStock.builder()
                .quantity(10)
                .hatchable(10)
                .rejects(0)
                .batchNumber("AB500")
                .isDispatched(true)
                .build();

        dispatch = Dispatch.builder()
                .quantity(10)
                .totalStockReceived(10)
                .eggsStock(eggsStock)
                .batchNumber("AB500")
                .build();

    }

    @Test
    void testSaveDispatch_WhenEggsStockNotFound_ShouldThrowException(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dispatchServiceImpl.saveDispatch(dispatchDto));

    }

    @Test
    void testSaveDispatch_WhenEggsStockFound_ShouldSetEggsStockDispatchedToTrue(){

        when(eggsStockRepository.findById(any(Long.class))).thenReturn(Optional.of(eggsStock));
        when(mapper.dispatchDtoToDispatch(any(DispatchDto.class))).thenReturn(dispatch);

        dispatchServiceImpl.saveDispatch(dispatchDto);

        assertTrue(eggsStock.isDispatched());
        verify(eggsStockRepository).save(eggsStock);
        verify(dispatchRepository).save(dispatch);

    }

    @Test
    void testGetDispatches_WhenGetDispatches_ShouldReturnedPaginatedContent(){

        Page<Dispatch> page = new PageImpl<>(List.of(dispatch));

        when(dispatchRepository.findAll(any(Pageable.class))).thenReturn(page);

        var pageResult = dispatchServiceImpl.getDispatches(0, 10, "id");

        assertNotNull(pageResult);
        assertEquals(1, pageResult.getContent().size());

    }

    @Test
    void testGetDispatchesNotInHatchery(){
        Page<Dispatch> page = new PageImpl<>(List.of(dispatch));

        when(dispatchRepository.findAll(any(Pageable.class))).thenReturn(page);

        var pageResult = dispatchServiceImpl.getDispatchesNotInHatchery(0, 10, "id");

        assertNotNull(pageResult);
        assertEquals(1, pageResult.getContent().size());

    }

}