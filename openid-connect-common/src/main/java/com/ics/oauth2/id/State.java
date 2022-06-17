package com.ics.oauth2.id;

import com.google.common.base.Strings;

public class State extends IdentifierGenerator{

    public State(String value) {
        super(value);
    }

    public State(int byteLen) {
        super(byteLen);
    }

    public State() {
        super();
    }

    public static State parse(final String s){
        if (Strings.isNullOrEmpty(s)){
            return null;
        }
        return new State(s);
    }

}
