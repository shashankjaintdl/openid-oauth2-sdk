package com.ics.oauth2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseTypeTest {

    @Test
    void testResponseTypeCopyConstructor() throws Exception{
        ResponseType responseType = new ResponseType("token","code");
        assertTrue(responseType.contains("code"));
        assertTrue(responseType.contains("token"));
        assertEquals(2, responseType.size());

        ResponseType rt = new ResponseType(ResponseType.Value.TOKEN, ResponseType.Value.CODE);
        assertTrue(rt.contains(ResponseType.Value.CODE));
        assertTrue(rt.contains(ResponseType.Value.TOKEN));

    }

    @Test
    void testParseResponseType() throws Exception{

        ResponseType rt = ResponseType.parse("code token");
        assertTrue(rt.contains(ResponseType.Value.TOKEN.getValue()));
        assertTrue(rt.contains(ResponseType.Value.TOKEN.getValue()));
    }

    @Test
    void testParseCollectionResponseType() throws Exception{
        ResponseType  rt = ResponseType.parse(Arrays.asList("code", "token"));
        assertTrue(rt.contains(ResponseType.Value.TOKEN.getValue()));
        assertTrue(rt.contains(ResponseType.Value.TOKEN.getValue()));
    }




}
