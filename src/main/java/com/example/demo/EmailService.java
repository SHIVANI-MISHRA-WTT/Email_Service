package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String fromEmail, String subject, String name, String comments) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo("abhayupadhyay513@gmail.com");
            helper.setSubject(subject);
            helper.setText(comments, true);
            helper.setFrom(fromEmail);
            helper.setReplyTo(fromEmail);

            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", "abhayupadhyay513@gmail.com");
        } catch (MailException | MessagingException e) {
            logger.error("Failed to send email to {}: {}", "abhayupadhyay513@gmail.com", e.getMessage());
        }
    }
}
