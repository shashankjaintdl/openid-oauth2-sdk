package com.ics.oauth2.token;

import com.ics.oauth2.id.IdentifierGenerator;


public class AccessTokenType extends IdentifierGenerator {

    public static final AccessTokenType BEARER = new AccessTokenType("Bearer");

    public static final AccessTokenType UNKNOWN = new AccessTokenType("unknown");

    public static final AccessTokenType NONE = new AccessTokenType("none");

    public AccessTokenType(final String value){
        super(value);
    }

    public static AccessTokenType parse(final String s){
        if (s.equals(BEARER.getValue())){
            return BEARER;
        } else if (s.equals(UNKNOWN.getValue())) {
            return UNKNOWN;
        } else if (s.equals(NONE.getValue())) {
            return NONE;
        }
        return new AccessTokenType(s);
    }

}
