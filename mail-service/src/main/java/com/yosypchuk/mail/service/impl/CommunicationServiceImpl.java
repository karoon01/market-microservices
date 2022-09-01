package com.yosypchuk.mail.service.impl;

import com.yosypchuk.mail.config.TemplatesProperties;
import com.yosypchuk.mail.config.TemplatesProperties.Template;
import com.yosypchuk.mail.model.User;
import com.yosypchuk.mail.model.message.Event;
import com.yosypchuk.mail.service.CommunicationService;
import com.yosypchuk.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunicationServiceImpl implements CommunicationService {

    private final EmailService emailService;
    private final ITemplateEngine templateEngine;
    private final TemplatesProperties templatesProperties;

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";

    @Override
    public void sendCommunication(User user, Event event) throws MessagingException {

        Optional<Template> templateHolder = templatesProperties.getTemplates()
                .stream()
                .filter(t -> t.getEvent().equals(event))
                .findFirst();

        if(templateHolder.isPresent()) {
            Template template = templateHolder.get();

            Context context = buildContext(user);

            String subject = template.getSubject();
            String from = template.getFrom();
            String[] to = { user.getEmail() };

            String html = templateEngine.process(template.getTemplate(), context);

            log.info("Send email with subject: {} to user with email: {}", subject, to);

            emailService.sendEmail(subject, html, from, to);
        } else {
            log.error("Cannot find template with event: {}", event);
        }
    }

    private Context buildContext(User user) {
        Context context = new Context();

        context.setVariable(FIRST_NAME, user.getFirstName());
        context.setVariable(LAST_NAME, user.getLastName());
        context.setVariable(EMAIL, user.getEmail());

        return context;
    }
}
