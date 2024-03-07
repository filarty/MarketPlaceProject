package com.filarty.zoomarket.services;


import com.filarty.zoomarket.models.RabbitMessageEmail;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender sender;
    private final MailProperties mailProperties;
    private final MimeMessageHelper helper;
    private final MimeMessage message;

    @Autowired
    @SneakyThrows
    EmailService (JavaMailSender sender, MailProperties mailProperties) {
        this.sender = sender;
        this.mailProperties = mailProperties;
        this.message = sender.createMimeMessage();
        this.helper = new MimeMessageHelper(this.message, true);
    }

    @SneakyThrows
    public void sendEmail(String to, String body) {
        RabbitMessageEmail email = new RabbitMessageEmail();
        email.setTo(to);
        email.setBody(body);
        helper.setFrom(mailProperties.getUsername());
        helper.setSubject("MarketPlace Registration");
        helper.setTo(email.getTo());
        helper.setText(email.getBody(), true);
        sender.send(message);
    }
}
