package com.spring.expenses.expensestracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;



    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        mailSender.send(simpleMailMessage);
    }
}
