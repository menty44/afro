package com.blaqueyard.kichap.logic;


/**
 * Created by admin on 11/6/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
/**
 * Message sender to send message to queue using exchange.
 */
@Component
public class MessageSender {
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
    /**
     *
     * @param rabbitTemplate
     * @param exchange
     * @param routingKey
     * @param data
     */
    public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data) {
        log.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
        rabbitTemplate.convertAndSend(exchange, routingKey, data);
        log.info("The message has been sent to the queue.");
    }
}

