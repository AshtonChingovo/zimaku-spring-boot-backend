package com.zimaku.zimaku.mapper.production;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class DispatchMapper {

    @Mapping(target = "date", source = "dispatch.createdDate")
    public abstract DispatchDto dispatchToDispatchDto(Dispatch dispatch);

    public abstract Dispatch dispatchDtoToDispatch(DispatchDto dispatchDto);

    @AfterMapping
    void convertDateToAge(@MappingTarget DispatchDto dispatchDto) {
        dispatchDto.setAgeOnDispatch(new DateToAgeConverter(dispatchDto.getDateStockReceived()).convert());
    }
}
