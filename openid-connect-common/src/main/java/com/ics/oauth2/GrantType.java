package com.ics.oauth2;

import com.ics.oauth2.id.IdentifierGenerator;

import java.util.Collections;
import java.util.Set;

public class GrantType extends IdentifierGenerator {

   public static final GrantType AUTHORIZATION_CODE = new GrantType("authorization_code", Set.of("code","redirect_uri"));

   public static final GrantType REFRESH_TOKEN = new GrantType("refresh_token", Collections.singleton("refresh_token"));

   public static final GrantType PASSWORD = new GrantType("password", Set.of("username","password"));

   public static final GrantType CLIENT_CREDENTIAL = new GrantType("client_credential", Collections.emptySet());

   public static final GrantType IMPLICIT = new GrantType("implicit", Collections.emptySet());

   private final Set<String> requestedParameters;

   public GrantType(String value){
       this(value, Collections.emptySet());
   }

   public GrantType(String value, Set<String> requestedParameters){
       super(value);
       this.requestedParameters = requestedParameters;
   }

    public Set<String> getRequestedParameters() {
        return requestedParameters;
    }

    public static GrantType getDefaultGrantType(){
       return AUTHORIZATION_CODE;
    }

    public static GrantType parse(final String s){
       if(s.equals(AUTHORIZATION_CODE.getValue())){
           return AUTHORIZATION_CODE;
       }
       else if(s.equals(REFRESH_TOKEN.getValue())){
           return REFRESH_TOKEN;
       }
       else if(s.equals(PASSWORD.getValue())){
           return PASSWORD;
       }
       else if (s.equals(CLIENT_CREDENTIAL.getValue())) {
           return CLIENT_CREDENTIAL;
       }
       else if (s.equals(IMPLICIT.getValue())) {
           return IMPLICIT;
       }
        return new GrantType(s);
   }


}
