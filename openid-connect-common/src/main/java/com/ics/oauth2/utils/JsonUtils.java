package com.ics.oauth2.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.ResponseType;
import com.ics.oauth2.Scope;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.SignedJWT;


import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

public class JsonUtils {


    private static final Gson GSON = new Gson();

    private JsonUtils(){}

    /**
     * create JsonElement object for JsonObject
     */
    public static JsonElement getAsArray(Set<String>  value){

        if (value==null){
            return JsonNull.INSTANCE;
        }
        if (value.isEmpty()){
            return JsonNull.INSTANCE;
        }
        return GSON.toJsonTree(value,new TypeToken<Set<String>>(){}.getType());
    }


    /**
     * Gets the value of the given field as a String, null if it doesn't exist
     */

    public static String getAsString(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()) {
                return jsonElement.getAsString();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a Long, null if it doesn't exist
     */

    public static Long getAsLong(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsLong();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as Integer, null if it doesn't exist
     */

    public static Integer getAsInteger(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsInt();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a Boolean, null if it doesn't exist
     */

    public static Boolean getAsBoolean(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsBoolean();
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Gets the value of the given field as a Date, null if it doesn't exist
     */

    public static Date getAsDate(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return new Date(jsonElement.getAsInt() *  1000L);
            }
            else{
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a set of String, emptySet if it doesn't exist
     */

    public static Set<String> getAsStringSet(JsonObject o, String field) throws JsonSyntaxException{

        if(o.has(field)){
            if(o.get(field).isJsonArray()){
                return GSON.fromJson(o.get(field), new TypeToken<Set<String>>(){}.getType());
            }
            else{
                Sets.newHashSet(o.get(field).getAsString());
            }
        }
        return Collections.emptySet();
    }


    /**
     * Gets the value of the given field as a list of String, emptyList if it doesn't exist
     */

    public static List<String> getAsStringList(JsonObject o, String field) throws JsonSyntaxException {

        if(o.has(field)){
            if(o.get(field).isJsonArray()){
                return GSON.fromJson(o.get(field), new TypeToken<List<String>>(){}.getType());
            }
            else{
                Sets.newHashSet(o.get(field).getAsString());
            }
        }
        return Collections.emptyList();
    }


    /**
     * Gets the value of the given field as a JWSAlgorithm, null if it doesn't exist
     */

    public static JWSAlgorithm getAsJwsAlgorithm(JsonObject o, String field) {
        String s = getAsString(o, field);
        if (s != null) {
            return JWSAlgorithm.parse(s);
        } else {
            return null;
        }
    }

    /**
     * Gets the value of the given field as a JWEAlgorithm, null if it doesn't exist
     */

    public static JWEAlgorithm getAsJweAlgorithm(JsonObject o, String field) {
        String s = getAsString(o, field);
        if (s != null) {
            return JWEAlgorithm.parse(s);
        } else {
            return null;
        }
    }

    /**
     * Gets the value of the given field as a EncryptionMethod, null if it doesn't exist
     */
    
    public static EncryptionMethod getEncryptedMethod(JsonObject o, String field){
        String s = getAsString(o, field);
        if(s!=null){
            return EncryptionMethod.parse(s);
        }
        else{
            return null;
        }
    }

    public static SignedJWT getAsSignedJwt(JsonObject o, String field){
        String s = getAsString(o, field);
        if (s!=null){
            try {
                return SignedJWT.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     * Gets the value of the given field as a JWKSet, null if it doesn't exist
     */

    public static JWKSet getAsJWKSet(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonObject()){
                try {
                    return JWKSet.parse(jsonElement.toString());
                } catch (ParseException e) {
//                    LOGGER.error("Unable to parse JWKSet", e);
                    return null;
                }
            }
        }
        return null;
    }

    public static List<JWSAlgorithm> getAsJwsAlgorithmList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<JWSAlgorithm> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(JWSAlgorithm.parse(s));
            }
            return algorithmList;
        }
        return null;
    }


    public static List<JWEAlgorithm> getAsJweAlgorithmList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<JWEAlgorithm> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(JWEAlgorithm.parse(s));
            }
            return algorithmList;
        }
        return null;
    }

    public static List<EncryptionMethod> getAsEncryptionMethodList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<EncryptionMethod> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(EncryptionMethod.parse(s));
            }
            return algorithmList;
        }
        return null;
    }


    /**
     * Gets the value of the given field as a Date, null if it doesn't exist
     */

    public static URI getAsURI(JsonObject o, String field){
        String s = getAsString(o, field);
        if (!Strings.isNullOrEmpty(s)){
            try {
                return new URI(s);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Set<URI> getAsURISet(JsonObject o, String fields){
        List<String> uriString = getAsStringList(o, fields);
        Set<URI> uris = new HashSet<>();
        for (String s: uriString){
            try {
                uris.add(new URI(s));
            } catch (URISyntaxException e) {
                return Collections.emptySet();
            }
        }
        return uris;
    }

    public static Set<ResponseType> getAsResponseTypes(JsonObject o, String fields) {

        List<String> rts = getAsStringList(o, fields);
        Set<ResponseType> responseTypes = new HashSet<>();
        for (String s:rts){
            try {
                responseTypes.add(ResponseType.parse(s));
            } catch (com.ics.oauth2.exception.ParseException e) {
                return Collections.emptySet();
            }
        }
        return responseTypes;
    }

    public static Set<GrantType> getAsGrantTypes(JsonObject o, String fields) {

        List<String> rts = getAsStringList(o, fields);
        Set<GrantType> grantTypes = new HashSet<>();
        for (String s:rts){
            grantTypes.add(GrantType.parse(s));
        }
        return grantTypes;
    }

    public static Set<Scope> getAsScope(JsonObject o, String fields) {

        List<String> rts = getAsStringList(o, fields);
        Set<Scope> scopes = new HashSet<>();
        for (String s:rts){
            scopes.add(Scope.parse(s));
        }
        return scopes;
    }
}
