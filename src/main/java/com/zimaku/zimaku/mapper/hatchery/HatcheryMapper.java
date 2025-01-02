package com.zimaku.zimaku.mapper.hatchery;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.hatchery.receiveStock.entity.HatcheryStock;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public interface HatcheryMapper {

    public HatcheryStock hatcheryDtoToHatcheryStock(HatcheryStockDto hatcheryStockDto);

    public HatcheryStockDto hatcheryStockToHatcheryDto(HatcheryStock hatcheryStock);

}
