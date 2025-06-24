package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsStockDto;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
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
public class EggsMapperImpl extends EggsMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public EggsStockDto eggsStockToEggsStockDto(EggsStock eggsStock) {
        if ( eggsStock == null ) {
            return null;
        }

        EggsStockDto.EggsStockDtoBuilder eggsStockDto = EggsStockDto.builder();

        eggsStockDto.date( instantDateMapperFormatter.formatInstant( eggsStock.getCreatedDate() ) );
        eggsStockDto.id( eggsStock.getId() );
        eggsStockDto.quantity( eggsStock.getQuantity() );
        eggsStockDto.hatchable( eggsStock.getHatchable() );
        eggsStockDto.rejects( eggsStock.getRejects() );
        eggsStockDto.batchNumber( eggsStock.getBatchNumber() );

        EggsStockDto eggsStockDtoResult = eggsStockDto.build();

        convertDateToDaysAndWeeks( eggsStockDtoResult );

        return eggsStockDtoResult;
    }

    @Override
    public EggsStock eggsDtoToEggs(EggsStockDto eggsStockDto) {
        if ( eggsStockDto == null ) {
            return null;
        }

        EggsStock.EggsStockBuilder eggsStock = EggsStock.builder();

        eggsStock.id( eggsStockDto.getId() );
        eggsStock.quantity( eggsStockDto.getQuantity() );
        eggsStock.hatchable( eggsStockDto.getHatchable() );
        eggsStock.rejects( eggsStockDto.getRejects() );
        eggsStock.batchNumber( eggsStockDto.getBatchNumber() );

        return eggsStock.build();
    }
}
