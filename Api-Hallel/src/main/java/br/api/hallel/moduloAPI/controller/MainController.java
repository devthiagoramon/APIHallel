package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.exceptions.SolicitarCadastroException;
import br.api.hallel.moduloAPI.exceptions.SolicitarLoginException;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.payload.requerimento.*;
import br.api.hallel.moduloAPI.payload.resposta.AuthenticationResponse;
import br.api.hallel.moduloAPI.payload.resposta.DescricaoCursoRes;
import br.api.hallel.moduloAPI.payload.resposta.EventosVisualizacaoResponse;
import br.api.hallel.moduloAPI.security.services.JwtService;
import br.api.hallel.moduloAPI.service.cursos.CursoService;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import br.api.hallel.moduloAPI.service.main.MainService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Log4j2
public class MainController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MainService mainService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private EventosService eventosService;
    @Autowired
    private MembroService membroService;

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
    public ResponseEntity<AuthenticationResponse> solicitarCadastro(@Valid @RequestBody SolicitarCadastroRequerimento solicitarCadastroRequerimento) {

        try {

            return ResponseEntity.ok().body(this.mainService.solicitarCadastro(solicitarCadastroRequerimento));
        } catch (SolicitarCadastroException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @GetMapping("/home/isTokenExpired")
    public ResponseEntity<Boolean> isTokenValid(@RequestParam(value = "token") String token) {
        token = token.replace("Bearer ", "");
        return ResponseEntity.ok().body(jwtService.isTokenExpired(token));
    }

    @GetMapping("/listarCurso")
    public ResponseEntity<List<Curso>> listAllCursosUsers() {
        return ResponseEntity.status(201).body(this.cursoService.listAllCursos());
    }

    @GetMapping("/descCurso/{id}")
    public ResponseEntity<DescricaoCursoRes> descCursoById(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.cursoService.descCursoById(id));
    }

    @PostMapping("/matricularParticipante/{idAssociado}/{idCurso}")
    public ResponseEntity<?> addParticipante(@PathVariable(value = "idAssociado")
                                             String idAssociado, @PathVariable(value = "idCurso")
                                             String idCurso) throws AssociadoNotFoundException {
        this.cursoService.addAssociadoCurso(idAssociado, idCurso);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/removerParticipante/{idAssociado}/{idCurso}")
    public ResponseEntity<?> removeParticipante(@PathVariable(value = "idCurso") String idCurso,
                                                @PathVariable(value = "idAssociado") String idAssociado) throws AssociadoNotFoundException {

        this.cursoService.removeAssociadoCurso(idAssociado, idCurso);
        return ResponseEntity.status(202).build();
    }

    @GetMapping("/home/eventos/semDestaque")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventosSemDestaque() {
        return ResponseEntity.status(200).body(eventosService.listEventosSemDestaqueToVisualizar());
    }

    @GetMapping("/home/eventos/destacados")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventosDestacados() {
        return ResponseEntity.status(200).body(eventosService.listEventosDestacadosToVisualizar());
    }

    @GetMapping("/home/eventos/listar")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEvento() {
        return ResponseEntity.ok(eventosService.listarAllEventos());
    }

    @PostMapping("/home/eventos/participarEvento")
    public ResponseEntity<Boolean> participarEvento(@RequestBody InscreverEventoRequest inscreverEventoRequest) {
        return ResponseEntity.ok(this.eventosService.inscreverEvento(inscreverEventoRequest));
    }



}



