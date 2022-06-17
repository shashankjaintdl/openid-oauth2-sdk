package com.ics.oauth2.id;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void testState() throws Exception{
        State state = new State();
        assertNotNull(state);
        assertNotNull(state.getValue());
    }

    @Test
    void testStateConstructorWithString() throws Exception{
        State state = new State("state-value");
        assertEquals("state-value", state.getValue());
        state = State.parse("state-value");
        assertEquals("state-value",state.getValue());
    }

    @Test
    void testStateConstructorWithByte() throws Exception{
        State state = new State(128);
        assertNotNull(state);
        assertNotNull(state.getValue());
    }
}
