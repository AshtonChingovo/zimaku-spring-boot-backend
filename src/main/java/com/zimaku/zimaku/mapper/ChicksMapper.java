package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import org.mapstruct.Mapper;

@Mapper
public interface ChicksMapper {

    ChicksDto chicksToChicksDto(Chicks chicks);

    Chicks chicksDtoToChicks(ChicksDto chicksDto);

}
