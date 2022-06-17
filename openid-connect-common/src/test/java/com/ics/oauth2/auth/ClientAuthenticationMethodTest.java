package com.ics.oauth2.auth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientAuthenticationMethodTest {

    @Test
    void testValidAuthMethod(){
        ClientAuthenticationMethod clientAuthenticationMethod = ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        Assertions.assertEquals(ClientAuthenticationMethod.getDefaultAuthMethod().getValue(), clientAuthenticationMethod.getValue());
    }

    @Test
    void testInvalidMethod(){
        ClientAuthenticationMethod clientAuthenticationMethod = new ClientAuthenticationMethod("unknown");
        Assertions.assertNotEquals(ClientAuthenticationMethod.CLIENT_SECRET_JWT, ClientAuthenticationMethod.parse(clientAuthenticationMethod.getValue()));
    }

}
