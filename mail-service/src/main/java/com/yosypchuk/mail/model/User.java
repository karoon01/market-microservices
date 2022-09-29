package com.yosypchuk.mail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class User implements Serializable {
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    private String phoneNumber;

    private Role role;
    private Status status;
}
