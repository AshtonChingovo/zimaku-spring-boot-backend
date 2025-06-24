package com.zimaku.zimaku.mapper.hatchery;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
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
public class HatcheryMapperImpl extends HatcheryMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public HatcheryStock hatcheryDtoToHatcheryStock(HatcheryStockDto hatcheryStockDto) {
        if ( hatcheryStockDto == null ) {
            return null;
        }

        HatcheryStock.HatcheryStockBuilder hatcheryStock = HatcheryStock.builder();

        hatcheryStock.id( hatcheryStockDto.getId() );
        hatcheryStock.breakages( hatcheryStockDto.getBreakages() );
        hatcheryStock.batchNumber( hatcheryStockDto.getBatchNumber() );
        hatcheryStock.totalDispatched( hatcheryStockDto.getTotalDispatched() );

        return hatcheryStock.build();
    }

    @Override
    public HatcheryStockDto hatcheryStockToHatcheryDto(HatcheryStock hatcheryStock) {
        if ( hatcheryStock == null ) {
            return null;
        }

        HatcheryStockDto.HatcheryStockDtoBuilder hatcheryStockDto = HatcheryStockDto.builder();

        hatcheryStockDto.date( instantDateMapperFormatter.formatInstant( hatcheryStock.getCreatedDate() ) );
        hatcheryStockDto.id( hatcheryStock.getId() );
        hatcheryStockDto.breakages( hatcheryStock.getBreakages() );
        hatcheryStockDto.batchNumber( hatcheryStock.getBatchNumber() );
        hatcheryStockDto.totalDispatched( hatcheryStock.getTotalDispatched() );

        HatcheryStockDto hatcheryStockDtoResult = hatcheryStockDto.build();

        addDifference( hatcheryStockDtoResult );

        return hatcheryStockDtoResult;
    }
}
