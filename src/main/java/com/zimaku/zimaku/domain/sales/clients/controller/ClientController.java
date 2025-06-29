package com.zimaku.zimaku.domain.sales.clients.controller;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.service.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientServiceImpl clientServiceImpl;

    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> getClients(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(clientServiceImpl.getClients(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientDto clientDto){
        clientServiceImpl.saveClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDto clientDto){
        clientServiceImpl.putClient(clientDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteClient(@Valid @RequestBody ClientDto clientDto){
        clientServiceImpl.deleteClient(clientDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
