package com.ics.oauth2.client.service;

import com.ics.oauth2.client.ClientInformation;
import com.ics.oauth2.id.ClientID;

public interface ClientInformationService {

    ClientInformation loadClientByClientId(ClientID clientID);
}
