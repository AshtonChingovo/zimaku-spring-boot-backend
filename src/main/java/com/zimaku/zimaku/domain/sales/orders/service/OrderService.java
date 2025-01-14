package com.zimaku.zimaku.domain.sales.orders.service;

import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.repository.OrderRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.sales.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper mapper;

    public OrderService(OrderRepository orderRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public Page<OrderDto> getOrders(int pageNumber, int pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort).descending());
        log.info("Content :: {}", orderRepository.findAll(page).map(mapper::orderToOrderDto));
        return orderRepository.findAll(page).map(mapper::orderToOrderDto);
    }

    public void saveOrder(OrderDto orderDto){
        try {
            orderRepository.save(mapper.orderDtoToOrder(orderDto));
        } catch (Exception e) {
            log.info("ERROR :: {}", String.valueOf(e));
        }
    }

    public void updateOrder(OrderDto orderDto){
        var order = orderRepository.findById(orderDto.id()).orElseThrow(() -> new ResourceNotFoundException("Order with provided Id not found"));

        order.setQuantity(orderDto.quantity());
        order.setCollectionDate(orderDto.collectionDate());
        order.setIsPaid(orderDto.isPaid());
        order.setComments(orderDto.comments());

        orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

}
