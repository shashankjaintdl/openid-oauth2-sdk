package com.ics.oauth2.id;

import com.ics.oauth2.utils.JsonUtils;

import java.util.UUID;

public class SoftwareID extends IdentifierGenerator{

    public SoftwareID(String value) {
        super(value);
    }

    public SoftwareID() {
        super(UUID.randomUUID().toString());
    }

    public static SoftwareID parse(final String s){
        return new SoftwareID(s);
    }
}
