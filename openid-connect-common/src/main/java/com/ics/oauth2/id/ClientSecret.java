package com.ics.oauth2.id;

import com.nimbusds.jose.util.Base64URL;
import org.bouncycastle.util.Arrays;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

public class ClientSecret implements Serializable {

    private static final int DEFAULT_BYTE_LENGTH = 32;

    private byte[] value;

    private final Date expDate;

    private static final SecureRandom random = new SecureRandom();

    public ClientSecret(){
        this(DEFAULT_BYTE_LENGTH);
    }

    public ClientSecret(final int byteLen){
        this(byteLen, null);
    }

    public ClientSecret(final int byteLen, final Date expDate){
        if(byteLen<1){
            throw new IllegalArgumentException("Byte length must noy be zero or less tha one!");
        }
        byte[] n = new byte[byteLen];
        random.nextBytes(n);

        value = Base64URL.encode(n).toString().getBytes(StandardCharsets.UTF_8);

        this.expDate = expDate;

    }

    public ClientSecret(final String value, final Date expDate){
        this.value = value.getBytes(StandardCharsets.UTF_8);
        this.expDate = expDate;
    }

    public String getValue(){
        if(value==null){
            return null;
        }

        return new String(value,StandardCharsets.UTF_8);
    }

    public byte[] getSHA256(){

        if(value==null){
            return new byte[0];
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getExpDate() {
        return expDate;
    }

    public boolean isExpired(){
        if (value==null){
            return false;
        }
        final Date currentDate = new Date();
        return expDate.before(currentDate);
    }

    public void erase(){
        if (value==null){
            return;
        }
        Arrays.fill(value,(byte) 0);
        value = null;
    }

 }
