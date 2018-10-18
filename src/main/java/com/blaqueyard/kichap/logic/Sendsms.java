package com.blaqueyard.kichap.logic;


/**
 * Created by admin on 10/15/18.
 */

import com.blaqueyard.kichap.controller.ConsoleColors;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.netty.handler.codec.rtsp.RtspHeaders.Names.USER_AGENT;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

public class Sendsms {

    @Autowired
    RestTemplate restTemplate;

    public String sms(String message) throws IOException {

        String messo = "Thank you for Contacting Blaqueyard";

        String url = "https://api.africastalking.com/restless/send?username=stimapap&Apikey=95ed1013626cf5015ba5a198ffcb2f8cec922e42390883f2c450c02b92e81773&to="+message+"&message="+ URLEncoder.encode(messo, "utf-8");

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result

        System.out.print(ConsoleColors.PURPLE+"###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");
        //printing result from response
        System.out.println(response.toString());
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(ConsoleColors.PURPLE+"###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");

        return response.toString();
    }
}
