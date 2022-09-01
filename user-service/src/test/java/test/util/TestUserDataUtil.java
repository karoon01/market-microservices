package test.util;

import com.yosypchuk.user.model.Role;
import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUserDataUtil {
    public static Long MOCK_ID = 1L;
    public static String MOCK_FIRST_NAME = "Bohdan";
    public static String MOCK_LAST_NAME = "Yosypchuk";
    public static String MOCK_EMAIL = "email@gmail.com";
    public static String MOCK_PASSWORD = "12345678";
    public static Role MOCK_ROLE = Role.USER;

    public static Long MOCK_ID_SECOND = 2L;
    public static String MOCK_FIRST_NAME_SECOND = "Yulia";
    public static String MOCK_LAST_NAME_SECOND = "Komendovska";
    public static String MOCK_EMAIL_SECOND = "email1@gmail.com";
    public static String MOCK_PASSWORD_SECOND = "123456789";
    public static Role MOCK_ROLE_SECOND = Role.USER;

    public static User createUser() {
        return User.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME)
                .lastName(MOCK_LAST_NAME)
                .email(MOCK_EMAIL)
                .password(MOCK_PASSWORD)
                .role(MOCK_ROLE)
                .build();
    }

    public static UserDTO createUserDto() {
        return UserDTO.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME)
                .lastName(MOCK_LAST_NAME)
                .email(MOCK_EMAIL)
                .password(MOCK_PASSWORD)
                .role(MOCK_ROLE)
                .build();
    }

    public static User createUpdatedUser() {
        return User.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME_SECOND)
                .lastName(MOCK_LAST_NAME_SECOND)
                .email(MOCK_EMAIL_SECOND)
                .password(MOCK_PASSWORD_SECOND)
                .role(MOCK_ROLE_SECOND)
                .build();
    }

    public static UserDTO createUpdatedUserDto() {
        return UserDTO.builder()
                .id(MOCK_ID)
                .firstName(MOCK_FIRST_NAME_SECOND)
                .lastName(MOCK_LAST_NAME_SECOND)
                .email(MOCK_EMAIL_SECOND)
                .password(MOCK_PASSWORD_SECOND)
                .role(MOCK_ROLE_SECOND)
                .build();
    }

    public static User createSecondUser() {
        return User.builder()
                .id(MOCK_ID_SECOND)
                .firstName(MOCK_FIRST_NAME_SECOND)
                .lastName(MOCK_LAST_NAME_SECOND)
                .email(MOCK_EMAIL_SECOND)
                .password(MOCK_PASSWORD_SECOND)
                .role(MOCK_ROLE_SECOND)
                .build();
    }

    public static UserDTO createSecondUserDto() {
        return UserDTO.builder()
                .id(MOCK_ID_SECOND)
                .firstName(MOCK_FIRST_NAME_SECOND)
                .lastName(MOCK_LAST_NAME_SECOND)
                .email(MOCK_EMAIL_SECOND)
                .password(MOCK_PASSWORD_SECOND)
                .role(MOCK_ROLE_SECOND)
                .build();
    }

}
