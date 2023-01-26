package br.api.hallel.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/google")
public class GoogleController {


    @PostMapping("/login")
    public void loginGoogle(){

    }

    @PostMapping("/logout")
    public void logoutGoogle(){
    }

    @PostMapping("/callback/{registrationId}")
    public void callbackGoogle(){

    }
}
