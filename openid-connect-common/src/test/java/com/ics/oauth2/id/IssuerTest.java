package com.ics.oauth2.id;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssuerTest {

    @Test
    void testIssuerWithValidURI() throws Exception{
        Issuer issuer = new Issuer("https://www.examplesite.com");

        assertTrue(issuer.isValid());
        assertEquals("https://www.examplesite.com",issuer.getValue());

    }

    @Test
    void testIssuerWithInvalidURI() throws Exception{
        Issuer issuer = new Issuer("https://www.examplesite.com");

        assertFalse(issuer.isValid(),"Issuer URI is not valid!");
    }

    @Test
    void testIssuerURIWithQueryParam() throws Exception {
        Issuer issuer = new Issuer("https://www.examplesite.com?id=12");
        assertFalse(issuer.isValid(),"Issuer should not contain the query param!");
    }


    @Test
    void testIssuerURIWithFragment() throws Exception {
        Issuer issuer = new Issuer("https://www.examplesite.com/#/");
        assertFalse(issuer.isValid(),"Issuer should not contain the query param!");
    }

}
