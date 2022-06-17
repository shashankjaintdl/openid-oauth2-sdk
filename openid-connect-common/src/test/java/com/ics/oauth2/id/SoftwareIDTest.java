package com.ics.oauth2.id;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoftwareIDTest {

    @Test
    void testSoftwareID() throws Exception{
        SoftwareID id = new SoftwareID();
        assertNotNull(id);
        assertNotNull(id.getValue());
    }

    @Test
    void testSoftwareIDWithConstructor() throws Exception{
        SoftwareID id = new SoftwareID("sId");
        assertNotNull(id);
        assertEquals("sId", id.getValue());
    }
}
