package com.yosypchuk.user.mapper;

import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-28T19:45:58+0300",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.16.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.firstName( userDTO.getFirstName() );
        user.lastName( userDTO.getLastName() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.phoneNumber( userDTO.getPhoneNumber() );
        user.role( userDTO.getRole() );

        return user.build();
    }

    @Override
    public UserDTO mapUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.role( user.getRole() );
        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.phoneNumber( user.getPhoneNumber() );

        return userDTO.build();
    }
}
