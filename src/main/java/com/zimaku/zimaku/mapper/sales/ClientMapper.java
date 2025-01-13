package com.zimaku.zimaku.mapper.sales;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class ClientMapper {

    @Mapping(target = "date", source = "client.createdDate")
    public abstract ClientDto clientToClientDto(Client client);

    public abstract Client clientDtoToClient(ClientDto clientDto);

}
