package br.api.hallel.controller;

import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import br.api.hallel.service.MainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/", allowCredentials = "true")
@RequestMapping("/api")
public class MainController {

    @Autowired
    private MainService mainService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logar(@Valid @RequestBody LoginRequerimento loginRequerimento){
        return ResponseEntity.ok().body(this.mainService.logar(loginRequerimento));
    }

    @PostMapping("/solicitarCadastro")
    public ResponseEntity<AuthenticationResponse> solicitarCadastro(@Valid @RequestBody SolicitarCadastroRequerimento solicitarCadastroRequerimento){
        return ResponseEntity.ok().body(this.mainService.solicitarCadastro(solicitarCadastroRequerimento));
    }


}
