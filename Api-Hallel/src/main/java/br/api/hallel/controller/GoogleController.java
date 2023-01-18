package br.api.hallel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/google")
public class GoogleController {

    @GetMapping("")
    public String user() {

        return "AAAAAAAAAAAAAA";
    }

    @GetMapping("/logar")
    public Principal user(Principal principal) {
        return principal;
    }
}
