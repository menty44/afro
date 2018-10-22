package com.blaqueyard.kichap.ActiveMQReceivers;


/**
 * Created by admin on 10/14/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import org.springframework.jms.annotation.JmsListener;
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
public class MAPReceiver {

    @JmsListener(destination = "MAPQueue", containerFactory = "myFactory")
    public void receiveMessage(List message) throws IOException {
        System.out.println(ConsoleColors.GREEN_BRIGHT +"Received text via !!! ACTIVEMQ !!! <>" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED_BACKGROUND +"Data !!! <" + message + ">" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW_BACKGROUND +"Data !!! <" + message.get(1) + ">" + ConsoleColors.RESET);


    }


}
