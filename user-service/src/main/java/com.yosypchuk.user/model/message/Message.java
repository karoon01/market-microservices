package com.yosypchuk.user.model.message;

import com.yosypchuk.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private Event event;
    private User user;
}
