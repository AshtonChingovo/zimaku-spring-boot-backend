package com.zimaku.zimaku.domain.sales.orders.service;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.clients.repository.ClientRepository;
import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.model.Order;
import com.zimaku.zimaku.domain.sales.orders.repository.OrderRepository;
import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.model.Price;
import com.zimaku.zimaku.domain.sales.price.repository.PriceRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.sales.OrderMapper;
import org.hibernate.ResourceClosedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    PriceRepository priceRepository;
    @Mock
    OrderMapper orderMapper;

    @InjectMocks
    OrderService orderService;

    Order order;
    OrderDto orderDtoNullClientID;
    OrderDto orderDtoNotNullClientId;
    Client client;
    ClientDto clientDto;
    ClientDto clientDtoNullId;
    Price price;
    PriceDto priceDto;
    UUID clientId;
    private Pageable pageable;


    @BeforeEach
    void setUp(){

        clientId = UUID.fromString("dfd57724-26bb-4f3a-b783-be3f311dd7c7"); // Use a fixed UUID

        order = Order.builder()
                .id(1L)
                .collectionDate("02/02/2025")
                .quantity(10)
                .isPaid(true)
                .comments("No comments")
                .build();

        orderDtoNullClientID = OrderDto.builder()
                .id(1L)
                .collectionDate("02/02/2025")
                .quantity(10)
                .isPaid(true)
                .comments("No comments")
                .build();

        orderDtoNotNullClientId = OrderDto.builder()
                .id(1L)
                .collectionDate("02/02/2025")
                .quantity(10)
                .isPaid(true)
                .comments("No comments")
                .build();

        client = Client.builder()
                .id(clientId)
                .firstName("John")
                .lastName("Doe")
                .address("John Doe Address")
                .phoneNumber("0777888999")
                .clientType("USER")
                .build();

        clientDto = ClientDto.builder()
                .id(clientId)
                .date("01/02/2021")
                .firstName("John")
                .lastName("Doe")
                .address("John Doe Address")
                .phoneNumber("0777888999")
                .clientType("USER")
                .build();

        clientDtoNullId = ClientDto.builder()
                .id(null)
                .date("01/02/2021")
                .firstName("John")
                .lastName("Doe")
                .address("John Doe Address")
                .phoneNumber("0777888999")
                .clientType("USER")
                .build();

        price = Price.builder()
                .unitPrice(10.0)
                .currency("USD")
                .build();

        orderDtoNullClientID.setClient(clientDtoNullId);

        orderDtoNotNullClientId.setClient(clientDto);

        pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

    }

    @Test
    void testGetOrders_WhenGetOrdersIsCalled_ShouldCallFindOrdersOrFindAll(){

        Page<Order> page = new PageImpl<>(List.of(order));

        when(orderRepository.findOrders(false, pageable)).thenReturn(page);
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(orderDtoNullClientID);

        orderService.getOrders(0, 10, "id", "PENDING");

        verify(orderRepository).findOrders(false, pageable);

    }

    @Test
    void testGetOrders_WhenGetOrdersIsCalledWithoutOrderType_ShouldCallFindAll(){

        Page<Order> page = new PageImpl<>(List.of(order));

        when(orderRepository.findAll( pageable)).thenReturn(page);
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(orderDtoNullClientID);

        orderService.getOrders(0, 10, "id", "");

        verify(orderRepository, times(1)).findAll(pageable);

    }

    @Test
    void testSaveOrder_WhenOrderDtoClientIdIsNull_ShouldCallFindByPhoneNumber(){

        when(clientRepository.findByPhoneNumber(any(String.class))).thenReturn(Optional.of(client));
        when(orderMapper.orderDtoToOrder(any(OrderDto.class))).thenReturn(order);
        when(priceRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(price));
        when(orderMapper.clientToClientDto(any(Client.class))).thenReturn(clientDto);

        orderService.saveOrder(orderDtoNullClientID);

        verify(clientRepository, times(2)).findByPhoneNumber(orderDtoNullClientID.getClient().phoneNumber());
        verify(clientRepository, times(0)).findById(UUID.randomUUID());
    }

    @Test
    void testSaveOrder_WhenOrderDtoClientIdIsNotNull_ShouldCallFindById(){

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(orderMapper.orderDtoToOrder(any(OrderDto.class))).thenReturn(order);
        when(priceRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(price));
        when(orderMapper.clientToClientDto(any(Client.class))).thenReturn(clientDto);

        orderService.saveOrder(orderDtoNotNullClientId);

        verify(clientRepository).findById(clientId);
        verify(clientRepository, times(0)).findByPhoneNumber(orderDtoNullClientID.getClient().phoneNumber());
    }

    @Test
    void testSaveOrder_WhenClientNotFound_ShouldThrowException(){

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.saveOrder(orderDtoNotNullClientId));

    }

    @Test
    void testSaveOrder_WhenPriceNotFound_ShouldThrowException(){

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(priceRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.saveOrder(orderDtoNotNullClientId));

    }

    @Test
    void testSaveThenGetNewWalkInClient_WhenClientPhoneNumberNotFound_ShouldThrowException(){

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientRepository.findByPhoneNumber(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.saveThenGetNewWalkInClient(orderDtoNotNullClientId));

    }

    @Test
    void testUpdateOrder_WhenOrderIdNotFound_ShouldThrowException(){

        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.updateOrder(orderDtoNullClientID));
    }

}