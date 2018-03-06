package com.shark.base.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;

public class RsaUtil {
    public static final String OUTPUT_STRING_BASE64_PRIVATE_KEY = "OUTPUT_STRING_BASE64_PRIVATE_KEY";
    public static final String OUTPUT_STRING_BASE64_PUBLIC_KEY = "OUTPUT_STRING_BASE64_PUBLIC_KEY";

    public static HashMap<String, String> generateKeys() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        HashMap<String, String> keyHashMap = new HashMap<String, String>();
        Base64.Encoder encoder = Base64.getEncoder();

        String base64PrivateKey = encoder.encodeToString(privateKey.getEncoded());
        String base64PublicKey = encoder.encodeToString(publicKey.getEncoded());

        keyHashMap.put(OUTPUT_STRING_BASE64_PRIVATE_KEY, base64PrivateKey);
        keyHashMap.put(OUTPUT_STRING_BASE64_PUBLIC_KEY, base64PublicKey);
        return keyHashMap;
    }

    public static PrivateKey generatePrivateKey(String base64privateKey) {
        Base64.Decoder decoder = Base64.getDecoder();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoder.decode(base64privateKey));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PrivateKey privateKey = null;
        try {
            privateKey = keyFactory.generatePrivate(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey generatePublicKey(String base64PublicKey){
        Base64.Decoder decoder = Base64.getDecoder();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoder.decode(base64PublicKey));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PublicKey publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static String encryptBase64Text(String base64Text, String base64PublicKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PublicKey key = generatePublicKey(base64PublicKey);
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String encryptText = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            encryptText = encoder.encodeToString(cipher.doFinal(base64Text.getBytes()));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    public static String decryptText(String encryptBase64Text, String base64PrivateKey) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            PrivateKey key = generatePrivateKey(base64PrivateKey);
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String text = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            text = new String(cipher.doFinal(decoder.decode(encryptBase64Text)));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return text;
    }
}
