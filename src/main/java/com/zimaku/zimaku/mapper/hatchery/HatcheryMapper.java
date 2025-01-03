package com.zimaku.zimaku.mapper.hatchery;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class HatcheryMapper {

    public abstract HatcheryStock hatcheryDtoToHatcheryStock(HatcheryStockDto hatcheryStockDto);

    @Mapping(target = "date", source = "hatcheryStock.createdDate")
    public abstract HatcheryStockDto hatcheryStockToHatcheryDto(HatcheryStock hatcheryStock);

    @AfterMapping
    void addDifference(@MappingTarget HatcheryStockDto hatcheryStockDto) {
        hatcheryStockDto.setDifference(hatcheryStockDto.getBreakages() - hatcheryStockDto.getTotalDispatched());
    }

}
