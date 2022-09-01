package com.yosypchuk.user.service;

import com.yosypchuk.user.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userBody);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    void banUser(Long id);

    void unbanUser(Long id);
}