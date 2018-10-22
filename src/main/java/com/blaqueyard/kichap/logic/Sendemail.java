package com.blaqueyard.kichap.logic;


/**
 * Created by admin on 10/22/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import org.springframework.http.ResponseEntity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

public class Sendemail {

    public ResponseEntity<Map<String,String>> email(String persona, String msg) throws IOException {

        Map<String,String> response = new HashMap<String, String>();

        if(persona != null && msg != null){

            final String username = "info@blaqueyard.com";
            final String password = "XZB9O6G8fred";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "mail.blaqueyard.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(persona));
                message.setSubject("IMPORTATNT Oluoch");
                message.setText(msg);

                Transport.send(message);

                System.out.println(ConsoleColors.CYAN_BOLD+"Done"+ConsoleColors.RESET);

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            response.put("mg", "email has been sent");
            response.put("code", "00");
            return ResponseEntity.ok().body(response);

        }else {

            response.put("msg", "some parameters are missing");
            response.put("code", "03");
            return ResponseEntity.ok().body(response);

        }

    }
}
