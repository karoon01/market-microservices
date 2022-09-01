package com.yosypchuk.user.service.impl;

import com.yosypchuk.user.model.User;
import com.yosypchuk.user.model.message.Event;
import com.yosypchuk.user.model.message.Message;
import com.yosypchuk.user.service.MessageSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageSenderServiceImpl implements MessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(User user, Event event) {
        log.info("User with id: {} send message", user.getId());
        log.info("Send email with event: {}", event);

        Message message = Message.builder()
                .event(event)
                .user(user)
                .build();

        log.info("Send message to email service");
        rabbitTemplate.convertAndSend("emailQueue", message);
    }
}
