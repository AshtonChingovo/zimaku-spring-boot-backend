package com.zimaku.zimaku.mapper.sales;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T17:25:41+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl extends ClientMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public ClientDto clientToClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.date( instantDateMapperFormatter.formatInstant( client.getCreatedDate() ) );
        clientDto.id( client.getId() );
        clientDto.firstName( client.getFirstName() );
        clientDto.lastName( client.getLastName() );
        clientDto.address( client.getAddress() );
        clientDto.phoneNumber( client.getPhoneNumber() );
        clientDto.clientType( client.getClientType() );

        return clientDto.build();
    }

    @Override
    public Client clientDtoToClient(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( clientDto.id() );
        client.firstName( clientDto.firstName() );
        client.lastName( clientDto.lastName() );
        client.address( clientDto.address() );
        client.phoneNumber( clientDto.phoneNumber() );
        client.clientType( clientDto.clientType() );

        return client.build();
    }
}
