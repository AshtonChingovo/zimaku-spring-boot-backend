package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.chicks.dto.AverageWeightDto;
import com.zimaku.zimaku.domain.production.chicks.dto.ChicksStockDto;
import com.zimaku.zimaku.domain.production.chicks.entity.AverageWeight;
import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T17:25:41+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ChicksMapperImpl extends ChicksMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public ChicksStockDto chicksToChicksDto(ChicksStock chicksStock) {
        if ( chicksStock == null ) {
            return null;
        }

        ChicksStockDto.ChicksStockDtoBuilder chicksStockDto = ChicksStockDto.builder();

        chicksStockDto.date( instantDateMapperFormatter.formatInstant( chicksStock.getCreatedDate() ) );
        chicksStockDto.id( chicksStock.getId() );
        chicksStockDto.males( chicksStock.getMales() );
        chicksStockDto.females( chicksStock.getFemales() );
        chicksStockDto.fatalities( chicksStock.getFatalities() );
        chicksStockDto.batchNumber( chicksStock.getBatchNumber() );
        chicksStockDto.averageWeight( averageWeightSetToAverageWeightDtoSet( chicksStock.getAverageWeight() ) );

        ChicksStockDto chicksStockDtoResult = chicksStockDto.build();

        convertDateToAge( chicksStockDtoResult );

        return chicksStockDtoResult;
    }

    @Override
    public ChicksStock chicksDtoToChicks(ChicksStockDto chicksStockDto) {
        if ( chicksStockDto == null ) {
            return null;
        }

        ChicksStock.ChicksStockBuilder chicksStock = ChicksStock.builder();

        chicksStock.id( chicksStockDto.getId() );
        chicksStock.males( chicksStockDto.getMales() );
        chicksStock.females( chicksStockDto.getFemales() );
        chicksStock.fatalities( chicksStockDto.getFatalities() );
        chicksStock.batchNumber( chicksStockDto.getBatchNumber() );
        chicksStock.averageWeight( averageWeightDtoSetToAverageWeightSet( chicksStockDto.getAverageWeight() ) );

        return chicksStock.build();
    }

    protected AverageWeightDto averageWeightToAverageWeightDto(AverageWeight averageWeight) {
        if ( averageWeight == null ) {
            return null;
        }

        AverageWeightDto.AverageWeightDtoBuilder averageWeightDto = AverageWeightDto.builder();

        averageWeightDto.id( averageWeight.getId() );
        averageWeightDto.week( averageWeight.getWeek() );
        averageWeightDto.averageWeight( averageWeight.getAverageWeight() );

        return averageWeightDto.build();
    }

    protected Set<AverageWeightDto> averageWeightSetToAverageWeightDtoSet(Set<AverageWeight> set) {
        if ( set == null ) {
            return null;
        }

        Set<AverageWeightDto> set1 = new LinkedHashSet<AverageWeightDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AverageWeight averageWeight : set ) {
            set1.add( averageWeightToAverageWeightDto( averageWeight ) );
        }

        return set1;
    }

    protected AverageWeight averageWeightDtoToAverageWeight(AverageWeightDto averageWeightDto) {
        if ( averageWeightDto == null ) {
            return null;
        }

        AverageWeight.AverageWeightBuilder averageWeight = AverageWeight.builder();

        averageWeight.id( averageWeightDto.getId() );
        averageWeight.week( averageWeightDto.getWeek() );
        averageWeight.averageWeight( averageWeightDto.getAverageWeight() );

        return averageWeight.build();
    }

    protected Set<AverageWeight> averageWeightDtoSetToAverageWeightSet(Set<AverageWeightDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<AverageWeight> set1 = new LinkedHashSet<AverageWeight>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AverageWeightDto averageWeightDto : set ) {
            set1.add( averageWeightDtoToAverageWeight( averageWeightDto ) );
        }

        return set1;
    }
}
