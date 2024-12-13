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
@Mapper(componentModel = "spring", uses = InstantDateMapperFormatter.class)
public abstract class ChicksMapper {

    @Mapping(target = "date", source = "chicks.createdDate", dateFormat = "dd-MM-yyyy")
    public abstract ChicksDto chicksToChicksDto(Chicks chicks);

    public abstract Chicks chicksDtoToChicks(ChicksDto chicksDto);

    @AfterMapping
    void convertNameToUpperCase(@MappingTarget ChicksDto chicksDto) {

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(chicksDto.getDate(), inputFormatter);

/*        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(chicksDto.getDate(), outputFormatter);*/

        long daysDifference = ChronoUnit.DAYS.between(date, LocalDate.now());

        var ageWeeks = daysDifference / 7;
        var ageDays = daysDifference % 7;
        var weeksString = ageWeeks == 1 ? "wk" : "wks";

        chicksDto.setAge(String.format("%d %s %d d", ageWeeks, weeksString, ageDays));

    }

}
