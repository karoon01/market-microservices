package com.yosypchuk.user.service;

import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.message.Event;

public interface MessageSenderService {
    void sendMessage(User user, Event event);
}
