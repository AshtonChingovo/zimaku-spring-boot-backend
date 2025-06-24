package com.zimaku.zimaku.domain.sales.price.service;

import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.repository.PriceRepository;
import com.zimaku.zimaku.mapper.sales.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceServiceImpl {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    public void savePrice(PriceDto priceDto) {
        priceRepository.save(priceMapper.priceDtoToPrice(priceDto));
    }

    public Page<PriceDto> getPrices(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return priceRepository.findAll(page).map(priceMapper::priceToPriceDto);
    }
}
