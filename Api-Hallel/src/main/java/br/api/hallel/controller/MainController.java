package br.api.hallel.controller;

import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import br.api.hallel.security.services.JwtService;
import br.api.hallel.service.MainService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MainController {

    @Autowired
    private JwtService jwtService;

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

    @PostMapping("/isTokenExpired")
    public ResponseEntity<Boolean> isTokenValid(@RequestBody String token){
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok().body(jwtService.isTokenExpired(token));
    }

}
