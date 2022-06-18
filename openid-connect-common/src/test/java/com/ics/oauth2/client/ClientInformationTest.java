package com.ics.oauth2.client;

import com.google.gson.JsonObject;
import com.ics.oauth2.ClientType;
import com.ics.oauth2.GrantType;
import com.ics.oauth2.ResponseType;
import com.ics.oauth2.auth.ClientAuthenticationMethod;
import com.ics.oauth2.id.ClientID;
import com.ics.oauth2.id.ClientSecret;
import com.ics.oauth2.id.SoftwareID;
import com.ics.oauth2.id.SoftwareVersion;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClientInformationTest {

    @Test
    void testClientInformationParameter() throws Exception{
        Set<String> parameter = ClientInformation.getRegisteredParameterName();

        assertTrue(parameter.contains("client_id"));
        assertTrue(parameter.contains("client_secret"));
        assertTrue(parameter.contains("client_id_issued_at"));
        assertTrue(parameter.contains("client_secret_expires_at"));
        assertTrue(parameter.contains("client_register_uri"));
    }

    @Test
    void testClientInformation() throws Exception{

        ClientID clientID = new ClientID("exampleId");
        ClientSecret secret = new ClientSecret();
        ClientMetadata metadata = new ClientMetadata();

        Set<URI> redirectUri = Set.of(new URI("https://example.com/callback"),new URI("https://example.com/callback2"));
        metadata.setRedirectUris(redirectUri);

        String clientName = "myexample";
        metadata.setClientName(clientName);

        Set<GrantType> grantTypes = Set.of(GrantType.PASSWORD, GrantType.getDefaultGrantType());
        metadata.setGrantType(grantTypes);

        Set<ResponseType> responseTypes = Set.of(ResponseType.CODE);
        metadata.setResponseTypes(responseTypes);

        String description = "My Example Client";
        metadata.setClientDescription(description);

        String softwareID = "sId";
        metadata.setSoftwareID(new SoftwareID(softwareID));

        String softwareVersion = "V 2.0";
        metadata.setSoftwareVersion(new SoftwareVersion(softwareVersion));

        URI jwksUri= new URI("https://client.example.com/jwks.json");
        metadata.setJwksUri(jwksUri);


        ClientInformation client = new ClientInformation(clientID,metadata,new Date(),secret, new URI("https://client.example.com/clientid=client"));

        assertEquals(clientID.getValue(),client.getClientID().getValue());
        assertNotNull(client.getIssuedAt());
        assertEquals(secret.getValue(),client.getClientSecret().getValue());
        assertEquals(redirectUri, client.getMetadata().getRedirectUris());
        assertEquals(jwksUri, client.getMetadata().getJwksUri());
        assertEquals(softwareID, client.getMetadata().getSoftwareID().getValue());
        assertEquals(softwareVersion, client.getMetadata().getSoftwareVersion().getValue());
        assertEquals(clientName, client.getMetadata().getClientName());
        assertEquals(2, metadata.getGrantType().size());
        assertEquals(1, metadata.getResponseTypes().size());
        assertEquals(new URI("https://client.example.com/clientid=client"),client.getRegisteredUri());
        assertTrue(metadata.getResponseTypes().contains(ResponseType.CODE));
        assertEquals(ClientType.CONFIDENTIAL, client.applyClientType());
        assertNotNull(client.toJsonObject());

        JsonObject jsonObject = client.toJsonObject();

        assertEquals(clientID.getValue(),jsonObject.get("client_id").getAsString());
        assertEquals(secret.getValue(), jsonObject.get("client_secret").getAsString());
        assertEquals("https://client.example.com/clientid=client", jsonObject.get("client_register_uri").getAsString());
        assertNotNull(jsonObject.get("client_id_issued_at"));

    }



    @Test
    void testWithNullClientID() throws Exception{
        ClientSecret secret = new ClientSecret();
        ClientMetadata metadata = new ClientMetadata();
        metadata.setClientName("name");

        assertThrows(IllegalArgumentException.class, ()-> new ClientInformation(null, metadata, new Date(), secret));

    }

    @Test
    void testWithNullClientMetadata() throws Exception{
        ClientID clientID = new ClientID("example");

        assertThrows(IllegalArgumentException.class, ()-> new ClientInformation(clientID, null));
    }

    @Test
    void testParseClientInformation() throws Exception{

        String json = "{"
                +"\"client_id\": \"exampleId\","+"\n"
                +"\"client_secret\": \"-ydkyoa123d3/drt3\","+"\n"
                +"\"client_name\": \"my client\","+"\n"
                +"\"jwks_uri\": \"https://client.example.com/jwks.json\","+"\n"
                +"\"grant_type\": [" +
                                    "\"authorization_code\"," +
                                    "\"implicit\"" +
                                "],"+"\n"
                +"\"redirect_uris\": [" +
                                    "\"https://client.example.com/callback\"," +
                                    "\"https://client.example.com/callback2\"" +
                                "],"+"\n"
                +"\"token_endpoint_auth_method\": \"client_secret_basic\","+"\n"
                +"\"scope\": [" +
                "\"read\"," +
                "\"write\"" +
                "],"+"\n"
                +"\"logo_uri\": \"https://client.example.com/logo/logo.png\""+"\n"
                + "}";

        ClientInformation information = ClientInformation.parse(json);

        assertEquals("exampleId", information.getClientID().getValue());
        assertEquals("-ydkyoa123d3/drt3", information.getClientSecret().getValue());
        assertEquals("my client", information.getMetadata().getClientName());
        assertEquals("https://client.example.com/jwks.json", information.getMetadata().getJwksUri().toString());
        assertEquals("https://client.example.com/logo/logo.png", information.getMetadata().getLogoUri().toString());
        assertTrue(information.getMetadata().getGrantType().contains(GrantType.AUTHORIZATION_CODE));
        assertTrue(information.getMetadata().getGrantType().contains(GrantType.IMPLICIT));
        assertEquals(2, information.getMetadata().getGrantType().size());
        assertEquals(2, information.getMetadata().getRedirectUris().size());
        assertTrue(information.getMetadata().getRedirectUris().contains(new URI("https://client.example.com/callback")));
        assertTrue(information.getMetadata().getScope().contains("read"));
        assertTrue(information.getMetadata().getScope().contains("write"));
        assertEquals(2, information.getMetadata().getScope().size());
        assertEquals(ClientAuthenticationMethod.CLIENT_SECRET_BASIC, information.getMetadata().getTokenEndpointAuthenticationMethod());



    }


}
