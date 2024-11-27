package com.zimaku.zimaku.domain.production.dispatch.controller;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.service.DispatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
