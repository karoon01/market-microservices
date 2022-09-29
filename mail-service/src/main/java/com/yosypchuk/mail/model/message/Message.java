package com.yosypchuk.mail.model.message;

import com.yosypchuk.mail.model.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Message implements Serializable {
    private Event event;
    private User user;
}
