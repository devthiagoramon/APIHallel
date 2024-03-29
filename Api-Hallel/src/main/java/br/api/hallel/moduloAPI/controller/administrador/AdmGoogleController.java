package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.MembroGoogle;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.moduloAPI.repository.MembroGoogleRepository;
import br.api.hallel.moduloAPI.service.main.MainService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/google")
public class AdmGoogleController {

    @Autowired
    private MembroService service;
    @Autowired
    private MainService mainService;
    @Autowired
    private MembroGoogleRepository googleRepository;

    @PostMapping("/login")
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

            // CRIAR CONTA E REDIRECIONAR PARA A PÁGINA PRINCIPAL (HOMEPAGE)
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
