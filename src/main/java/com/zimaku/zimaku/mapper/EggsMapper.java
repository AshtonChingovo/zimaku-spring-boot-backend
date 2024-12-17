package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class)
public interface EggsMapper {

    EggsDto eggsToEggsDto(Eggs eggs);

    Eggs eggsDtoToEggs(EggsDto eggsDto);

}
