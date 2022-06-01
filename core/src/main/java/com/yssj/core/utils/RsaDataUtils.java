package com.yssj.core.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaDataUtils {

    private static final String rsaPrivateKeyBase64 =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKCpeowzTUUZ01Oq\n" +
            "Hho3hgmFQpT6mDX5VYkXbNFbJIzSh9F8pxA5aXOWSqlkzSo89FIu0JDU7yZHBlQN\n" +
            "Tk/40XB73yBQ2P5w+kHPAgGh3qsAGrF5s1QdMiGYt5ZAXZOt0InpitAGLSiWEtXe\n" +
            "BkSFg5jjrUYzn5OKj8eozy8PS6kdAgMBAAECgYAakQvPpSgQ1h1ox0elBCto5Z1F\n" +
            "704+FpW7TqFS2SRpGOMWk+s4NpYmY+LCsgVvB5dLA6y/VkaZaMM2IOEjPjy7ft4G\n" +
            "QoDPnfRLN0bw3JwAH0Mj+7EwwYU5K5gQmS1nuSikVDuPwP5mS6p1cau/mm/F0PGx\n" +
            "tp+FY+kBinPEQbhurQJBAMrmcG3YANpZLGTQojMN5g7IJKQCSt0ovgDqHp5KZCHu\n" +
            "z3wYJJX9Rys47mbt1p8yzKF3ev6Zb+KQaNoDRb4/1k8CQQDKtT20lVzFJQNIXIEz\n" +
            "UCBDGSokik7Na2I0G+PwE8dVQdyEpehlPv4F5KxtyJPtjxXNkot9L57cyPrlcYmb\n" +
            "9hrTAkAW1fixZC40ZPTqI9EdjeLmpv/4tYlXes5nq/HmPtkmOIqBnPYBK5Bm/PFY\n" +
            "BEyzfjQpuBjPrtU42qfyYYvn7adhAkANw5S3CFVORZ8dcLqNI2mtZK4J4SJMrHf0\n" +
            "ldg6Wv1z+o7gqutGxKo2+DoFElOJE+glF0hFbdBZqYPvxG/VmTZlAkEAmmnfdxdp\n" +
            "IDHPsU4BFclrEgnljpsCGrDaYeks/amLOMQUNJnxKN7e5QvMeQG+NrcRG0lVtpVv\n" +
            "iIawRhrVF0tCpQ==\n";



    private static final String rsaPublicKeyBase64 =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgqXqMM01FGdNTqh4aN4YJhUKU\n" +
            "+pg1+VWJF2zRWySM0ofRfKcQOWlzlkqpZM0qPPRSLtCQ1O8mRwZUDU5P+NFwe98g\n" +
            "UNj+cPpBzwIBod6rABqxebNUHTIhmLeWQF2TrdCJ6YrQBi0olhLV3gZEhYOY461G\n" +
            "M5+Tio/HqM8vD0upHQIDAQAB\n";


    private static RSAPrivateKey getRSAPrivateKeyBybase64(String base64s) throws Exception{
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec((new BASE64Decoder()).decodeBuffer(base64s));
        RSAPrivateKey privateKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static RSAPublicKey getRSAPublidKeyBybase64(String base64s) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((new BASE64Decoder()).decodeBuffer(base64s));
        RSAPublicKey publicKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static String  rsaDecrypt(String encrypt) {
        try {
            RSAPrivateKey rsaPrivateKey = getRSAPrivateKeyBybase64(rsaPrivateKeyBase64);
            RSAPublicKey rsaPublicKey = getRSAPublidKeyBybase64(rsaPublicKeyBase64);
            AsymmetricCrypto asymmetricCrypto = new AsymmetricCrypto("RSA/ECB/PKCS1Padding", rsaPrivateKey, rsaPublicKey);
            byte[] decypt = asymmetricCrypto.decrypt(encrypt,KeyType.PrivateKey);
            return new String(decypt);
        }catch (Exception e) {
            e.getMessage();
        }
        return null;
    };


    public static String rsaEncrypt(String data) {
        try {
            RSAPrivateKey rsaPrivateKey = getRSAPrivateKeyBybase64(rsaPrivateKeyBase64);
            RSAPublicKey rsaPublicKey = getRSAPublidKeyBybase64(rsaPublicKeyBase64);
            AsymmetricCrypto asymmetricCrypto = new AsymmetricCrypto("RSA", rsaPrivateKey, rsaPublicKey);
            return asymmetricCrypto.encryptBase64(data,KeyType.PublicKey);
        }catch (Exception e) {
            e.getMessage();
        }
        return null;
    };

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("admin").append("|").append("888888").append("|").append("keys");
        System.out.println(rsaEncrypt(sb.toString()));
        System.out.println(SecureUtil.md5("123456"));
    }

}
