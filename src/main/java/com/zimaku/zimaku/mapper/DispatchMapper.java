package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.dispatch.dto.DispatchDto;
import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;

@Mapper(config = IgnoreUnmappedPropertiesConfig.class)
public interface DispatchMapper {

    DispatchDto dispatchToDispatchDto(Dispatch dispatch);

}
