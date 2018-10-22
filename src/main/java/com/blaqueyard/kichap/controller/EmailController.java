package com.blaqueyard.kichap.controller;

/**
 * Created by admin on 5/27/18.
 */

import com.blaqueyard.kichap.logic.Sendemail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
@RequestMapping("/api")
public class EmailController {




    @CrossOrigin
    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String,String>> sendmail(@RequestParam(value = "persona") String persona,
                                                       @RequestParam(value = "message") String msg) throws IOException {

        Sendemail sendemail = new Sendemail();
        return sendemail.email(persona, msg);

    }

}
