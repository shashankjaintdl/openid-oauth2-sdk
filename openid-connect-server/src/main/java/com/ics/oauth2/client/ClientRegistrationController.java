//package com.ics.oauth2.client;
//
//import com.ics.oauth2.client.service.ClientRegistrationService;
//import com.ics.oauth2.exception.ParseException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping(ClientRegistrationController.ENDPOINT)
//public class ClientRegistrationController {
//
//    public static final String ENDPOINT = "oauth2/client";
//
//    private final ClientRegistrationService clientRegistrationService;
//
//    public ClientRegistrationController(ClientRegistrationService clientRegistrationService) {
//        this.clientRegistrationService = clientRegistrationService;
//    }
//
//    @GetMapping("/register")
//    public ModelAndView registerNewClient() throws ParseException {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//        return mv;
//    }
//}
