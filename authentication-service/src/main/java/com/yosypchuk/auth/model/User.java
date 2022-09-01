package com.yosypchuk.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    private String phoneNumber;

    private Role role;
    private Status status;
}
