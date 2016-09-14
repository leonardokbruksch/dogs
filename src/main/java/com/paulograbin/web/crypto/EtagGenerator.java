package com.paulograbin.web.crypto;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EtagGenerator {

    MessageDigest md;

    public EtagGenerator() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Couldn't get instance MD5 algorithm");
        }
    }

    public String calculateEtag(String content) {
        if(md == null)
            return null;

        System.out.println("Calculating hash of: " + content);

        md.update(content.getBytes());

        return new String(new BigInteger(1,md.digest()).toString(16));
    }
}
