package com.ics.oauth2.id;

import com.google.common.base.Strings;

import java.net.URI;
import java.net.URISyntaxException;

public class Issuer extends IdentifierGenerator {

    public Issuer(final String value) {
        super(value);
    }

    public Issuer(final URI value){
        super(value.toString());
    }

    public Issuer(final IdentifierGenerator value){
        super(value.getValue());
    }

    public static boolean isValid(final String value){
        if (Strings.isNullOrEmpty(value)){
            return false;
        }

        try {
            return isValid(new URI(value));
        }
        catch (URISyntaxException e) {
            return false;
        }

    }

    public static boolean isValid(final URI value){
        if (value == null){
            return false;
        }
        if (value.getScheme()==null || !value.getScheme().equalsIgnoreCase("https")){
            return false;
        }
        if (value.getRawQuery()!=null){
            return false;
        }
        return value.getRawFragment() == null;
    }

    public static boolean isValid(final Issuer value){
        if (value==null){
            return false;
        }
        try{
            return isValid(new URI(value.getValue()));
        }
        catch (URISyntaxException e){
            return false;
        }
    }

    public boolean isValid(){
        return isValid(this);
    }

    public static Issuer parse(final String s){
        if (Strings.isNullOrEmpty(s)){
            return null;
        }
        return new Issuer(s);
    }

}
