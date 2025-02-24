package com.zimaku.zimaku.domain.sales.orders.service;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
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

import static com.zimaku.zimaku.domain.util.StringUtil.SALE_COMPLETED;
import static com.zimaku.zimaku.domain.util.StringUtil.SALE_PENDING;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final PriceRepository priceRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, PriceRepository priceRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.priceRepository = priceRepository;
        this.orderMapper = orderMapper;
    }

    public Page<OrderDto> getOrders(int pageNumber, int pageSize, String sort, String orderType){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());

        return switch (orderType) {
            case SALE_PENDING -> orderRepository.findOrders(false, page).map(orderMapper::orderToOrderDto);
            case SALE_COMPLETED -> orderRepository.findOrders(true, page).map(orderMapper::orderToOrderDto);
            default -> orderRepository.findAll(page).map(orderMapper::orderToOrderDto);
        };

    }

    public void saveOrder(OrderDto orderDto){

        Client client;

        // null id means this is a walkIn client
        if(orderDto.getClient().id() == null){
            // check if provided phone number is in the DB
            client = clientRepository.findByPhoneNumber(orderDto.getClient().phoneNumber()).orElse(saveAndGetNewWalkInClient(orderDto));
        }
        else{
            client = clientRepository.findById(orderDto.getClient().id()).orElseThrow(() -> new ResourceNotFoundException("Client with requested id not found"));
        }

        var price = priceRepository.findFirstByOrderByIdDesc().orElseThrow(() -> new ResourceNotFoundException("Price/unit has not been set, contact admin"));

        orderDto.setClient(orderMapper.clientToClientDto(client));
        var order = orderMapper.orderDtoToOrder(orderDto);

        order.setClient(client);
        order.setPrice(price);

        orderRepository.save(order);
    }

    public Client saveAndGetNewWalkInClient(OrderDto orderDto){

        clientRepository.save(
                Client.builder()
                        .firstName(orderDto.getClient().firstName())
                        .lastName(orderDto.getClient().lastName())
                        .phoneNumber(orderDto.getClient().phoneNumber())
                        .clientType(orderDto.getClient().clientType())
                        .build()
        );

        return clientRepository.findByPhoneNumber(orderDto.getClient().phoneNumber()).orElseThrow(() -> new ResourceNotFoundException("Client with Phone Number not found"));
    }

    public void updateOrder(OrderDto orderDto){
        var order = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Order with provided Id not found"));

        order.setQuantity(orderDto.getQuantity());
        order.setCollectionDate(orderDto.getCollectionDate());
        order.setIsPaid(orderDto.getIsPaid());
        order.setComments(orderDto.getComments());

        orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

}
