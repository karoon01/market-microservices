package com.yosypchuk.mail.model.message;

import com.yosypchuk.mail.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private Event event;
    private User user;
}
