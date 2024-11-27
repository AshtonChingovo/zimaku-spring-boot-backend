package com.zimaku.zimaku.domain.production.eggs.controller;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.service.EggsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/eggs")
public class EggsController {

    private EggsService eggsService;

    public EggsController(EggsService eggsService) {
        this.eggsService = eggsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEggs(EggsDto eggsDto){
        eggsService.saveEggs(eggsDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
