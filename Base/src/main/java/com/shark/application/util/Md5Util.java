package com.shark.application.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String generateMd5(String text) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(text.getBytes());
        byte[] digest = messageDigest.digest();
        String md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return md5;
    }
}
