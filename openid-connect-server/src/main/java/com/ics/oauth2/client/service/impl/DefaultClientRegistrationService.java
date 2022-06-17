package com.ics.oauth2.client.service.impl;

import com.ics.oauth2.client.ClientInformation;
import com.ics.oauth2.client.service.ClientInformationService;
import com.ics.oauth2.client.service.ClientRegistrationService;
import com.ics.oauth2.id.ClientID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultClientRegistrationService implements ClientInformationService, ClientRegistrationService
{

    private static final String SS = "";

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DefaultClientRegistrationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ClientInformation addNewClient(ClientInformation clientInformation) {
        return null;
    }

    @Override
    public ClientInformation loadClientByClientId(ClientID clientID) {
        return null;
    }
}
