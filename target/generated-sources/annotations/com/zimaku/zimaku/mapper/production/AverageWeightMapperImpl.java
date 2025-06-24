package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.chicks.dto.AverageWeightDto;
import com.zimaku.zimaku.domain.production.chicks.entity.AverageWeight;
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
public class AverageWeightMapperImpl extends AverageWeightMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public AverageWeightDto averageWeightToAverageWeightDto(AverageWeight averageWeight) {
        if ( averageWeight == null ) {
            return null;
        }

        AverageWeightDto.AverageWeightDtoBuilder averageWeightDto = AverageWeightDto.builder();

        averageWeightDto.date( instantDateMapperFormatter.formatInstant( averageWeight.getCreatedDate() ) );
        averageWeightDto.id( averageWeight.getId() );
        averageWeightDto.week( averageWeight.getWeek() );
        averageWeightDto.averageWeight( averageWeight.getAverageWeight() );

        return averageWeightDto.build();
    }

    @Override
    public AverageWeight averageWeightDtoToAverageWeight(AverageWeightDto averageWeightDto) {
        if ( averageWeightDto == null ) {
            return null;
        }

        AverageWeight.AverageWeightBuilder averageWeight = AverageWeight.builder();

        averageWeight.id( averageWeightDto.getId() );
        averageWeight.week( averageWeightDto.getWeek() );
        averageWeight.averageWeight( averageWeightDto.getAverageWeight() );

        return averageWeight.build();
    }
}
