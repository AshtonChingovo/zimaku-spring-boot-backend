package com.zimaku.zimaku.mapper;

import com.zimaku.zimaku.domain.production.chicks.dto.ChicksDto;
import com.zimaku.zimaku.domain.production.chicks.entity.Chicks;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class ChicksMapper {

    @Mapping(target = "date", source = "chicks.createdDate")
    public abstract ChicksDto chicksToChicksDto(Chicks chicks);

    public abstract Chicks chicksDtoToChicks(ChicksDto chicksDto);

    @AfterMapping
    void convertDateToAge(@MappingTarget ChicksDto chicksDto) {

        chicksDto.setAge(new DateToAgeConverter(chicksDto.getDate()).convert());

    }

}
