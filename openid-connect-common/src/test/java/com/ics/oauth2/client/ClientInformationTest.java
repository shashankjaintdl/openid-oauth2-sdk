package com.ics.oauth2.client;

import com.ics.oauth2.GrantType;
import com.ics.oauth2.ResponseType;
import com.ics.oauth2.id.ClientID;
import com.ics.oauth2.id.ClientSecret;
import com.ics.oauth2.id.SoftwareID;
import com.ics.oauth2.id.SoftwareVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClientInformationTest {

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


        ClientInformation client = new ClientInformation(clientID,metadata,new Date(),secret);

        assertEquals(clientID.getValue(),client.getClientID().getValue());
        assertEquals(secret.getValue(),client.getClientSecret().getValue());
        assertEquals(redirectUri, client.getMetadata().getRedirectUris());
        assertEquals(jwksUri, client.getMetadata().getJwksUri());
        assertEquals(softwareID, client.getMetadata().getSoftwareID().getValue());
        assertEquals(softwareVersion, client.getMetadata().getSoftwareVersion().getValue());
        assertEquals(clientName, client.getMetadata().getClientName());
        assertEquals(2, metadata.getGrantType().size());
        assertEquals(1, metadata.getResponseTypes().size());
        assertTrue(metadata.getResponseTypes().contains(ResponseType.CODE));

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
        ClientSecret secret = new ClientSecret();
        ClientID clientID = new ClientID("example");

        assertThrows(IllegalArgumentException.class, ()-> new ClientInformation(clientID, null, new Date(), secret));
    }



}
