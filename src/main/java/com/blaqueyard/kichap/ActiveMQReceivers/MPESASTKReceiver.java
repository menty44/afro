package com.blaqueyard.kichap.ActiveMQReceivers;//package com.blaqueyard.www.ActiveMQReceivers;

import com.blaqueyard.kichap.controller.ConsoleColors;
import org.springframework.jms.annotation.JmsListener;

import java.io.IOException;

//
//
///**
// * Created by admin on 10/14/18.
// */

///**
// * Fredrick Oluoch
// * http://www.blaqueyard.com
// * 0720106420 | 0722508906
// * email: menty44@gmail.com
// */
//
//@Component
public class MPESASTKReceiver {

    @JmsListener(destination = "MPESASTKQueue", containerFactory = "myFactory")
    public void receiveMpesa(String message) throws IOException {


            System.out.println(ConsoleColors.GREEN_BRIGHT +"Received MPESASTKQueue via !!! ACTIVEMQ !!! <" + message + ">");
            System.out.println(ConsoleColors.RED_BRIGHT + message );

//        Sendsms sendsms = new Sendsms();
//        sendsms.sms(message);
    }


}

