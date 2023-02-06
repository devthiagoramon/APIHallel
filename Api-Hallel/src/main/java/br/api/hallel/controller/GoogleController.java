package br.api.hallel.controller;

import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import br.api.hallel.service.MainService;
import br.api.hallel.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthenticationResponse> cadastroGoogle(@Valid @RequestBody SolicitarCadastroRequerimento solicitarCadastroRequerimento, OAuth2AuthenticationToken token) {
        solicitarCadastroRequerimento.setNome(token.getPrincipal().getAttribute("name"));
        solicitarCadastroRequerimento.setEmail(token.getPrincipal().getAttribute("email"));

        return ResponseEntity.ok().body(this.mainService.solicitarCadastro(solicitarCadastroRequerimento));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginGoogle(@Valid @RequestBody LoginRequerimento loginRequerimento){
        return ResponseEntity.ok().body(this.mainService.logar(loginRequerimento));
    }

    @PostMapping("/logout")
    public void logoutGoogle() {

    }

    @PostMapping("/callback/{registrationId}")
    public void callbackGoogle() {

    }

}
