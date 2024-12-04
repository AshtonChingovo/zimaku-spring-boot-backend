package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;

@Mapper(config = IgnoreUnmappedPropertiesConfig.class)
public interface ChicksMapper {

    ChicksDto chicksToChicksDto(Chicks chicks);

    Chicks chicksDtoToChicks(ChicksDto chicksDto);

}
