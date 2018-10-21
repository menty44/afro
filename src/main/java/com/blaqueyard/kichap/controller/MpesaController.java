package com.blaqueyard.kichap.controller;

/**
 * Created by admin on 7/3/18.
 */


import com.blaqueyard.kichap.model.Gender;
import com.blaqueyard.kichap.model.MpesastkPush;
import com.blaqueyard.kichap.repository.MpesastkpushRepository;
import com.oracle.tools.packager.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.springframework.jms.core.JmsTemplate;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@RestController
public class MpesaController {


    @Autowired
    MpesastkpushRepository msprepo;


    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public void sms(@RequestBody Gender gender) {
        System.out.println("Sending a sms.");
        // Post message to the message queue named "OrderTransactionQueue"
        //jmsTemplate.convertAndSend("SMSQueue", gender);
    }

    @GetMapping("/email")
    public static String email() {
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"Sending a Email.");

        Context context = new Context();
        context.setVariable("title", "Lorem Ipsum");
        context.setVariable("description", "Lorem Lorem Lorem");

//        EmailStatus emailStatus = EmailHtmlSender.send("menty44@gmail.com", "Title of email", "email/template-1", context);

        // Post message to the message queue named "OrderTransactionQueue"
        //jmsTemplate.convertAndSend("SMSQueue", gender);
        return "sent";
    }

    @PostMapping("/sendtx")
    public void send(@RequestBody Gender gender) {

        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"Sending a transaction."+ ConsoleColors.RESET);
        // Post message to the message queue named "OrderTransactionQueue"
        jmsTemplate.convertAndSend("OrderTransactionQueue", gender);
    }

    @PostMapping("/map")
    public void map() {
        Map<String,String> response = new HashMap<String, String>();
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT +"Sending a MAP transaction."+ ConsoleColors.RESET);

        response.put("mg", "pass");
        response.put("code", "0");
        response.put("desc", "activemq test");
        // Post message to the message queue named "OrderTransactionQueue"
        jmsTemplate.convertAndSend("MAPQueue", ResponseEntity.ok().body(response).toString());
    }

    @CrossOrigin
    @RequestMapping(value = "sendtext", method = RequestMethod.GET, produces = "application/json")
    public void sendtext(@RequestParam(value = "number", defaultValue = "not available") String number) throws IOException {

        //System.out.println(ConsoleColors.RED + "RED COLORED" + ConsoleColors.RESET + " NORMAL");
        //Note Don't forget to use the RESET after printing as the effect will remain if it's not cleared

        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Sending a text via !!! ACTIVEMQ !!!" + ConsoleColors.RESET);
        Log.info("Sending a text via !!! ACTIVEMQ !!!");

        // Post message to the message queue named "OrderTransactionQueue"
//        jmsTemplate.convertAndSend("SMSQueue", number );
        jmsTemplate.convertAndSend("SMSQueue", number);

    }

    @PostMapping("/kyctoken")
    public void send() throws IOException {
        //System.out.println("Sending a gender transaction.");
        // Post message to the message queue named "OrderTransactionQueue"
        //jmsTemplate.convertAndSend("OrderTransactionQueue", gender);
        token();
    }





    @CrossOrigin
    @RequestMapping(value = "mpesastk", method = RequestMethod.GET, produces = "application/json")
    public Map getRev(@RequestParam(value = "number", defaultValue = "not available") String number,
                      @RequestParam(value = "amount", defaultValue = "not available") String amount) throws IOException {

        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print(number);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(amount);
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");

        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V", "oOpJICRVlyrGSAkM");

        a.authenticate();

        a.STKPushSimulation(amount, number);

        //String tombaone = a.tombaone();

        //return Collections.singletonMap(tombaone.toString());
        return Collections.singletonMap("", "");
    }


    //test returns
    @CrossOrigin
    @RequestMapping(value = "/mpesaworking", method = RequestMethod.POST, produces = "application/json")
    public String getRevo(@RequestParam(value = "number", defaultValue = "not available") String number,
                          @RequestParam(value = "amount", defaultValue = "not available") String amount) throws IOException, ParseException {

        System.out.println(ConsoleColors.BLUE_BRIGHT+"amount: "+amount+" number: "+number+ConsoleColors.RESET);

        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V", "oOpJICRVlyrGSAkM");

        a.authenticate();

        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "USER_AGENT");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("authorization", "Bearer " + a.authenticate());

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

        String dateString = format.format(new Date());
        System.out.println(dateString);

        String pass="174379"+"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"+dateString;
        byte[] bytes = new byte[0];
        try {
            bytes = pass.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String password = Base64.getEncoder().encodeToString(bytes);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", "174379");
//        jsonObject.put("Password", "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMDYyMDIy");
        jsonObject.put("Password", password);
        jsonObject.put("Timestamp", dateString);
//        jsonObject.put("Timestamp", "");
        jsonObject.put("TransactionType", "CustomerPayBillOnline");
        jsonObject.put("Amount", amount);
        jsonObject.put("PhoneNumber", "254720106420");
        jsonObject.put("PartyA", number);
        jsonObject.put("PartyB", "174379");
        jsonObject.put("CallBackURL", "https://peternjeru.co.ke/safdaraja/api/callback.php");
        jsonObject.put("AccountReference", "fredLTD");
        //jsonObject.put("QueueTimeOutURL", "tp://51.15.242.122:8310/c2b/confirmation");
        jsonObject.put("TransactionDesc", "fredLTD");

        jsonArray.add(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(requestJson);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post Data : " + requestJson);
        System.out.println(requestJson);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");
        //printing result from response
        System.out.println(response);
        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(response.toString());
        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");

//        try {
            System.out.println("Reading JSON file from MPESA \n");
            JSONParser parser = new JSONParser();
            JSONObject mystring = (JSONObject) parser.parse(response.toString());

            String MerchantRequestID = (String) mystring.get("MerchantRequestID");
            String CheckoutRequestID = (String) mystring.get("CheckoutRequestID");
            String ResponseCode = (String) mystring.get("ResponseCode");
            String ResponseDescription = (String) mystring.get("ResponseDescription");
            String CustomerMessage = (String) mystring.get("CustomerMessage");

            System.out.print("###################################### START MPESA STK PARSE #####################################################\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.println("MerchantRequestID: " + MerchantRequestID);
            System.out.print("\n");
            System.out.println("CheckoutRequestID: " + CheckoutRequestID);
            System.out.print("\n");
            System.out.println("ResponseCode: " + Integer.parseInt(ResponseCode));
            System.out.print("\n");
            System.out.println("ResponseDescription: " + ResponseDescription);
            System.out.print("\n");
            System.out.println("CustomerMessage: " + CustomerMessage);
            System.out.print("\n");
            System.out.print("###################################### END MPESA STK PARSE #####################################################\n");


            HttpURLConnection http = (HttpURLConnection) obj.openConnection();
            int success = Integer.parseInt(ResponseCode);
            int statusCode = http.getResponseCode();
            System.out.print("The STATUS CODE "+ statusCode+ "\n");


        if(success == 0){
                MpesastkPush msp = new MpesastkPush();

                msp.setMerchantrequestid(MerchantRequestID);
                msp.setCheckoutrequestid(CheckoutRequestID);
                msp.setResponsecode(Integer.parseInt(ResponseCode));
                msp.setResponsedescription(ResponseDescription);
                msp.setCustomermessage(CustomerMessage);
                msp.setUserid(2882);

                msprepo.save(msp);

                return response.toString();
            }else {

                return "{\"fail\":1}";

            }

//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        return response.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "mpesastkstatus", method = RequestMethod.GET, produces = "application/json")
    public String getRevo(@RequestParam(value = "checkoutRequestID", defaultValue = "not available") String checkoutRequestID) throws IOException {

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("BusinessShortCode", "174379");
        jsonObject.put("Password", "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMDYyMDIy");
        jsonObject.put("Timestamp", "20180703062022");
        jsonObject.put("CheckoutRequestID", checkoutRequestID);

        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V", "oOpJICRVlyrGSAkM");

        a.authenticate();

        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpushquery/v1/query";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "USER_AGENT");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("authorization", "Bearer " + a.authenticate());

        jsonArray.add(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(requestJson);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post Data : " + requestJson);
        System.out.println(requestJson);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");
        //printing result from response
        System.out.println(response);
        System.out.print("###########################################################################################\n");
        System.out.print("\n");
        System.out.print("\n");

        return response.toString();
    }

    public String token() throws IOException {

        String url = "https://api-test.equitybankgroup.com/identity-uat/v1/token";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "USER_AGENT");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("authorization", "Bearer " + a.authenticate());

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("username", "8388784267");
        jsonObject.put("Password", "xw2cqiGlMRzPa8r4vb0UXanPWr0F5BXCc");
        jsonObject.put("grant_type", "password");
        jsonObject.put("merchant", "Apigee001");
        jsonObject.put("service", "KYC");

        jsonArray.add(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(requestJson);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post Data : " + requestJson);
        System.out.println(requestJson);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.print("########################### token ################################## token ##############################\n");
        System.out.print("\n");
        System.out.print("\n");
        //printing result from response
        System.out.println(response);
        System.out.print("########################## token ################################## token ###############################\n");
        System.out.print("\n");
        System.out.print("\n");

        return response.toString();

    }

}