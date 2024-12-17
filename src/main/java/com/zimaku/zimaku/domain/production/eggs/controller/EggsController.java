package com.zimaku.zimaku.domain.production.eggs.controller;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.service.EggsService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/eggs")
public class EggsController {

    private EggsService eggsService;

    public EggsController(EggsService eggsService) {
        this.eggsService = eggsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEggs(@Valid @RequestBody EggsDto eggsDto){
        eggsService.saveEggs(eggsDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<EggsDto>> getEggs(@RequestParam(defaultValue = "0") Integer pageNumber,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(eggsService.getEggs(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putEggs(@RequestBody EggsDto eggsDto){
        eggsService.putEggs(eggsDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity deleteEggs(@PathVariable Integer id){
        eggsService.deleteEggs(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
