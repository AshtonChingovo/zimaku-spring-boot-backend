package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import org.mapstruct.Mapper;

@Mapper
public interface DispatchMapper {

    DispatchDto dispatchToDispatchDto(Dispatch dispatch);

}
