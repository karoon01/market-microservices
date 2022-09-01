package com.yosypchuk.user.service.impl;

import com.yosypchuk.user.exception.UserAlreadyExistException;
import com.yosypchuk.user.exception.UserNotFoundException;
import com.yosypchuk.user.mapper.UserMapper;
import com.yosypchuk.user.model.Role;
import com.yosypchuk.user.model.Status;
import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import com.yosypchuk.user.repository.UserRepository;
import com.yosypchuk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userBody) {
        String email = userBody.getEmail();

        log.info("Trying to get user with email: {}", email);
        Optional<User> possibleUser = userRepository.findByEmail(email);

        if(possibleUser.isPresent()) {
            log.error("User with email: {} is already exist!", email);
            throw new UserAlreadyExistException("User is already exist!");
        }

        User user = User.builder()
                .firstName(userBody.getFirstName())
                .lastName(userBody.getLastName())
                .email(email)
                .password(userBody.getPassword())
                .role(Role.USER)
                .status(Status.UNBLOCKED)
                .build();

        log.info("Save user to database");
        userRepository.save(user);

        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Get user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        log.info("Trying to get user by email: {} ", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::mapUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Update user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));
        User updatedUser = User.builder()
                .id(id)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(user.getRole())
                .build();

        userRepository.save(updatedUser);

        return UserMapper.INSTANCE.mapUserDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        log.info("Delete user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void banUser(Long id) {
        log.info("Trying to get user by id: {} ", id);
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));

        log.info("Ban user with id: {}", id);
        userRepository.changeUserStatus(id, Status.BLOCKED);
    }

    @Transactional
    @Override
    public void unbanUser(Long id) {
        log.info("Trying to get user by id: {} ", id);
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found!"));

        log.info("Ban user with id: {}", id);
        userRepository.changeUserStatus(id, Status.UNBLOCKED);
    }
}
