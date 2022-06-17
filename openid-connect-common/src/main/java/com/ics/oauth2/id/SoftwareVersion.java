package com.ics.oauth2.id;

public class SoftwareVersion extends IdentifierGenerator{

    public SoftwareVersion(String value) {
        super(value);
    }

    public static SoftwareVersion parse(final String s){
        return new SoftwareVersion(s);
    }

}
