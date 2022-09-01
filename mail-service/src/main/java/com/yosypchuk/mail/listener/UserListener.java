package com.yosypchuk.mail.listener;

import com.yosypchuk.mail.model.message.Message;
import com.yosypchuk.mail.service.CommunicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserListener {

    private final CommunicationService communicationService;

    @RabbitListener(queues = "emailQueue")
    public void processUser(Message message) throws MessagingException {
        log.info("Get message with event: {} for User with id: {}", message.getEvent(), message.getUser().getId());

        communicationService.sendCommunication(message.getUser(), message.getEvent());

        log.info("Email has been send");
    }
}
