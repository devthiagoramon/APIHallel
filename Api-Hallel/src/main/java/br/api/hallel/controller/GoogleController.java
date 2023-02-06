package br.api.hallel.controller;

import br.api.hallel.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.service.MainService;
import br.api.hallel.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/google")
public class GoogleController {

    @Autowired
    private MembroService service;
    @Autowired
    private MainService mainService;

    @GetMapping("/cadastro")
    public String cadastroGoogle(@RequestBody @Valid SolicitarCadastroGoogle cadastroGoogle, OAuth2AuthenticationToken token) {
        cadastroGoogle.setNome(token.getPrincipal().getAttribute("name"));
        cadastroGoogle.setEmail(token.getPrincipal().getAttribute("email"));

        System.out.println(this.mainService.solicitarCadastroGoogle(cadastroGoogle));

        return cadastroGoogle.getNome() + " | " + cadastroGoogle.getEmail();
    }

    @GetMapping("/login")
    public String loginGoogle(@RequestBody @Valid LoginRequerimentoGoogle loginGoogle, OAuth2AuthenticationToken token) {
        loginGoogle.setEmail(token.getPrincipal().getAttribute("email"));
        loginGoogle.setNome(token.getPrincipal().getAttribute("name"));

        this.mainService.logarGoogle(loginGoogle);
        return "Logado";
    }

    @PostMapping("/logout")
    public void logoutGoogle() {

    }

    @PostMapping("/callback/{registrationId}")
    public void callbackGoogle() {

    }

}
