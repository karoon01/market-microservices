package com.yosypchuk.mail.service;

import com.yosypchuk.mail.model.User;
import com.yosypchuk.mail.model.message.Event;

import javax.mail.MessagingException;

public interface CommunicationService {

    void sendCommunication(User user, Event event) throws MessagingException;
}
