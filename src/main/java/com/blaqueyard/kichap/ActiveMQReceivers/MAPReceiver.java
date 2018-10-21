package com.blaqueyard.kichap.ActiveMQReceivers;


/**
 * Created by admin on 10/14/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
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
public class MAPReceiver {

    @JmsListener(destination = "MAPQueue", containerFactory = "myFactory")
    public void receiveMessage(String message) throws IOException {
        System.out.println(ConsoleColors.GREEN_BRIGHT +"Received text via !!! ACTIVEMQ !!! <>" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN_BRIGHT +"Data !!! <" + message + ">" + ConsoleColors.RESET);


    }


}
