package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.eggs.dto.EggsDto;
import com.zimaku.zimaku.domain.production.eggs.entity.Eggs;
import org.mapstruct.Mapper;

@Mapper
public interface EggsMapper {

    EggsDto eggsToEggsDto(Eggs eggs);

}
