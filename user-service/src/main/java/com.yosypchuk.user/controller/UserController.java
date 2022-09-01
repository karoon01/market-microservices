package com.yosypchuk.user.controller;

import com.yosypchuk.user.api.UserApi;
import com.yosypchuk.user.mapper.UserMapper;
import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import com.yosypchuk.user.model.message.Event;
import com.yosypchuk.user.service.MessageSenderService;
import com.yosypchuk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final MessageSenderService messageSenderService;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        messageSenderService.sendMessage(UserMapper.INSTANCE.mapUser(user), Event.CREATED);

        return user;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> banUser(Long id) {
        userService.banUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> unbanUser(Long id) {
        userService.unbanUser(id);
        return ResponseEntity.noContent().build();
    }
}
