package com.yosypchuk.mail.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(String subject, String text, String from, String[] to) throws MessagingException;
}
