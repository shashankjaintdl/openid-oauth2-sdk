package com.ics.oauth2.auth;

import com.google.common.base.Strings;
import com.ics.oauth2.id.IdentifierGenerator;

public final class ClientAuthenticationMethod extends IdentifierGenerator {

    public static final ClientAuthenticationMethod CLIENT_SECRET_BASIC = new ClientAuthenticationMethod("client_secret_basic");

    public static final ClientAuthenticationMethod CLIENT_SECRET_JWT = new ClientAuthenticationMethod("client_secret_jwt");

    public static final ClientAuthenticationMethod CLIENT_SECRET_POST = new ClientAuthenticationMethod("client_secret_post");

    public static final ClientAuthenticationMethod PRIVATE_KEY_JWT = new ClientAuthenticationMethod("private_key_jwt");

    public static final ClientAuthenticationMethod NONE = new ClientAuthenticationMethod("none");

    public ClientAuthenticationMethod(String value) {
        super(value);
    }

    public static ClientAuthenticationMethod getDefaultAuthMethod(){
        return CLIENT_SECRET_BASIC;
    }

    public static ClientAuthenticationMethod parse(final String s){
        if (Strings.isNullOrEmpty(s)){
            return getDefaultAuthMethod();
        }
        else if (s.equals(CLIENT_SECRET_BASIC.getValue())){
            return CLIENT_SECRET_BASIC;
        }
        else if(s.equals(CLIENT_SECRET_JWT.getValue())){
            return CLIENT_SECRET_JWT;
        }
        else if (s.equals(CLIENT_SECRET_POST.getValue())){
            return CLIENT_SECRET_POST;
        }
        else if (s.equals(PRIVATE_KEY_JWT.getValue())) {
            return PRIVATE_KEY_JWT;
        }
        else if (s.equals(NONE.getValue())) {
            return NONE;
        }
        else {
            return new ClientAuthenticationMethod(s);
        }
    }


}
