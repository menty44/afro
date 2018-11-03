package com.blaqueyard.kichap.controller;

/**
 * Created by admin on 5/27/18.
 */

import com.blaqueyard.kichap.utils.Con;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//@RequestMapping("/api")
public class EmailController {


    @Autowired
    private JmsTemplate jmsTemplate;

//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public ResponseEntity<Object> downloadFile() throws IOException  {
//        String filename = "/Users/admin/Documents/mapicha/fred.png";
//        File file = new File(filename);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        ResponseEntity<Object>
//                responseEntity = ResponseEntity.ok().headers(headers).contentLength(
//                file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
//
//        return responseEntity;
//    }
//
//
//    @RequestMapping(value = "/upload", method = RequestMethod.POST,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//
//    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        File convertFile = new File("/Users/admin/Documents/mapicha/"+file.getOriginalFilename());
//        convertFile.createNewFile();
//        FileOutputStream fout = new FileOutputStream(convertFile);
//        fout.write(file.getBytes());
//        fout.close();
//        return "File is upload successfully";
//    }

//    @CrossOrigin
    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json")
    public void sendmail(@RequestParam(value = "persona") String persona,
                                                       @RequestParam(value = "message") String msg) throws IOException {

        List a1 = new ArrayList();
        a1.add(persona);
        a1.add(msg);

        // Post message to the message queue named "EmailQueue"
//        jmsTemplate.convertAndSend("EmailQueue", a1);
        jmsTemplate.convertAndSend(Con.EMAIL_RCV_Q, a1);

//        JmsTemplate.receive("EmailQueue");


//        Sendemail sendemail = new Sendemail();
//        return sendemail.email(persona, msg);

    }

}
