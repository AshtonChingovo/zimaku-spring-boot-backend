package com.zimaku.zimaku.mapper.sales;

import com.zimaku.zimaku.domain.sales.price.dto.PriceDto;
import com.zimaku.zimaku.domain.sales.price.model.Price;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T17:25:42+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class PriceMapperImpl implements PriceMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

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
}
