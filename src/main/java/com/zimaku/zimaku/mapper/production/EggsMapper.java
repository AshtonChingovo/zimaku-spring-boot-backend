package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class EggsMapper {

    @Mapping(target = "date", source = "eggs.createdDate")
    public abstract EggsDto eggsToEggsDto(Eggs eggs);

    public abstract Eggs eggsDtoToEggs(EggsDto eggsDto);

    @AfterMapping
    void convertDateToDaysAndWeeks(@MappingTarget EggsDto eggsDto) {
        eggsDto.setAge(new DateFormatConverter(eggsDto.getDate()).convert());
    }

}
