package com.blaqueyard.kichap.controller;

/**
 * Created by admin on 5/27/18.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
@RequestMapping("/api")
public class EmailController {


    @Autowired
    private JmsTemplate jmsTemplate;

    @CrossOrigin
    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json")
    public void sendmail(@RequestParam(value = "persona") String persona,
                                                       @RequestParam(value = "message") String msg) throws IOException {

        List a1 = new ArrayList();
        a1.add(persona);
        a1.add(msg);

        // Post message to the message queue named "EmailQueue"
        jmsTemplate.convertAndSend("EmailQueue", a1);

//        JmsTemplate.receive("EmailQueue");


//        Sendemail sendemail = new Sendemail();
//        return sendemail.email(persona, msg);

    }

}
