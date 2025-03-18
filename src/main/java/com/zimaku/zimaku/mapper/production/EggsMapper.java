package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsStockDto;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class EggsMapper {

    @Mapping(target = "date", source = "eggsStock.createdDate")
    public abstract EggsStockDto eggsStockToEggsStockDto(EggsStock eggsStock);

    public abstract EggsStock eggsDtoToEggs(EggsStockDto eggsStockDto);

    @AfterMapping
    void convertDateToDaysAndWeeks(@MappingTarget EggsStockDto eggsStockDto) {
        eggsStockDto.setAge(new DateFormatConverter(eggsStockDto.getDate()).convert());
    }

}
