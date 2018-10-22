package com.blaqueyard.kichap.ActiveMQReceivers;


/**
 * Created by admin on 10/14/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import com.blaqueyard.kichap.logic.Sendemail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@Component
public class EmailReceiver {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "EmailQueue", containerFactory = "myFactory")
//    public ResponseEntity<Map<String,String>> receiveMessage(List message) throws IOException {
    public void receiveMessage(List message) throws IOException {
        System.out.println(ConsoleColors.GREEN_BRIGHT +"Received text via !!! ACTIVEMQ !!! <>" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BACKGROUND_BRIGHT +"Data !!! <" + message + ">" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN_BACKGROUND +"Data !!! <" + message.get(0) + ">" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW_BACKGROUND +"Data !!! <" + message.get(1) + ">" + ConsoleColors.RESET);

        Sendemail sendemail = new Sendemail();
        sendemail.email(message.get(0).toString(), message.get(1).toString());

    }


}
