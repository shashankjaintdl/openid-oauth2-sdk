package com.ics.oauth2.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ics.oauth2.ClientType;
import com.ics.oauth2.exception.ParseException;
import com.ics.oauth2.id.ClientID;
import com.ics.oauth2.id.ClientSecret;
import com.ics.oauth2.token.AccessToken;

import java.net.URI;
import java.util.Date;
import java.util.Set;

import static com.ics.oauth2.client.ClientDetailsParameters.*;

public class ClientInformation {

    private static final Set<String> REGISTERED_PARAMETER_NAME;

    static {
        REGISTERED_PARAMETER_NAME = Set.of(CLIENT_ID, CLIENT_ID_ISSUED_AT ,CLIENT_SECRET, CLIENT_SECRET_EXPIRES_AT, CLIENT_REGISTERED_URI);
    }

    private final ClientID clientID;
    private final Date issuedAt;
    private final ClientSecret clientSecret;
    private final URI registeredUri;
    private final ClientMetadata metadata;
    private AccessToken accessToken;

    // TODO: Need to add registered client access token

    public ClientInformation(final ClientID clientID, final ClientMetadata metadata){
        this(clientID, metadata, null, null);
    }

    public ClientInformation(final ClientID clientID, final ClientMetadata metadata, final Date issuedAt, final ClientSecret clientSecret){
        this(clientID, metadata, issuedAt, clientSecret, null);
    }

    public ClientInformation(final ClientID clientID, final ClientMetadata metadata, final Date issuedAt, final ClientSecret clientSecret, final URI registeredUri){

        if (clientID == null){
            throw new IllegalArgumentException("Client Id must not be null !");
        }

        if (metadata == null){
            throw new IllegalArgumentException("Client Metadata must not be null !");
        }

        this.clientID = clientID;
        this.metadata = metadata;
        this.issuedAt = issuedAt;
        this.clientSecret = clientSecret;
        this.registeredUri = registeredUri;

    }

    public static Set<String> getRegisteredParameterName(){
        return REGISTERED_PARAMETER_NAME;
    }

    public ClientID getClientID(){
        return clientID;
    }

    public ClientSecret getClientSecret(){
        return clientSecret;
    }

    public Date getIssuedAt(){
        return issuedAt;
    }

    public URI getRegisteredUri(){
        return registeredUri;
    }

    public ClientMetadata getMetadata(){
        return metadata;
    }

    public ClientType applyClientType(){
        return (metadata.getTokenEndpointAuthenticationMethod().getValue() == null &&
                metadata.getJwkSet() == null &&
                metadata.getJwksUri() == null &&
                clientSecret == null) ? ClientType.PUBLIC : ClientType.CONFIDENTIAL;
    }

    public static ClientInformation parse(final String s) throws ParseException {
        JsonElement jsonElement = JsonParser.parseString(s);
        return jsonElement.isJsonObject() ? parse(jsonElement) : null;
    }

    public static ClientInformation parse(final JsonElement jsonElement) throws ParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new ClientInformation(ClientParser.parseClientID(jsonObject),
                ClientMetadata.parse(jsonElement),
                ClientParser.parseClientIDIssuedAt(jsonObject),
                ClientParser.parseClientSecret(jsonObject),
                ClientParser.parseRedirectURI(jsonObject)
        );
    }

    public JsonObject toJsonObject(){

        JsonObject o = metadata.toJsonObject();

        o.addProperty(CLIENT_ID, clientID.getValue());

        if (issuedAt!=null){
            o.addProperty(CLIENT_ID_ISSUED_AT, issuedAt.getTime()/1000);
        }

        if (clientSecret!=null) {
            o.addProperty(CLIENT_SECRET, clientSecret.getValue());
            if (clientSecret.getExpDate()!=null){
                o.addProperty(CLIENT_SECRET_EXPIRES_AT, clientSecret.getExpDate().getTime()/1000);
            }
            else{
                o.addProperty(CLIENT_SECRET_EXPIRES_AT, 0L);
            }
        }

        if (registeredUri!=null){
            o.addProperty(CLIENT_REGISTERED_URI, registeredUri.toString());
        }

        return o;
    }

}
