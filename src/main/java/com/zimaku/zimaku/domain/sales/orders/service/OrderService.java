package com.zimaku.zimaku.domain.sales.orders.service;

import com.zimaku.zimaku.domain.sales.clients.repository.ClientRepository;
import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.repository.OrderRepository;
import com.zimaku.zimaku.domain.sales.price.repository.PriceRepository;
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

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final PriceRepository priceRepository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, PriceRepository priceRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }


    public Page<OrderDto> getOrders(int pageNumber, int pageSize, String sort){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort).descending());
        return orderRepository.findAll(page).map(mapper::orderToOrderDto);
    }

    public void saveOrder(OrderDto orderDto){
        var client = clientRepository.findById(orderDto.client().id()).orElseThrow(() -> new ResourceNotFoundException("Client with requested id not found"));
        var price = priceRepository.findFirstByOrderByIdDesc().orElseThrow(() -> new ResourceNotFoundException("Price/unit has not been set, contact admin"));

        var order = mapper.orderDtoToOrder(orderDto);

        order.setClient(client);
        order.setPrice(price);

        orderRepository.save(order);
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
