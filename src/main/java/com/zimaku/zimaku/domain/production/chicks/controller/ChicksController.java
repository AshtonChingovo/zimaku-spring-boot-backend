package com.zimaku.zimaku.domain.production.chicks.controller;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.repository.ChicksRepository;
import com.zimaku.zimaku.domain.production.chicks.service.ChicksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chicks")
public class ChicksController {

    ChicksRepository chicksRepository;
    ChicksService chicksService;

    public ChicksController(ChicksRepository chicksRepository, ChicksService chicksService) {
        this.chicksRepository = chicksRepository;
        this.chicksService = chicksService;
    }

    @PostMapping
    public ResponseEntity<Object> saveChicks(@Valid @RequestBody ChicksDto chicksDto){
        chicksService.saveChicks(chicksDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
