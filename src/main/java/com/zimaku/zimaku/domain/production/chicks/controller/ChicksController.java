package com.zimaku.zimaku.domain.production.chicks.controller;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import com.zimaku.zimaku.domain.production.chicks.service.ChicksService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chicks")
public class ChicksController {

    ChicksService chicksService;

    public ChicksController(ChicksRepository chicksRepository, ChicksService chicksService) {
        this.chicksService = chicksService;
    }

    @PostMapping
    public ResponseEntity<ChicksDto> saveChicks(@Valid @RequestBody ChicksDto chicksDto){
        return new ResponseEntity<>(chicksService.saveChicks(chicksDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ChicksDto>> getChicks(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(chicksService.getChicks(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ChicksDto> putChicks(@Valid @RequestBody ChicksDto chicksDto){
        chicksService.putChicks(chicksDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteChicks(@PathVariable Integer id){
        chicksService.deleteChicks(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
