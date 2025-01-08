package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksStockDto;
import com.zimaku.zimaku.domain.production.chicks.entity.ChicksStock;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class ChicksMapper {

    @Mapping(target = "date", source = "chicksStock.createdDate")
    public abstract ChicksStockDto chicksToChicksDto(ChicksStock chicksStock);

    public abstract ChicksStock chicksDtoToChicks(ChicksStockDto chicksStockDto);

    @AfterMapping
    void convertDateToAge(@MappingTarget ChicksStockDto chicksStockDto) {
        chicksStockDto.setAge(new DateFormatConverter(chicksStockDto.getDate()).convert());
    }

}
