package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.chicks.dto.AverageWeightDto;
import com.zimaku.zimaku.domain.production.chicks.entity.AverageWeight;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class AverageWeightMapper {

    @Mapping(target = "date", source = "averageWeight.createdDate")
    public abstract AverageWeightDto averageWeightToAverageWeightDto(AverageWeight averageWeight);

    public abstract AverageWeight averageWeightDtoToAverageWeight(AverageWeightDto averageWeightDto);

}
