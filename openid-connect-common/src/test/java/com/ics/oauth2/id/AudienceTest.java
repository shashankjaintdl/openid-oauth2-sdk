package com.ics.oauth2.id;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AudienceTest {

    @Test
    void testAudience() throws Exception{
        Audience audience = new Audience("client-example");
        Assertions.assertEquals("client-example",audience.getValue());
    }



}
