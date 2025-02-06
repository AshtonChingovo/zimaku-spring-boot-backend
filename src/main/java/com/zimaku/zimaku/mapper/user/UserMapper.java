package com.zimaku.zimaku.mapper.user;

import com.zimaku.zimaku.domain.hatchery.receiveStock.dto.HatcheryStockDto;
import com.zimaku.zimaku.domain.user.dto.UserDto;
import com.zimaku.zimaku.domain.user.entity.User;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import com.zimaku.zimaku.mapper.config.IgnoreUnmappedPropertiesConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring", config = IgnoreUnmappedPropertiesConfig.class, uses = InstantDateMapperFormatter.class)
public abstract class UserMapper {

    public abstract User userDtoToUser(UserDto userDto);

    @Mapping(target = "date", source = "user.createdDate")
    public abstract UserDto userToUserDto(User user);

    @AfterMapping
    void formatUserRoles(@MappingTarget UserDto userDto) {
        userDto.getRoles().forEach(role -> role.setTitle(role.getTitle().substring(5)));
    }

}
