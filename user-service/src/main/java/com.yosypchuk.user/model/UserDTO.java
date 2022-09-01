package com.yosypchuk.user.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDTO {
    private Long id;
    private Role role;

    @NotBlank(message = "${user.first.name.not-blank}")
    private String firstName;
    @NotBlank(message = "${user.last.name.not-blank}")
    private String lastName;

    @NotBlank(message = "${user.email.not-blank}")
    @Email(message = "${user.email.not-valid}")
    private String email;

    @NotBlank(message = "${user.password.not-blank}")
    @Size(min = 6, message = "${user.password.length}")
    private String password;

//    @PhoneNumber(message = "${user.phone.not-valid}")
    private String phoneNumber;
}
