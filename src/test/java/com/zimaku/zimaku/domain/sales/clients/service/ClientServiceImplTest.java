package com.zimaku.zimaku.domain.sales.clients.service;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.clients.repository.ClientRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.sales.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    ClientMapper clientMapper;

    @InjectMocks
    ClientService clientService;

    ClientDto clientDto;
    Client client;

    @BeforeEach
    void setUp(){

        client = Client.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .address("John Doe Address")
                .phoneNumber("0777888999")
                .clientType("USER")
                .build();

        clientDto = ClientDto.builder()
                .id(UUID.randomUUID())
                .date("01/02/2021")
                .firstName("John")
                .lastName("Doe")
                .address("John Doe Address")
                .phoneNumber("0777888999")
                .clientType("USER")
                .build();
    }

    @Test
    void testGetClients_WhenGetClients_ShouldReturnPaginatedData(){

        Page<Client> page = new PageImpl<>(List.of(client));

        when(clientRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(clientMapper.clientToClientDto(any(Client.class))).thenReturn(clientDto);

        var result = clientService.getClients(0, 10, "id");

        assertEquals(1, result.getContent().size());

    }

    @Test
    void testPutClient_WhenIdNotFound_ShouldThrowException(){

        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.putClient(clientDto));
    }

}