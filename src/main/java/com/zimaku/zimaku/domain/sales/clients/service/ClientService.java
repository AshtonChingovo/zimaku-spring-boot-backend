package com.zimaku.zimaku.domain.sales.clients.service;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.clients.repository.ClientRepository;
import com.zimaku.zimaku.exception.ResourceNotFoundException;
import com.zimaku.zimaku.mapper.sales.ClientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private ClientMapper mapper;

    public ClientService(ClientRepository clientRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public void saveClient(ClientDto clientDto){
        clientRepository.save(mapper.clientDtoToClient(clientDto));
    }

    public Page<ClientDto> getClients(Integer pageNumber, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return clientRepository.findAll(paging).map(mapper::clientToClientDto);
    }

    public void putClient(ClientDto clientDto){
        Client client = clientRepository.findById(clientDto.id()).orElseThrow(() -> new ResourceNotFoundException("Could not find client with the requested ID"));

        client.setFirstName(clientDto.firstName());
        client.setLastName(clientDto.lastName());
        client.setAddress(clientDto.address());
        client.setPhoneNumber(clientDto.phoneNumber());
        client.setClientType(clientDto.clientType());

        clientRepository.save(client);
    }

    public void deleteClient(ClientDto clientDto){
        clientRepository.deleteById(clientDto.id());
    }
}
