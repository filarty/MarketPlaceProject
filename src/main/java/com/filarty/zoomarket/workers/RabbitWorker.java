package com.filarty.zoomarket.workers;
import com.filarty.zoomarket.models.RabbitMessageEmail;
import com.filarty.zoomarket.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "EmailSender")
@RequiredArgsConstructor
@Slf4j
public class RabbitWorker {
    private final EmailService emailService;
    @RabbitHandler
    public void sendMimeMessageToEmail(RabbitMessageEmail rabbitMessageEmail) {
        log.info("address: {} body: {}", rabbitMessageEmail.getTo(), rabbitMessageEmail.getBody());
        emailService.sendEmail(rabbitMessageEmail.getTo(), rabbitMessageEmail.getBody());
    }
}
