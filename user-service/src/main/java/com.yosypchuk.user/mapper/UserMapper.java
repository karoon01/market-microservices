package com.yosypchuk.user.mapper;

import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUser(UserDTO userDTO);
    UserDTO mapUserDto(User user);
}
