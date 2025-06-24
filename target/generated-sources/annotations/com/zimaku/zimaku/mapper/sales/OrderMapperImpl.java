package com.zimaku.zimaku.mapper.sales;

import com.zimaku.zimaku.domain.sales.clients.dto.ClientDto;
import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.orders.dto.OrderDto;
import com.zimaku.zimaku.domain.sales.orders.model.Order;
import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.model.Price;
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
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDto.getId() );
        order.quantity( orderDto.getQuantity() );
        order.collectionDate( orderDto.getCollectionDate() );
        order.isPaid( orderDto.getIsPaid() );
        order.comments( orderDto.getComments() );
        order.client( clientDtoToClient( orderDto.getClient() ) );
        order.price( priceDtoToPrice( orderDto.getPrice() ) );

        return order.build();
    }

    @Override
    public OrderDto orderToOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto.OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.date( instantDateMapperFormatter.formatInstant( order.getCreatedDate() ) );
        orderDto.id( order.getId() );
        orderDto.collectionDate( order.getCollectionDate() );
        orderDto.quantity( order.getQuantity() );
        orderDto.isPaid( order.getIsPaid() );
        orderDto.comments( order.getComments() );
        orderDto.client( clientToClientDto( order.getClient() ) );
        orderDto.price( priceToPriceDto( order.getPrice() ) );

        return orderDto.build();
    }

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
    public Price priceDtoToPrice(PriceDto priceDto) {
        if ( priceDto == null ) {
            return null;
        }

        Price.PriceBuilder price = Price.builder();

        price.unitPrice( priceDto.unitPrice() );
        price.currency( priceDto.currency() );

        return price.build();
    }

    @Override
    public PriceDto priceToPriceDto(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDto.PriceDtoBuilder priceDto = PriceDto.builder();

        priceDto.date( instantDateMapperFormatter.formatInstant( price.getCreatedDate() ) );
        priceDto.unitPrice( price.getUnitPrice() );
        priceDto.currency( price.getCurrency() );

        return priceDto.build();
    }

    protected Client clientDtoToClient(ClientDto clientDto) {
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
