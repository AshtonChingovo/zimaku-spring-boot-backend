package com.zimaku.zimaku.domain.sales.orders.controller;

import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getOrders(@RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "0") Integer pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy,
                                       @RequestParam(defaultValue = "") String orderType){
        return new ResponseEntity<>(orderService.getOrders(pageNumber, pageSize, sortBy, orderType), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDto orderDto){
        orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderDto orderDto){
        orderService.updateOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@Valid @RequestBody OrderDto orderDto){
        orderService.deleteOrder(orderDto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
