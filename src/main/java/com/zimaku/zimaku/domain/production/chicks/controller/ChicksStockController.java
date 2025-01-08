package com.zimaku.zimaku.domain.production.chicks.controller;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksStockDto;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksStockRepository;
import com.zimaku.zimaku.domain.production.chicks.service.ChicksStockService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing operations related to chicks.
 * This class provides RESTful endpoints to handle CRUD operations
 * and other operations like saving average weights for chicks.
 */
@RestController
@RequestMapping("api/v1/chicks")
public class ChicksStockController {

    ChicksStockService chicksStockService;

    public ChicksStockController(ChicksStockRepository chicksStockRepository, ChicksStockService chicksStockService) {
        this.chicksStockService = chicksStockService;
    }

    @PostMapping
    public ResponseEntity<ChicksStockDto> saveChicks(@Valid @RequestBody ChicksStockDto chicksStockDto){
        return new ResponseEntity<>(chicksStockService.saveChicks(chicksStockDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/average_weights")
    public ResponseEntity<Object> saveChickAverageWeight(@Valid @RequestBody ChicksStockDto chicksStockDto){
        chicksStockService.saveAverageWeight(chicksStockDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ChicksStockDto>> getChicks(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(chicksStockService.getChicks(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ChicksStockDto> putChicks(@Valid @RequestBody ChicksStockDto chicksStockDto){
        chicksStockService.putChicks(chicksStockDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChicks(@PathVariable Integer id){
        chicksStockService.deleteChicks(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
