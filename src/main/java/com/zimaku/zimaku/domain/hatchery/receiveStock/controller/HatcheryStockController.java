package com.zimaku.zimaku.domain.hatchery.receiveStock.controller;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.service.HatcheryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hatchery_stock")
class HatcheryStockController {

    private HatcheryServiceImpl hatcheryServiceImpl;

    public HatcheryStockController(HatcheryServiceImpl hatcheryServiceImpl) {
        this.hatcheryServiceImpl = hatcheryServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Page<HatcheryStockDto>> getHatcheryStock(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam(defaultValue = "id") String sort){
        return new ResponseEntity<>(hatcheryServiceImpl.getHatcheryStock(pageNumber, pageSize, sort), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveHatcheryStock(@Valid @RequestBody HatcheryStockDto hatcheryStockDto){
        hatcheryServiceImpl.saveHatcheryStock(hatcheryStockDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Object> putHatcheryStock(@Valid @RequestBody HatcheryStockDto hatcheryStockDto){
        hatcheryServiceImpl.putHatcheryStock(hatcheryStockDto.getBreakages(), hatcheryStockDto.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}