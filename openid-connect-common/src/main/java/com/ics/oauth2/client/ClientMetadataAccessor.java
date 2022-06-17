package com.ics.oauth2.client;

import com.nimbusds.jose.jwk.JWKSet;

import java.net.URI;
import java.util.Set;

public interface ClientMetadataAccessor   {

    URI getClientUri();

    String getClientName();

    Set<String> getGrantType();

    Set<URI> getRedirectUri();

    Set<String> getResponseType();

    Set<String> getContacts();

    String getJwksUri();

    JWKSet getJwkSet();

    URI getLogoUri();

    URI getPolicyUri();

}