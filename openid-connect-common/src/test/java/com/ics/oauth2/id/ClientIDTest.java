package com.ics.oauth2.id;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ClientIDTest {


    @Test
    void testDefaultClientID() throws Exception{
        ClientID clientID = new ClientID();
        assertNotNull(clientID.getValue());
    }


    @Test
    void testWithAssignedValue() throws Exception{
        ClientID clientID = new ClientID("client");
        assertEquals("client",clientID.getValue());
    }

    @Test
    void testClientIDWithByte() throws Exception{
        ClientID clientID = new ClientID(32);
        assertNotNull(clientID.getValue());
    }
}
