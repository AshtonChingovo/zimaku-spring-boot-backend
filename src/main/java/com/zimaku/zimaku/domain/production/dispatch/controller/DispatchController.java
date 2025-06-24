package com.zimaku.zimaku.domain.production.dispatch.controller;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.service.DispatchServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/dispatches")
public class DispatchController {

    private DispatchServiceImpl dispatchServiceImpl;

    public DispatchController(DispatchServiceImpl dispatchServiceImpl) {
        this.dispatchServiceImpl = dispatchServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> saveDispatch(@Valid @RequestBody DispatchDto dispatchDto){
        dispatchServiceImpl.saveDispatch(dispatchDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<DispatchDto>> getDispatches(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(dispatchServiceImpl.getDispatches(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping(path = "hatchery")
    public ResponseEntity<Page<DispatchDto>> getDispatchesNotInHatchery(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(dispatchServiceImpl.getDispatchesNotInHatchery(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

}
