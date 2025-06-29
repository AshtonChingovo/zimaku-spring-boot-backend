package com.zimaku.zimaku.domain.sales.price.controller;

import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.service.PriceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/prices")
public class PricesController {

    private final PriceServiceImpl priceServiceImpl;

    public PricesController(PriceServiceImpl priceServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
    }

    @GetMapping
    public ResponseEntity<?> getPrices(@RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "0") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(priceServiceImpl.getPrices(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePrice(@Valid @RequestBody PriceDto priceDto){
        priceServiceImpl.savePrice(priceDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
