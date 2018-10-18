package com.blaqueyard.kichap.ActiveMQReceivers;


/**
 * Created by admin on 10/14/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import com.blaqueyard.kichap.model.Gender;
import com.blaqueyard.kichap.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@Component
public class OrderTransactionReceiver {
    @Autowired
    private GenderRepository genderRepository;
    @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
    public void receiveMessage(Gender gender) {
        System.out.println(ConsoleColors.YELLOW_UNDERLINED+"Received <" + gender + ">"+ConsoleColors.RESET);
        genderRepository.save(gender);
    }
}
