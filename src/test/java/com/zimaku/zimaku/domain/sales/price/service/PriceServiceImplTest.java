package com.zimaku.zimaku.domain.sales.price.service;

import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.model.Price;
import com.zimaku.zimaku.domain.sales.price.repository.PriceRepository;
import com.zimaku.zimaku.mapper.sales.PriceMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    PriceRepository priceRepository;
    @Mock
    PriceMapper priceMapper;

    @InjectMocks
    PriceServiceImpl priceServiceImpl;

    PriceDto priceDto;
    Price price;

    @BeforeEach
    void setUp(){

        price = Price.builder()
                .unitPrice(10.0)
                .currency("USD")
                .build();

        priceDto = PriceDto.builder()
                .unitPrice(10.0)
                .currency("USD")
                .build();

    }

    @Test
    void testGetPrices_WhenGetPrices_ShouldReturnPaginatedData(){

        Page<Price> page = new PageImpl<>(List.of(price));

        when(priceRepository.findAll(any(Pageable.class))).thenReturn(page);

        var pagination = priceServiceImpl.getPrices(0, 10, "id");

        assertEquals(1, pagination.getContent().size());

    }

}