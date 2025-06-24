package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
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
public class DispatchMapperImpl extends DispatchMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public DispatchDto dispatchToDispatchDto(Dispatch dispatch) {
        if ( dispatch == null ) {
            return null;
        }

        DispatchDto.DispatchDtoBuilder dispatchDto = DispatchDto.builder();

        dispatchDto.date( instantDateMapperFormatter.formatInstant( dispatch.getCreatedDate() ) );
        dispatchDto.eggsStockId( dispatchEggsStockId( dispatch ) );
        dispatchDto.id( dispatch.getId() );
        dispatchDto.quantity( dispatch.getQuantity() );
        dispatchDto.batchNumber( dispatch.getBatchNumber() );
        dispatchDto.totalStockReceived( dispatch.getTotalStockReceived() );
        dispatchDto.dateStockReceived( dispatch.getDateStockReceived() );

        DispatchDto dispatchDtoResult = dispatchDto.build();

        convertDateToAge( dispatchDtoResult );

        return dispatchDtoResult;
    }

    @Override
    public Dispatch dispatchDtoToDispatch(DispatchDto dispatchDto) {
        if ( dispatchDto == null ) {
            return null;
        }

        Dispatch.DispatchBuilder dispatch = Dispatch.builder();

        dispatch.id( dispatchDto.getId() );
        dispatch.quantity( dispatchDto.getQuantity() );
        dispatch.batchNumber( dispatchDto.getBatchNumber() );
        dispatch.totalStockReceived( dispatchDto.getTotalStockReceived() );
        dispatch.dateStockReceived( dispatchDto.getDateStockReceived() );

        return dispatch.build();
    }

    private Long dispatchEggsStockId(Dispatch dispatch) {
        EggsStock eggsStock = dispatch.getEggsStock();
        if ( eggsStock == null ) {
            return null;
        }
        return eggsStock.getId();
    }
}
