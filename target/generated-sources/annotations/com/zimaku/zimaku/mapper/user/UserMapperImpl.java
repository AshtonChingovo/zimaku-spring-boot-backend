package com.zimaku.zimaku.mapper.user;

import com.zimaku.zimaku.domain.user.dto.UserDto;
import com.zimaku.zimaku.domain.user.entity.Role;
import com.zimaku.zimaku.domain.user.entity.User;
import com.zimaku.zimaku.domain.util.InstantDateMapperFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T17:25:42+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Autowired
    private InstantDateMapperFormatter instantDateMapperFormatter;

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.email( userDto.getEmail() );
        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.phoneNumber( userDto.getPhoneNumber() );
        user.address( userDto.getAddress() );
        user.department( userDto.getDepartment() );
        Collection<Role> collection = userDto.getRoles();
        if ( collection != null ) {
            user.roles( new ArrayList<Role>( collection ) );
        }

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.date( instantDateMapperFormatter.formatInstant( user.getCreatedDate() ) );
        userDto.id( user.getId() );
        userDto.email( user.getEmail() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phoneNumber( user.getPhoneNumber() );
        userDto.address( user.getAddress() );
        userDto.department( user.getDepartment() );
        Collection<Role> collection = user.getRoles();
        if ( collection != null ) {
            userDto.roles( new ArrayList<Role>( collection ) );
        }

        UserDto userDtoResult = userDto.build();

        formatUserRoles( userDtoResult );

        return userDtoResult;
    }
}
