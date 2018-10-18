package com.blaqueyard.kichap.ActiveMQReceivers;


/**
 * Created by admin on 10/14/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import com.blaqueyard.kichap.logic.Sendsms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@Component
public class SMSReceiver {

    @JmsListener(destination = "SMSQueue", containerFactory = "myFactory")
    public void receiveMessage(String message) throws IOException {
        System.out.println(ConsoleColors.GREEN_BRIGHT +"Received text via !!! ACTIVEMQ !!! <" + message + ">" + ConsoleColors.RESET);
        Sendsms sendsms = new Sendsms();
        sendsms.sms(message);
    }


}
