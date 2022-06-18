package com.ics.oauth2.client;

import com.ics.oauth2.client.service.ClientRegistrationService;
import com.ics.oauth2.exception.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ClientRegistrationController.ENDPOINT)
public class ClientRegistrationController {

    public static final String ENDPOINT = "oauth2/client";

    private final ClientRegistrationService clientRegistrationService;

    public ClientRegistrationController(ClientRegistrationService clientRegistrationService) {
        this.clientRegistrationService = clientRegistrationService;
    }

    @GetMapping("/register")
    public String registerNewClient(@RequestBody String json) throws ParseException {
        return null;
    }




}
