package com.zimaku.zimaku.domain.production.eggs.controller;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsStockDto;
import com.zimaku.zimaku.domain.production.eggs.service.EggsStockService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/eggs")
public class EggsStockController {

    private EggsStockService eggsStockService;

    public EggsStockController(EggsStockService eggsStockService) {
        this.eggsStockService = eggsStockService;
    }

    @PostMapping
    public ResponseEntity<EggsStockDto> saveEggs(@Valid @RequestBody EggsStockDto eggsStockDto){
        return new ResponseEntity<>(eggsStockService.saveEggs(eggsStockDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<EggsStockDto>> getEggs(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(eggsStockService.getEggs(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/not_dispatched")
    public ResponseEntity<Page<EggsStockDto>> getEggsNotDispatched(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(eggsStockService.getEggsNotDispatched(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putEggs(@Valid @RequestBody EggsStockDto eggsStockDto){
        eggsStockService.putEggs(eggsStockDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEggs(@PathVariable Integer id){
        eggsStockService.deleteEggs(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
