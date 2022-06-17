package com.ics.oauth2.id;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SoftwareVersionTest {

    @Test
    void testConstructorSoftwareVersion() throws Exception{
        SoftwareVersion version = new SoftwareVersion("V 1.2");
        Assertions.assertNotNull(version);
        Assertions.assertEquals("V 1.2", version.getValue());
    }

    @Test
    void testParseSoftwareVersion() throws Exception{
        SoftwareVersion version = SoftwareVersion.parse("V 1.2");
        Assertions.assertNotNull(version);
        Assertions.assertEquals("V 1.2", version.getValue());
    }
}
