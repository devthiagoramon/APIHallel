package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.exceptions.SolicitarCadastroException;
import br.api.hallel.moduloAPI.exceptions.SolicitarLoginException;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.payload.requerimento.*;
import br.api.hallel.moduloAPI.payload.resposta.*;
import br.api.hallel.moduloAPI.security.services.JwtService;
import br.api.hallel.moduloAPI.service.cursos.CursoService;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import br.api.hallel.moduloAPI.service.eventos.VoluntarioService;
import br.api.hallel.moduloAPI.service.main.MainService;
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
    private VoluntarioService voluntarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logar(@Valid @RequestBody LoginRequerimento loginRequerimento) {



        try {


            return ResponseEntity.ok().body(this.mainService.logar(loginRequerimento));
        } catch (SolicitarLoginException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<AuthenticationResponse> solicitarCadastro(@Valid @RequestBody SolicitarCadastroRequerimento solicitarCadastroRequerimento){

        try {

        return ResponseEntity.ok().body(this.mainService.solicitarCadastro(solicitarCadastroRequerimento));
        }catch (SolicitarCadastroException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @GetMapping("/home/isTokenExpired")
    public ResponseEntity<Boolean> isTokenValid(@RequestParam(value = "token") String token){
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
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventosSemDestaque(){
        return ResponseEntity.status(200).body(eventosService.listEventosSemDestaqueToVisualizar());
    }

    @GetMapping("/home/eventos/destacados")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventosDestacados(){
        return ResponseEntity.status(200).body(eventosService.listEventosDestacadosToVisualizar());
    }

    @GetMapping("/home/eventos/listar")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEvento(){
        return ResponseEntity.status(200).body(this.eventosService.listarAllEventos());
    }




    @PostMapping("/home/eventos/participarEvento")
    public ResponseEntity<Boolean> participarEvento(@RequestBody InscreverEventoRequest inscreverEventoRequest){

        System.out.println(inscreverEventoRequest.toString());
        System.out.println("inscrito !");

        return ResponseEntity.ok(this.eventosService.inscreverEvento(inscreverEventoRequest));
    }

    @PostMapping("/home/eventos/seVoluntariar")
    public ResponseEntity<Boolean> SeVoluntariar(@RequestBody SeVoluntariarEventoReq seVoluntariarEventoRequest){
        System.out.println(seVoluntariarEventoRequest.toString());
        return ResponseEntity.ok(this.eventosService.InscreverVoluntarioEmEvento(seVoluntariarEventoRequest));
    }

    @GetMapping("/home/{id}/listVoluntarios")
    public ResponseEntity<List<SeVoluntariarEventoResponse>> listAllVoluntarios(@PathVariable(value = "id") String idEvento){
        return ResponseEntity.ok().body(voluntarioService.listAllVoluntarios(idEvento));
    }

    @GetMapping("/home/{id}/listValoresEvento")
    public ResponseEntity<ValoresEventoResponse> listInformacoesDosValoresDoEvento(@PathVariable(value = "id") String idEvento){
        System.out.println("oiiiii");
        return ResponseEntity.ok().body(eventosService.informacoesValoresEvento(idEvento));
    }

    @PostMapping("/home/{id}/DoacaoDinheiro")
    public ResponseEntity<Boolean> FazerDoacaoDinheiro(@PathVariable(value = "id") String idEvento,
                                                       @RequestBody DoacaoDinheiroEventoReq doacaoDinheiroEventoReq){
        System.out.println("Doacao dinheiro");
        return ResponseEntity.ok(this.eventosService.FazerDoacaoDinheiro(doacaoDinheiroEventoReq,idEvento));

    }

    //listagem das doacoes em dinheiro de todos os eventos
    @GetMapping("/home/{id}/ListDoacaoDinheiro")
    public ResponseEntity<List<DoacaoDinheiroEventoResponse>> ListDoacoesDinheiro(@PathVariable(value="id") String idEvento){
        return ResponseEntity.ok().body(this.eventosService.listAllDoacoesDinheiro(idEvento));
    }


    @PostMapping("/home/{id}/DoacaoObjeto")
    public ResponseEntity<Boolean> FazerDoacaoObjeto(@PathVariable(value = "id") String idEvento, @RequestBody DoacaoObjetosEventosReq doacaoObjetosEventosReq){
        System.out.println("doacao objeto");
        return ResponseEntity.ok(this.eventosService.FazerDoacaoObjeto(doacaoObjetosEventosReq,idEvento));
    }

    @GetMapping("/home/{id}/ListDoacaoObjetos")
    public ResponseEntity<List<DoacaoObjetosEventosResponse>> ListDoacaoObjetos(@PathVariable(value="id") String idEvento){
        return ResponseEntity.ok().body(this.eventosService.listAllDoacoesObjetos(idEvento));
    }





}
