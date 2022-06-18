package com.ics.oauth2.client;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ics.oauth2.ClientType;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.ResponseType;
import com.ics.oauth2.Scope;
import com.ics.oauth2.auth.ClientAuthenticationMethod;
import com.ics.oauth2.exception.ParseException;
import com.ics.oauth2.id.SoftwareID;
import com.ics.oauth2.id.SoftwareVersion;
import com.ics.oauth2.utils.JsonUtils;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.SignedJWT;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ics.oauth2.client.ClientDetailsParameters.*;

public class ClientMetadata {

    public static final Set<String> CLIENT_METADATA_REGISTER_PARAMETERS;

    static {

        CLIENT_METADATA_REGISTER_PARAMETERS = Set.of(
                CLIENT_NAME, CLIENT_TYPE, CLIENT_DESCRIPTION, REDIRECT_URIS, RESPONSE_TYPES, CONTACTS, LOGO_URI, POLICY_URI, JWK_SET,
                JWKS_URI, RESOURCE_IDS, TOKEN_ENDPOINT_AUTH_METHOD, TOKEN_ENDPOINT_AUTH_SIGNING_ALG, SOFTWARE_ID, SOFTWARE_VERSION,
                SOFTWARE_STATEMENT, SCOPE, GRANT_TYPE, REQUEST_OBJECT_SIGNING_ALG, REQUEST_OBJECT_ENCRYPTION_ALG, REQUEST_OBJECT_ENCRYPTION_ENC
        );

    }

    private String clientName;
    private String clientDescription;
    private Set<URI> redirectUris;
    private URI jwksUri;
    private JWKSet jwkSet;
    private ClientAuthenticationMethod tokenEndpointAuthenticationMethod;
    private JWSAlgorithm tokenEndpointAuthSigningAlg;
    private ClientType clientType;
    private Set<String> contacts;
    private URI logoUri;
    private URI policyUri;
    private SoftwareID softwareID;
    private SoftwareVersion softwareVersion;
    private SignedJWT softwareStatement;
    private Set<String> resourceIds;
    private Scope scope;
    private Set<GrantType> grantType;
    private Set<ResponseType> responseTypes;
    private JWSAlgorithm requestObjectSigningAlg;
    private JWEAlgorithm requestObjectEncryptionAlg;
    private EncryptionMethod requestObjectEncryptionEnc;

    public ClientMetadata(){}

    public ClientMetadata(final ClientMetadata metadata){
        this.clientName = metadata.getClientName();
        this.clientDescription = metadata.getClientDescription();
        this.redirectUris = metadata.getRedirectUris();
        this.jwksUri = metadata.getJwksUri();
        this.jwkSet = metadata.getJwkSet();
        this.tokenEndpointAuthenticationMethod = metadata.getTokenEndpointAuthenticationMethod();
        this.tokenEndpointAuthSigningAlg = metadata.getTokenEndpointAuthSigningAlg();
        this.clientType = metadata.getClientType();
        this.contacts = metadata.getContacts();
        this.logoUri = metadata.getLogoUri();
        this.policyUri = metadata.getPolicyUri();
        this.softwareID = metadata.getSoftwareID();
        this.softwareVersion = metadata.getSoftwareVersion();
        this.softwareStatement = metadata.getSoftwareStatement();
        this.resourceIds = metadata.getResourceIds();
        this.grantType = metadata.getGrantType();
        this.scope = metadata.getScope();
        this.responseTypes = metadata.getResponseTypes();
        this.requestObjectEncryptionAlg = metadata.getRequestObjectEncryptionAlg();
        this.requestObjectSigningAlg = metadata.getRequestObjectSigningAlg();
        this.requestObjectEncryptionEnc = metadata.getRequestObjectEncryptionEnc();
    }

    public static Set<String> getClientMetaDataRegisterParameter(){
        return CLIENT_METADATA_REGISTER_PARAMETERS;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public Set<URI> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<URI> redirectUris) {
        if (redirectUris!=null){
            for (URI uri:redirectUris){
                if (uri == null){
                    throw new IllegalArgumentException();
                }
                if (uri.getFragment() != null){
                    throw new IllegalArgumentException();
                }
            }
            this.redirectUris = Collections.unmodifiableSet(redirectUris);
        }
        else{
            this.redirectUris = null;
        }
    }

    public Set<String> getStringRedirectUris(){
        if (getRedirectUris() == null){
            return null;
        }
        return getRedirectUris().stream().map(String::valueOf).collect(Collectors.toSet());
    }

    public ClientAuthenticationMethod getTokenEndpointAuthenticationMethod() {
        return tokenEndpointAuthenticationMethod;
    }

    public void setTokenEndpointAuthenticationMethod(ClientAuthenticationMethod tokenEndpointAuthenticationMethod) {
        this.tokenEndpointAuthenticationMethod = tokenEndpointAuthenticationMethod;
    }

    public URI getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(URI jwksUri) {
        this.jwksUri = jwksUri;
    }

    public JWKSet getJwkSet() {
        return jwkSet;
    }

    public void setJwkSet(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public Set<String> getContacts() {
        return contacts;
    }

    public void setContacts(Set<String> contacts) {
        this.contacts = contacts;
    }

    public URI getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(URI logoUri) {
        this.logoUri = logoUri;
    }

    public URI getPolicyUri() {
        return policyUri;
    }

    public void setPolicyUri(URI policyUri) {
        this.policyUri = policyUri;
    }

    public JWSAlgorithm getTokenEndpointAuthSigningAlg() {
        return tokenEndpointAuthSigningAlg;
    }

    public void setTokenEndpointAuthSigningAlg(JWSAlgorithm tokenEndpointAuthSigningAlg) {
//        if (tokenEndpointAuthSigningAlg.equals(new JWSAlgorithm("none"))){
//            throw new IllegalArgumentException("JWSAlgorithm must not be none !");
//        }
        this.tokenEndpointAuthSigningAlg = tokenEndpointAuthSigningAlg;
    }

    public SoftwareID getSoftwareID() {
        return softwareID;
    }

    public void setSoftwareID(SoftwareID softwareID) {
        this.softwareID = softwareID;
    }

    public SoftwareVersion getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(SoftwareVersion softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public SignedJWT getSoftwareStatement() {
        return softwareStatement;
    }

    public void setSoftwareStatement(SignedJWT softwareStatement) {
        if (softwareStatement != null && softwareStatement.getState().equals(JWSObject.State.UNSIGNED)){
            throw new IllegalStateException("Software statement must be signed !");
        }
        this.softwareStatement = softwareStatement;
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Set<GrantType> getGrantType() {
        return grantType;
    }

    public void setGrantType(Set<GrantType> grantType) {
        this.grantType = grantType;
    }

    public JWSAlgorithm getRequestObjectSigningAlg() {
        return requestObjectSigningAlg;
    }

    public void setRequestObjectSigningAlg(JWSAlgorithm requestObjectSigningAlg) {
        this.requestObjectSigningAlg = requestObjectSigningAlg;
    }

    public JWEAlgorithm getRequestObjectEncryptionAlg() {
        return requestObjectEncryptionAlg;
    }

    public void setRequestObjectEncryptionAlg(JWEAlgorithm requestObjectEncryptionAlg) {
        this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
    }

    public EncryptionMethod getRequestObjectEncryptionEnc() {
        return requestObjectEncryptionEnc;
    }

    public void setRequestObjectEncryptionEnc(EncryptionMethod requestObjectEncryptionEnc) {
        this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
    }

    public Set<ResponseType> getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(Set<ResponseType> responseTypes) {
        this.responseTypes = responseTypes;
    }

    public void applyDefaults(){

        if (responseTypes == null){
            responseTypes = new HashSet<>();
            responseTypes.add(ResponseType.getDefault());
        }

        if (grantType == null){
            grantType = new HashSet<>();
            grantType.add(GrantType.getDefaultGrantType());
        }

        if (tokenEndpointAuthenticationMethod == null){
            if (grantType.contains(GrantType.IMPLICIT) && grantType.size() == 1){
                tokenEndpointAuthenticationMethod = ClientAuthenticationMethod.NONE;
            }
            else{
                tokenEndpointAuthenticationMethod = ClientAuthenticationMethod.getDefaultAuthMethod();
            }
        }


    }

    public static ClientMetadata parse(final String s) throws ParseException {
        JsonElement jsonElement = JsonParser.parseString(s);
        return jsonElement.isJsonObject() ? parse(jsonElement) : null;
    }


    public static ClientMetadata parse(final JsonElement jsonElement) throws ParseException {
        JsonObject o = jsonElement.getAsJsonObject();

        if (o.isJsonNull()){
            throw new IllegalArgumentException("Json must not be null!");
        }

        ClientMetadata c =  new ClientMetadata();

        if (o.get(CLIENT_NAME) != null) {
            c.setClientName(JsonUtils.getAsString(o, CLIENT_NAME));
        }

        if (o.get(CLIENT_TYPE) != null) {
            c.setClientType(ClientType.parse(JsonUtils.getAsString(o, CLIENT_TYPE)));
        }

        c.setRedirectUris(JsonUtils.getAsURISet(o, REDIRECT_URIS));
        c.setClientDescription(JsonUtils.getAsString(o, CLIENT_DESCRIPTION));
        c.setJwksUri(JsonUtils.getAsURI(o, JWKS_URI));
        c.setJwkSet(JsonUtils.getAsJWKSet(o, JWK_SET));
        c.setTokenEndpointAuthenticationMethod(ClientAuthenticationMethod.parse(JsonUtils.getAsString(o, TOKEN_ENDPOINT_AUTH_METHOD)));
        c.setTokenEndpointAuthSigningAlg(JsonUtils.getAsJwsAlgorithm(o, TOKEN_ENDPOINT_AUTH_SIGNING_ALG));
        c.setContacts(JsonUtils.getAsStringSet(o, CONTACTS));
        c.setLogoUri(JsonUtils.getAsURI(o, LOGO_URI));
        c.setPolicyUri(JsonUtils.getAsURI(o, POLICY_URI));

        if(o.get(SOFTWARE_ID) != null) {
            c.setSoftwareID(SoftwareID.parse(JsonUtils.getAsString(o, SOFTWARE_ID)));
        }

        if (o.get(SOFTWARE_VERSION) != null) {
            c.setSoftwareVersion(SoftwareVersion.parse(JsonUtils.getAsString(o, SOFTWARE_VERSION)));
        }
        c.setSoftwareStatement(JsonUtils.getAsSignedJwt(o, SOFTWARE_STATEMENT));
        c.setResourceIds(JsonUtils.getAsStringSet(o, RESOURCE_IDS));
        c.setGrantType(JsonUtils.getAsGrantTypes(o, GRANT_TYPE));
        c.setScope(Scope.parse(JsonUtils.getAsStringList(o, SCOPE)));
        c.setResponseTypes(JsonUtils.getAsResponseTypes(o, RESPONSE_TYPES));
        c.setRequestObjectSigningAlg(JsonUtils.getAsJwsAlgorithm(o, REQUEST_OBJECT_SIGNING_ALG));
        c.setRequestObjectEncryptionAlg(JsonUtils.getAsJweAlgorithm(o, REQUEST_OBJECT_ENCRYPTION_ALG));
        c.setRequestObjectEncryptionEnc(JsonUtils.getEncryptedMethod(o, REQUEST_OBJECT_ENCRYPTION_ENC));
        return c;
    }


    public JsonObject toJsonObject(){

        JsonObject o = new JsonObject();
        if (redirectUris!=null){
            o.add(REDIRECT_URIS, JsonUtils.getAsArray(getStringRedirectUris()));
        }

        if (!Strings.isNullOrEmpty(clientName)){
            o.addProperty(CLIENT_NAME, clientName);
        }

        if (clientType != null){
            o.addProperty(CLIENT_TYPE, clientType.getValue());
        }

        if (!Strings.isNullOrEmpty(clientDescription)){
            o.addProperty(CLIENT_DESCRIPTION, clientDescription);
        }

        if (jwkSet != null){
            o.addProperty(JWK_SET, jwkSet.toPublicJWKSet().toJSONObject().toString());
        }

        if (jwksUri != null){
            o.addProperty(JWKS_URI, jwksUri.toString());
        }

        if (logoUri != null){
            o.addProperty(LOGO_URI, logoUri.toString());
        }

        if (policyUri != null){
            o.addProperty(POLICY_URI, policyUri.toString());
        }

        if (tokenEndpointAuthenticationMethod != null){
            o.addProperty(TOKEN_ENDPOINT_AUTH_METHOD, tokenEndpointAuthenticationMethod.getValue());
        }

        if (tokenEndpointAuthSigningAlg != null){
            o.addProperty(TOKEN_ENDPOINT_AUTH_SIGNING_ALG, tokenEndpointAuthSigningAlg.getName());
        }

        if(contacts != null){
            o.add(CONTACTS, JsonUtils.getAsArray(contacts));
        }

        if (softwareID != null){
            o.addProperty(SOFTWARE_ID, softwareID.getValue());
        }

        if (softwareVersion != null){
            o.addProperty(SOFTWARE_VERSION, softwareVersion.getValue());
        }

        if (softwareStatement != null) {
            o.addProperty(SOFTWARE_STATEMENT, softwareStatement.serialize());
        }

        if (resourceIds != null){
            o.add(RESOURCE_IDS, JsonUtils.getAsArray(resourceIds));
        }

        if (requestObjectSigningAlg != null){
            o.addProperty(REQUEST_OBJECT_SIGNING_ALG, requestObjectSigningAlg.getName());
        }

        if (requestObjectEncryptionAlg != null){
            o.addProperty(REQUEST_OBJECT_ENCRYPTION_ALG, requestObjectEncryptionAlg.getName());
        }

        if (requestObjectEncryptionEnc != null){
            o.addProperty(REQUEST_OBJECT_ENCRYPTION_ENC, requestObjectEncryptionEnc.getName());
        }

        if (responseTypes != null){
           JsonArray jsonArray = new JsonArray();
           for (ResponseType rt:responseTypes){
               jsonArray.add(new ArrayList<>(rt).get(0).getValue());
           }
           o.add(RESPONSE_TYPES, jsonArray);
        }

        if (grantType != null){
            JsonArray jsonArray = new JsonArray();
            for (GrantType gt:grantType){
                jsonArray.add(gt.getValue());
            }
            o.add(GRANT_TYPE, jsonArray);
        }

        if (scope != null){
            JsonArray jsonArray = new JsonArray();
            for (String s:scope.toStringList()){
                jsonArray.add(s);
            }
            o.add(SCOPE, jsonArray);
        }
        return o;
    }


}
