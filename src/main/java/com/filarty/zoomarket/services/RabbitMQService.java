package com.filarty.zoomarket.services;
import com.filarty.zoomarket.config.RabbitConfig;
import com.filarty.zoomarket.models.RabbitMessageEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;

    public void sendEmail(RabbitMessageEmail rabbitMessageEmail) {
        rabbitTemplate.convertAndSend(rabbitConfig.getQueue().getName(), rabbitMessageEmail);
    }
}
