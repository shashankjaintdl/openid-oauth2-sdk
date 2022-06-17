package com.ics.oauth2.client;

import com.ics.oauth2.client.service.ClientRegistrationService;
import com.ics.oauth2.exception.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClientRegistrationController.ENDPOINT)
public class ClientRegistrationController {

    public static final String ENDPOINT = "oauth2/client";

    private final ClientRegistrationService clientRegistrationService;

    public ClientRegistrationController(ClientRegistrationService clientRegistrationService) {
        this.clientRegistrationService = clientRegistrationService;
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerNewClient() throws ParseException {
        return null;
    }




}
