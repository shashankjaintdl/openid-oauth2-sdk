package com.ics.oauth2.id;

import com.google.common.base.Strings;
import com.nimbusds.jose.util.Base64URL;

import java.io.Serializable;
import java.security.SecureRandom;

public class IdentifierGenerator implements Serializable{

    private static final int DEFAULT_BYTE_LENGTH = 32;

    private final String value;

    public IdentifierGenerator(final String value){
        if (Strings.isNullOrEmpty(value)){
            throw new IllegalArgumentException("Value must not be blank!");
        }
        this.value = value;
    }

    public IdentifierGenerator(final int byteLen){

        if (byteLen<1){
            throw new IllegalArgumentException("Byte length must not be equal to zero");
        }

        byte[] val = new byte[byteLen];

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(val);

        this.value = Base64URL.encode(val).toString();
    }

    public IdentifierGenerator(){
        this(DEFAULT_BYTE_LENGTH);
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return getValue()!=null ? getValue().hashCode() : 0;
    }
}
