package com.zimaku.zimaku.mapper.sales;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.model.Order;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public interface OrderMapper extends PriceMapper {

    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "date", source = "order.createdDate")
    OrderDto orderToOrderDto(Order order);

    @Mapping(target = "date", source = "client.createdDate")
    ClientDto clientToClientDto(Client client);

}

