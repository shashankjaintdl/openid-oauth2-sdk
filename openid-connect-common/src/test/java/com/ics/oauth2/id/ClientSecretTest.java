package com.ics.oauth2.id;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientSecretTest {

    @Test
    void testClientSecretWithNoConstructorParameter() throws Exception {
        ClientSecret clientSecret = new ClientSecret();
        assertNotNull(clientSecret);
        assertNotNull(clientSecret.getValue());
        assertNull(clientSecret.getExpDate());
    }

    @Test
    void testClientSecretWithCustomByteLength() throws Exception {
        ClientSecret clientSecret = new ClientSecret(64, new Date());
        assertNotNull(clientSecret);
        assertNotNull(clientSecret.getValue());
        assertNotNull(clientSecret.getExpDate());
        assertNotNull(clientSecret.getSHA256());
        clientSecret.erase();
        assertNull(clientSecret.getValue());

    }

}

