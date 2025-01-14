package com.zimaku.zimaku.domain.sales.clients.controller;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> getClients(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(clientService.getClients(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientDto clientDto){
        clientService.saveClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDto clientDto){
        clientService.putClient(clientDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteClient(@Valid @RequestBody ClientDto clientDto){
        clientService.deleteClient(clientDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
