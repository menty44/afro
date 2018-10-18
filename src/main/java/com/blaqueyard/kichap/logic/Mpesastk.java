//package com.blaqueyard.kichap.logic;//package com.blaqueyard.www.logic;
//
//
/////**
//// * Created by admin on 10/15/18.
//// */
//
////import java.io.*;
////import java.net.HttpURLConnection;
////import java.net.URL;
////import java.text.SimpleDateFormat;
////import java.util.Base64;
////import java.util.Date;
////
/////**
//// * Fredrick Oluoch
//// * http://www.blaqueyard.com
//// * 0720106420 | 0722508906
//// * email: menty44@gmail.com
//// */
//
//import com.blaqueyard.kichap.controller.Auth;
//import com.blaqueyard.kichap.model.MpesastkPush;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.text.SimpleDateFormat;
//import java.util.Base64;
//
//public class Mpesastk {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    public String stk(String message) throws IOException {
//
//        Auth a = new Auth("GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V", "oOpJICRVlyrGSAkM");
//
//        a.authenticate();
//
//        String url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // Setting basic post request
//        con.setRequestMethod("POST");
//        con.setRequestProperty("User-Agent", "USER_AGENT");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("authorization", "Bearer " + a.authenticate());
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
//
//        String dateString = format.format(new Date());
//        System.out.println(dateString);
//
//        String pass="174379"+"bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"+dateString;
//        byte[] bytes = new byte[0];
//        try {
//            bytes = pass.getBytes("ISO-8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String password = Base64.getEncoder().encodeToString(bytes);
//
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("BusinessShortCode", "174379");
////        jsonObject.put("Password", "MTc0Mzc5YmZiMjc5ZjlhYTliZGJjZjE1OGU5N2RkNzFhNDY3Y2QyZTBjODkzMDU5YjEwZjc4ZTZiNzJhZGExZWQyYzkxOTIwMTgwNzAzMDYyMDIy");
//        jsonObject.put("Password", password);
//        jsonObject.put("Timestamp", dateString);
////        jsonObject.put("Timestamp", "");
//        jsonObject.put("TransactionType", "CustomerPayBillOnline");
//        jsonObject.put("Amount", amount);
//        jsonObject.put("PhoneNumber", "254720106420");
//        jsonObject.put("PartyA", number);
//        jsonObject.put("PartyB", "174379");
//        jsonObject.put("CallBackURL", "https://peternjeru.co.ke/safdaraja/api/callback.php");
//        jsonObject.put("AccountReference", "fredLTD");
//        //jsonObject.put("QueueTimeOutURL", "tp://51.15.242.122:8310/c2b/confirmation");
//        jsonObject.put("TransactionDesc", "fredLTD");
//
//        jsonArray.add(jsonObject);
//
//        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(requestJson);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        //System.out.println("\nSending 'POST' request to URL : " + url);
//        //System.out.println("Post Data : " + requestJson);
//        System.out.println(requestJson);
//        //System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer response = new StringBuffer();
//
//        while ((output = in.readLine()) != null) {
//            response.append(output);
//        }
//        in.close();
//
//        System.out.print("###########################################################################################\n");
//        System.out.print("\n");
//        System.out.print("\n");
//        //printing result from response
//        System.out.println(response);
//        System.out.print("###########################################################################################\n");
//        System.out.print("\n");
//        System.out.print("\n");
//        System.out.println(response.toString());
//        System.out.print("###########################################################################################\n");
//        System.out.print("\n");
//        System.out.print("\n");
//
////        try {
//        System.out.println("Reading JSON file from MPESA \n");
//        JSONParser parser = new JSONParser();
//        JSONObject mystring = (JSONObject) parser.parse(response.toString());
//
//        String MerchantRequestID = (String) mystring.get("MerchantRequestID");
//        String CheckoutRequestID = (String) mystring.get("CheckoutRequestID");
//        String ResponseCode = (String) mystring.get("ResponseCode");
//        String ResponseDescription = (String) mystring.get("ResponseDescription");
//        String CustomerMessage = (String) mystring.get("CustomerMessage");
//
//        System.out.print("###################################### START MPESA STK PARSE #####################################################\n");
//        System.out.print("\n");
//        System.out.print("\n");
//        System.out.println("MerchantRequestID: " + MerchantRequestID);
//        System.out.print("\n");
//        System.out.println("CheckoutRequestID: " + CheckoutRequestID);
//        System.out.print("\n");
//        System.out.println("ResponseCode: " + Integer.parseInt(ResponseCode));
//        System.out.print("\n");
//        System.out.println("ResponseDescription: " + ResponseDescription);
//        System.out.print("\n");
//        System.out.println("CustomerMessage: " + CustomerMessage);
//        System.out.print("\n");
//        System.out.print("###################################### END MPESA STK PARSE #####################################################\n");
//
//
//        HttpURLConnection http = (HttpURLConnection) obj.openConnection();
//        int success = Integer.parseInt(ResponseCode);
//        int statusCode = http.getResponseCode();
//        System.out.print("The STATUS CODE "+ statusCode+ "\n");
//
//
//        if(success == 0){
//            MpesastkPush msp = new MpesastkPush();
//
//            msp.setMerchantrequestid(MerchantRequestID);
//            msp.setCheckoutrequestid(CheckoutRequestID);
//            msp.setResponsecode(Integer.parseInt(ResponseCode));
//            msp.setResponsedescription(ResponseDescription);
//            msp.setCustomermessage(CustomerMessage);
//            msp.setUserid(2882);
//
//            msprepo.save(msp);
//
//            return response.toString();
//        }else {
//
//            return "{\"fail\":1}";
//
//        }
//    }
//}
