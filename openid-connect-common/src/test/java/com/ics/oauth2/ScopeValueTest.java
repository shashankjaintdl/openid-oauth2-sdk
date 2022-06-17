package com.ics.oauth2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScopeValueTest {

    @Test
    void testScopeValue() throws Exception{
        Scope.Value value = new Scope.Value("read", Scope.Value.Requirement.REQUIRED);
        assertEquals("read",value.getValue());
        assertEquals(Scope.Value.Requirement.REQUIRED, value.getRequirement());
    }

    @Test
    void testScopeValueParse(){
        Scope.Value value = new Scope.Value("read write", Scope.Value.Requirement.OPTIONAL);
        assertEquals("read write",value.getValue());
        assertEquals(Scope.Value.Requirement.OPTIONAL, value.getRequirement());
        Scope scope = Scope.parse(value.getValue());
        assertTrue(scope.contains("read"));
        assertTrue(scope.contains("write"));
    }


}
