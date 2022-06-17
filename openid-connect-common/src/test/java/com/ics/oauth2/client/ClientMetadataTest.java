package com.ics.oauth2.client;


import com.ics.oauth2.ClientType;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.ResponseType;
import com.ics.oauth2.Scope;
import com.ics.oauth2.auth.ClientAuthenticationMethod;
import com.ics.oauth2.id.SoftwareID;
import com.ics.oauth2.id.SoftwareVersion;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClientMetadataTest {

    @Test
    void testClientMetadataParameters() throws Exception{
        Set<String> parameter = ClientMetadata.getClientMetaDataRegisterParameter();

        assertTrue(parameter.contains("client_name"));
        assertTrue(parameter.contains("client_description"));
        assertTrue(parameter.contains("contacts"));
        assertTrue(parameter.contains("token_endpoint_auth_signing_alg"));
        assertTrue(parameter.contains("response_types"));
        assertTrue(parameter.contains("software_id"));
        assertTrue(parameter.contains("software_version"));
        assertTrue(parameter.contains("software_statement"));
        assertTrue(parameter.contains("resource_ids"));
        assertTrue(parameter.contains("grant_type"));
        assertTrue(parameter.contains("redirect_uris"));
        assertTrue(parameter.contains("client_type"));
        assertTrue(parameter.contains("logo_uri"));
        assertTrue(parameter.contains("jwks_uri"));
        assertTrue(parameter.contains("jwk_set"));
        assertTrue(parameter.contains("scope"));
        assertTrue(parameter.contains("request_object_encryption_alg"));
        assertTrue(parameter.contains("request_object_encryption_enc"));
        assertTrue(parameter.contains("request_object_signing_alg"));
        assertTrue(parameter.contains("token_endpoint_auth_method"));
        assertTrue(parameter.contains("token_endpoint_auth_signing_alg"));

    }


    @Test
    void testValidClientMetadata() throws Exception{
        ClientMetadata metadata = new ClientMetadata();

        String clientName = "name";
        metadata.setClientName(clientName);

        Set<URI> redirectUris = Set.of(new URI("https://www.xyz.com"));
        metadata.setRedirectUris(redirectUris);
        metadata.setClientType(ClientType.CONFIDENTIAL);

        metadata.setTokenEndpointAuthSigningAlg(JWSAlgorithm.ES256);
        metadata.setTokenEndpointAuthenticationMethod(ClientAuthenticationMethod.getDefaultAuthMethod());

        RSAKey key = new RSAKey.Builder(new Base64URL("asbc"),new Base64URL("zyz")).build();
        JWKSet jwkSet = new JWKSet(key);
        metadata.setJwkSet(jwkSet);

        URI jwksUri = new URI("https://www.example.com/jwks.json");
        metadata.setJwksUri(jwksUri);

        String version = "V 1.2";
        metadata.setSoftwareVersion(new SoftwareVersion(version));

        String softwareID = "abcd";
        metadata.setSoftwareID(new SoftwareID(softwareID));

        Set<String> resourceIds = Set.of("RESOURCE_1","RESOURCE_2");
        metadata.setResourceIds(resourceIds);

        Set<ResponseType> responseTypes = Set.of(ResponseType.CODE, ResponseType.TOKEN);
        metadata.setResponseTypes(responseTypes);

        Set<GrantType> grantTypes = Set.of(GrantType.getDefaultGrantType(), GrantType.PASSWORD);
        metadata.setGrantType(grantTypes);

        metadata.setRequestObjectEncryptionAlg(JWEAlgorithm.ECDH_ES);
        metadata.setRequestObjectSigningAlg(JWSAlgorithm.ES512);
        metadata.setRequestObjectEncryptionEnc(EncryptionMethod.XC20P);

        Set<Scope> scopes = Set.of(new Scope("read"), new Scope("write"));
        metadata.setScope(scopes);

        assertEquals(clientName, metadata.getClientName());
        assertEquals(redirectUris, metadata.getRedirectUris());
        assertEquals(ClientType.CONFIDENTIAL, metadata.getClientType());
        assertEquals(JWSAlgorithm.ES256, metadata.getTokenEndpointAuthSigningAlg());
        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, metadata.getTokenEndpointAuthenticationMethod());
        assertEquals("asbc",((RSAKey) metadata.getJwkSet().getKeys().get(0)).getModulus().toString());
        assertEquals("zyz",((RSAKey) metadata.getJwkSet().getKeys().get(0)).getPublicExponent().toString());
        assertEquals(1, metadata.getJwkSet().getKeys().size());
        assertEquals(jwksUri, metadata.getJwksUri());
        assertEquals(resourceIds, metadata.getResourceIds());
        assertEquals(grantTypes, metadata.getGrantType());
        assertEquals(2,metadata.getGrantType().size());
        assertEquals(JWEAlgorithm.ECDH_ES, metadata.getRequestObjectEncryptionAlg());
        assertEquals(JWSAlgorithm.ES512, metadata.getRequestObjectSigningAlg());
        assertEquals(EncryptionMethod.XC20P, metadata.getRequestObjectEncryptionEnc());
        assertEquals(softwareID, metadata.getSoftwareID().getValue());
        assertEquals(version, metadata.getSoftwareVersion().getValue());
        assertEquals(responseTypes, metadata.getResponseTypes());
        assertEquals(2, metadata.getResponseTypes().size());

        String json = metadata.toJsonObject().toString();
        metadata = ClientMetadata.parse(json);

        assertEquals(clientName, metadata.getClientName());
        assertEquals(redirectUris, metadata.getRedirectUris());
        assertEquals(responseTypes.size(),metadata.getResponseTypes().size());
        assertEquals(grantTypes, metadata.getGrantType());
        assertEquals(2, metadata.getGrantType().size());
        assertEquals(ClientType.CONFIDENTIAL, metadata.getClientType());
        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, metadata.getTokenEndpointAuthenticationMethod());
        assertEquals(jwksUri, metadata.getJwksUri());
        assertEquals(2,metadata.getScope().size());

    }

    @Test
    void testClientCopyConstructor() throws Exception{
        ClientMetadata metadata = new ClientMetadata();

        String clientName = "name";
        metadata.setClientName(clientName);

        Set<URI> redirectUris = Set.of(new URI("https://www.xyz.com"));
        metadata.setRedirectUris(redirectUris);
        metadata.setClientType(ClientType.CONFIDENTIAL);

        metadata.setTokenEndpointAuthSigningAlg(JWSAlgorithm.ES256);
        metadata.setTokenEndpointAuthenticationMethod(ClientAuthenticationMethod.getDefaultAuthMethod());

        RSAKey key = new RSAKey.Builder(new Base64URL("asbc"),new Base64URL("zyz")).build();
        JWKSet jwkSet = new JWKSet(key);
        metadata.setJwkSet(jwkSet);

        URI jwksUri = new URI("https://www.example.com/jwks.json");
        metadata.setJwksUri(jwksUri);

        String version = "V 1.2";
        metadata.setSoftwareVersion(new SoftwareVersion(version));

        String softwareID = "abcd";
        metadata.setSoftwareID(new SoftwareID(softwareID));

        Set<String> resourceIds = Set.of("RESOURCE_1","RESOURCE_2");
        metadata.setResourceIds(resourceIds);

        Set<ResponseType> responseTypes = Set.of(ResponseType.CODE, ResponseType.TOKEN);
        metadata.setResponseTypes(responseTypes);

        Set<GrantType> grantTypes = Set.of(GrantType.getDefaultGrantType(), GrantType.PASSWORD);
        metadata.setGrantType(grantTypes);

        metadata.setRequestObjectEncryptionAlg(JWEAlgorithm.ECDH_ES);
        metadata.setRequestObjectSigningAlg(JWSAlgorithm.ES512);
        metadata.setRequestObjectEncryptionEnc(EncryptionMethod.XC20P);

        ClientMetadata copy = new ClientMetadata(metadata);

        assertEquals(redirectUris, copy.getRedirectUris());
        assertEquals(clientName, copy.getClientName());
        assertEquals(ClientType.CONFIDENTIAL, copy.getClientType());
        assertEquals(JWSAlgorithm.ES256, copy.getTokenEndpointAuthSigningAlg());
        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, copy.getTokenEndpointAuthenticationMethod());
        assertEquals(softwareID, copy.getSoftwareID().getValue());
        assertEquals(version, copy.getSoftwareVersion().getValue());
        assertEquals(jwksUri, copy.getJwksUri());
        assertEquals(resourceIds, copy.getResourceIds());
        assertEquals(responseTypes, copy.getResponseTypes());
        assertEquals(2, copy.getResponseTypes().size());
        assertEquals(grantTypes, copy.getGrantType());
        assertEquals(JWEAlgorithm.ECDH_ES, copy.getRequestObjectEncryptionAlg());
        assertEquals(JWSAlgorithm.ES512, copy.getRequestObjectSigningAlg());
        assertEquals(EncryptionMethod.XC20P, copy.getRequestObjectEncryptionEnc());

    }

    @Test
    void testClientWithNullRedirectUri() throws Exception{
        ClientMetadata metadata = new ClientMetadata();
        metadata.setRedirectUris(null);

        assertNull(metadata.getRedirectUris());
        assertNull(metadata.getStringRedirectUris());

        Set<URI> uris = Set.of(new URI("https://www.example.com"));
        metadata.setRedirectUris(uris);

        assertEquals(uris, metadata.getRedirectUris());

    }

    @Test
    void testClientWithInvalidRedirectUri() throws Exception{
        ClientMetadata metadata = new ClientMetadata();

        Set<URI> uris = Set.of(new URI("https://www.example.com/callback/#/"));

        assertThrows(IllegalArgumentException.class, ()->metadata.setRedirectUris(uris));
    }

    @Test
    void testParse() throws Exception{

        String json = "{"
                +"\"client_name\": \"client example\","+"\n"
                +"\"client_type\": \"confidential\","+"\n"
                +"\"jwks_uri\": \"https://www.example.com/jwks.json\","+"\n"
                +"\"redirect_uris\": [\"https://www.example.com/callback2\",\"https://www.example2.com/callback3\"],"+"\n"
                +"\"token_endpoint_auth_method\": \"client_secret_basic\""+"\n"
                +"}";

        ClientMetadata metadata = ClientMetadata.parse(json);

        assertEquals("client example",metadata.getClientName());
        assertEquals(ClientType.CONFIDENTIAL, metadata.getClientType());
        assertEquals("https://www.example.com/jwks.json", metadata.getJwksUri().toString());
        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, metadata.getTokenEndpointAuthenticationMethod());
        assertEquals(2, metadata.getRedirectUris().size());
        assertTrue(metadata.getRedirectUris().contains(new URI("https://www.example.com/callback2")));

    }

    @Test
    void testParseWithInvalidRedirectUris() throws Exception{
        String json = "{"
                +"\"client_name\": \"client example\","+"\n"
                +"\"client_type\": \"confidential\","+"\n"
                +"\"jwks_uri\": \"https://www.example.com/jwks.json\","+"\n"
                +"\"redirect_uris\": [\"https://www.example.com/callback2/#/\",\"https://www.example2.com/callback3//\"],"+"\n"
                +"\"token_endpoint_auth_method\": \"client_secret_basic\""+"\n"
                +"}";

        assertThrows(IllegalArgumentException.class,()->ClientMetadata.parse(json));

    }

    @Test
    void testDefaultMetadata() throws Exception{
        ClientMetadata clientMetadata = new ClientMetadata();
        clientMetadata.applyDefaults();

        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, clientMetadata.getTokenEndpointAuthenticationMethod());
        assertEquals(1,clientMetadata.getGrantType().size());
        assertEquals(1,clientMetadata.getResponseTypes().size());
    }



}
