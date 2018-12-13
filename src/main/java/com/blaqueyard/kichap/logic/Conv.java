package com.blaqueyard.kichap.logic;

import org.apache.commons.net.util.Base64;

import java.io.File;
import java.io.FileInputStream;

public class Conv {
    public static void main(String[] args) throws Exception{

        File f =  new File("/home/user/Desktop/logo.png");
        String encodstring = encodeFileToBase64Binary(f);
        System.out.println(encodstring);
    }

    private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }
}
