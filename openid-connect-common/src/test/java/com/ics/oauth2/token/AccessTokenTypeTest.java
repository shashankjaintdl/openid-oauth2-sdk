package com.ics.oauth2.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenTypeTest {

    @Test
    void testAccessTokenCopyConstructor() throws Exception{
        AccessTokenType accessTokenType = new AccessTokenType(AccessTokenType.BEARER.getValue());
        assertEquals("Bearer",accessTokenType.getValue());

        AccessTokenType unknown = new AccessTokenType(AccessTokenType.UNKNOWN.getValue());
        assertEquals("unknown",unknown.getValue());

        AccessTokenType none = new AccessTokenType(AccessTokenType.NONE.getValue());
        assertEquals("none",none.getValue());
    }

    @Test
    void testParseAccessTokenType(){

        AccessTokenType tokenType = AccessTokenType.parse("mytokentype");
        assertEquals("mytokentype",tokenType.getValue());
    }
}
