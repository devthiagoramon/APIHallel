package br.api.hallel.controller;

import br.api.hallel.model.Membro;
import br.api.hallel.model.MembroGoogle;
import br.api.hallel.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.repository.MembroGoogleRepository;
import br.api.hallel.service.MainService;
import br.api.hallel.service.MembroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/google")
public class GoogleController {

    @Autowired
    private MembroService service;
    @Autowired
    private MainService mainService;
    @Autowired
    private MembroGoogleRepository googleRepository;

    @GetMapping("/login")
    public String cadastroGoogle(OAuth2AuthenticationToken token, String senha) {


        //CRIANDO A SOLICITAÇÃO DE CADASTRO
        SolicitarCadastroGoogle cadastroGoogle = new SolicitarCadastroGoogle();


        cadastroGoogle.setNome(token.getPrincipal().getAttribute("name"));
        cadastroGoogle.setEmail(token.getPrincipal().getAttribute("email"));
        cadastroGoogle.setSenha(senha);



        Optional<MembroGoogle> optional = this.googleRepository.findByEmail(cadastroGoogle.getEmail());

        //VERIFICAÇÃO PARA VER SE A CONTA JÁ FOI CRIADA
        if(optional.isPresent()){
            //CONTA CRIADA
            //A PENSAR NO QUE FAZER AINDA

            return "Email já registrado";

        }else{

            // CRIAR CONTA E REDIRECIONAR PARA A PÁGINA PRINCIPAL
            this.mainService.solicitarCadastroGoogle(cadastroGoogle);
            return "Cadastrado! Bem vindo "+cadastroGoogle.getNome();
        }

    }


    @PostMapping("/logout")
    public void logoutGoogle() {

    }

    @PostMapping("/callback/{registrationId}")
    public void callbackGoogle() {

    }

}
