package com.ics.oauth2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScopeTest {

    @Test
    void testCopyConstructor() throws Exception{
        Scope scope = new Scope("read", "write");
        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
        assertEquals(2,scope.size());
    }

    @Test
    void testScopeWithValueConstructor() throws Exception{
        Scope scope = new Scope(new Scope.Value("read"),new Scope.Value("write"));
        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
        assertEquals(2, scope.size());

        String out = scope.toString();

        assertEquals("read write", out);

    }

    @Test
    void testValidScope() throws Exception{
        Scope scope = new Scope();

        scope.add(new Scope.Value("read"));
        scope.add(new Scope.Value("write"));

        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
        assertEquals(2, scope.size());
        assertFalse(scope.contains(new Scope.Value("no-such-value")));
        assertFalse(scope.contains("no-such-value"));

        String out = scope.toString();

        assertEquals("read write", out);
    }

    @Test
    void testParseString() throws Exception{
        Scope scope = Scope.parse("read write");

        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
    }

    @Test
    void testParseCollection() throws Exception{
        Scope scope = Scope.parse(Arrays.asList("read","write"));

        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
    }

}
