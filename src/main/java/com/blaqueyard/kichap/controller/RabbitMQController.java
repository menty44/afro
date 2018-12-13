package com.blaqueyard.kichap.controller;


/**
 * Created by admin on 11/3/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

import com.blaqueyard.kichap.logic.ApplicationConfigReader;
import com.blaqueyard.kichap.logic.MessageSender;
import com.blaqueyard.kichap.logic.UserDetails;
import com.blaqueyard.kichap.utils.ApplicationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

    private final RabbitTemplate rabbitTemplate;
    private ApplicationConfigReader applicationConfig;
    private MessageSender messageSender;
    public ApplicationConfigReader getApplicationConfig() {
        return applicationConfig;
    }
    @Autowired
    public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
        this.applicationConfig = applicationConfig;
    }
    @Autowired
    public RabbitMQController(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public MessageSender getMessageSender() {
        return messageSender;
    }
    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody UserDetails user) {
        String exchange = getApplicationConfig().getApp1Exchange();
        String routingKey = getApplicationConfig().getApp1RoutingKey();
/* Sending to Message Queue */
        try {
            messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user);
            return new ResponseEntity<String>(ApplicationConstant.IN_QUEUE, HttpStatus.OK);
        } catch (Exception ex) {
//            System.out.println("Exception occurred while sending message to the queue. Exception= {}", ex);

            return new ResponseEntity<>(ApplicationConstant.MESSAGE_QUEUE_SEND_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
