package com.ics.oauth2;

import com.google.common.base.Strings;
import com.ics.oauth2.exception.ParseException;

public enum ClientType {

    CONFIDENTIAL("confidential"),

    PUBLIC("public");

    private final String value;

    ClientType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static ClientType parse(final String s) throws ParseException {

        if (Strings.isNullOrEmpty(s)){
            throw new ParseException();
        }

        if (s.equals(ClientType.CONFIDENTIAL.getValue())){
            return CONFIDENTIAL;
        }

        return PUBLIC;

    }

}
