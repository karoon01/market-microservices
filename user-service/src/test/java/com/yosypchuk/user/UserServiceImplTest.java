package com.yosypchuk.user;


import com.yosypchuk.user.exception.UserNotFoundException;
import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import com.yosypchuk.user.repository.UserRepository;
import com.yosypchuk.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.util.TestUserDataUtil;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static test.util.TestUserDataUtil.MOCK_EMAIL;
import static test.util.TestUserDataUtil.MOCK_ID;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;


    @Test
    void testGetUserByEmail() {
        //given
        User expectedUser = TestUserDataUtil.createUser();
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn(Optional.of(expectedUser));

        //when
        UserDTO actualUser = userService.getUserByEmail(MOCK_EMAIL);

        //then
        assertThat(actualUser, allOf(
                hasProperty("id", equalTo(expectedUser.getId())),
                hasProperty("firstName", equalTo(expectedUser.getFirstName())),
                hasProperty("lastName", equalTo(expectedUser.getLastName())),
                hasProperty("email", equalTo(expectedUser.getEmail())),
                hasProperty("role", equalTo(expectedUser.getRole()))
        ));
    }

    @Test
    void testGetUserByEmailThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(MOCK_EMAIL));
    }

    @Test
    void testGetUserById() {
        //given
        User expectedUser = TestUserDataUtil.createUser();
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedUser));

        //when
        UserDTO actualUser = userService.getUserById(MOCK_ID);

        //then
        assertThat(actualUser, allOf(
                hasProperty("id", equalTo(expectedUser.getId())),
                hasProperty("firstName", equalTo(expectedUser.getFirstName())),
                hasProperty("lastName", equalTo(expectedUser.getLastName())),
                hasProperty("email", equalTo(expectedUser.getEmail())),
                hasProperty("role", equalTo(expectedUser.getRole()))
        ));
    }

    @Test
    void testGetUserByIdThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(MOCK_ID));
    }

    @Test
    void testGetAllUsers() {
        //given
        User firstUser = TestUserDataUtil.createUser();
        User secondUser = TestUserDataUtil.createSecondUser();
        List<User> userList = List.of(firstUser, secondUser);

        when(userRepository.findAll()).thenReturn(userList);

        //when
        List<UserDTO> users = userService.getAllUsers();

        //then
        assertThat(users, hasSize(2));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testDeleteUser() {
        //given
        User user = TestUserDataUtil.createUser();

        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any());

        //when
        userService.deleteUser(MOCK_ID);

        //then
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUserThrowsExceptionIfUserDoesntExist() {
        //when
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(MOCK_ID));
    }

    @Test
    void testUpdateTest() {
        //given
        User user = TestUserDataUtil.createUser();
        User expectedUser = TestUserDataUtil.createUpdatedUser();
        UserDTO updateBody = TestUserDataUtil.createUpdatedUserDto();

        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(expectedUser);

        //when
        UserDTO updatedUser = userService.updateUser(MOCK_ID, updateBody);

        //then
        assertThat(updatedUser, allOf(
                hasProperty("id", equalTo(expectedUser.getId())),
                hasProperty("firstName", equalTo(expectedUser.getFirstName())),
                hasProperty("lastName", equalTo(expectedUser.getLastName())),
                hasProperty("email", equalTo(expectedUser.getEmail())),
                hasProperty("role", equalTo(expectedUser.getRole()))
        ));
    }

    @Test
    void testUpdateTestThrowsExceptionIfUserDoesntExist() {
        //given
        UserDTO updateBody = TestUserDataUtil.createUpdatedUserDto();

        //when
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(MOCK_ID, updateBody));
    }
}
