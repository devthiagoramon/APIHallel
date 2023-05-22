package br.api.hallel.controller;

import br.api.hallel.exceptions.AssociadoNotFoundException;
import br.api.hallel.exceptions.SolicitarCadastroException;
import br.api.hallel.exceptions.SolicitarLoginException;
import br.api.hallel.model.Curso;
import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import br.api.hallel.payload.resposta.DescricaoCursoRes;
import br.api.hallel.security.services.JwtService;
import br.api.hallel.service.CursoService;
import br.api.hallel.service.MainService;
import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MainController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MainService mainService;
    @Autowired
    private CursoService cursoService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logar(@Valid @RequestBody LoginRequerimento loginRequerimento) {

        try {
            return ResponseEntity.ok().body(this.mainService.logar(loginRequerimento));
        } catch (SolicitarLoginException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PostMapping("/solicitarCadastro")
    public ResponseEntity<AuthenticationResponse> solicitarCadastro(@Valid @RequestBody SolicitarCadastroRequerimento solicitarCadastroRequerimento){

        try {

        return ResponseEntity.ok().body(this.mainService.solicitarCadastro(solicitarCadastroRequerimento));
        }catch (SolicitarCadastroException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @GetMapping("/isTokenExpired/{token}")
    public ResponseEntity<Boolean> isTokenValid(@PathVariable String token){
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok().body(jwtService.isTokenExpired(token));
    }

    @GetMapping("/listarCurso")
    public ResponseEntity<List<Curso>> listAllCursosUsers() {
        return ResponseEntity.status(201).body(this.cursoService.listAllCursos());
    }

    @GetMapping("/descCurso/{id}")
    public ResponseEntity<DescricaoCursoRes> descCursoById(@PathVariable String id){
        return ResponseEntity.status(201).body(this.cursoService.descCursoById(id));
    }

    @PostMapping("/matricularCurso/{idAssociado}/{idCurso}")
    public ResponseEntity<?> addParticipante(@PathVariable(value = "idAssociado") String idAssociado, @PathVariable(value = "idCurso") String idCurso) throws AssociadoNotFoundException {
        this.cursoService.addAssociadoCurso(idAssociado, idCurso);
        return ResponseEntity.status(204).build();
    }

}
