package com.zimaku.zimaku.domain.production.dispatch.controller;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.service.DispatchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dispatch")
public class DispatchController {

    private DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<Object> saveDispatch(DispatchDto dispatchDto){
        dispatchService.saveDispatch(dispatchDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<DispatchDto>> getDispatches(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(dispatchService.getDispatches(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity putDispatch(@Valid @RequestBody DispatchDto dispatchDto){
        dispatchService.putDispatch();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity deleteDispatch(@PathVariable Integer id){
        dispatchService.deleteDispatch(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
