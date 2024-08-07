package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.financeiroNovo.model.StatusEntradaEvento;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.*;
import br.api.hallel.moduloAPI.payload.resposta.*;
import br.api.hallel.moduloAPI.service.eventos.EventosService;
import br.api.hallel.moduloAPI.service.eventos.VoluntarioService;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/eventos")
@CrossOrigin(origins = "*")
@Log4j2
public class EventosController {


    @Autowired
    private EventosService service;
    @Autowired
    private MembroService membroService;
    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping("")
    public ResponseEntity<List<EventosVisualizacaoResponse>> listAllEventos() {
        return ResponseEntity.ok().body(service.listEventosSemDestaqueToVisualizar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosResponse> listEventoByIdController(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(201).body(service.listarEventoById(id));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<EventosResponse> listEventoByNomeController(@PathVariable(value = "nome") String nome) {
        return ResponseEntity.status(201).body(service.listarEventosByTitulo(nome));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<EventosResponse> updateEventos(@PathVariable(value = "id") String id,
                                                         @RequestBody EventosRequest request) {
        return ResponseEntity.status(200).body(service.updateEventoById(id, request));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteEventoById(@PathVariable String id) {

        this.service.deleteEventoById(id);
        return ResponseEntity.status(204).build();
    }

    public Boolean adicionarMembro(InscreverEventoRequest inscreverEventoRequest) {

        log.info(inscreverEventoRequest.toString());
        if (this.service.inscreverEvento(inscreverEventoRequest)) {
            return true;
        }
        return false;
    }

    @GetMapping("/eventosInscritos")
    public ResponseEntity<List<EventosResponse>> listEventosInscritos(@RequestParam(value = "idUser") String iduser) {
        return ResponseEntity.ok(this.service.listarEventosInscritos(iduser)   );

    }

    @GetMapping("/verificarUsuario/{idUser}")
    public ResponseEntity<EventoUsuarioVerifyResponse> verificarIdMembro(@PathVariable(value = "idUser") String id)  {

        Associado associadoDb = associadoService.listAssociadoById(id);
        Membro membroDb = this.membroService.listMembroId(id);

        if (associadoDb != null) {
            return ResponseEntity
                    .ok()
                    .body(new EventoUsuarioVerifyResponse().toEventoUsuarioVerifyResponse(associadoDb));
        }
        if (membroDb != null) {
            return ResponseEntity
                    .ok()
                    .body(new EventoUsuarioVerifyResponse().toEventoUsuarioVerifyResponse(membroDb));
        }
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/verificarInscrito")
    public ResponseEntity<Boolean> verificarIsInscrito(@RequestParam(value = "idEvento") String idEvento,
                                                       @RequestParam(value = "idUser") String idUser) {
        return ResponseEntity.ok(this.service.verificarIsInscrito(idEvento, idUser));

    }

    @GetMapping("/verificarSituacaoEmEvento")
    public ResponseEntity<StatusEntradaEvento> verificarSituacaoMembroEmEvento(@RequestParam(value = "idEvento") String idEvento, @RequestParam(value = "email") String emailMembro) {
        return ResponseEntity.ok(this.service.verificarSituacaoMembroEmEvento(idEvento, emailMembro));
    }


    //teste
    @PostMapping("/participarEvento")
    public ResponseEntity<Boolean> participarEvento(@RequestBody InscreverEventoRequest inscreverEventoRequest){
       return ResponseEntity.ok(this.service.inscreverEvento(inscreverEventoRequest));
    }



    @GetMapping("/{id}/listVoluntarios")
    public ResponseEntity<List<SeVoluntariarEventoResponse>> listAllVoluntarios(@PathVariable(value = "id") String idEvento){
        return ResponseEntity.ok().body(voluntarioService.listAllVoluntarios(idEvento));
    }

    @GetMapping("/{id}/ListValorEvento")
    public ResponseEntity<ValorEventoResponse> ListValorEvento(@PathVariable(value="id") String idEvento,String idMembro){
        return ResponseEntity.ok().body(this.service.InformacoesValorEvento(idEvento,idMembro));
    }





//    @PostMapping("/verificarEmail/{email}/{idEvento}")
//    public ResponseEntity<Boolean> verificarEmailMembro(@PathVariable(value = "email") String email,
//                                                        @PathVariable(value = "idEvento") String idEvento) {
//
//        Membro membroBD = this.membroService.findByEmail(email);
//
//        if (membroBD != null) {
//            this.service.preencherFormularioInscricaoComBanco(membroBD, idEvento);
//            return ResponseEntity.accepted().body(true);
//        }
//
//        return ResponseEntity.accepted().body(false); }

  /*  @PostMapping("/{id}/contribuir")
    public ResponseEntity<Boolean> contribuirEvento(@PathVariable(value = "id") String idEvento
            , @RequestBody ContribuicaoEventoReq req) {
        return ResponseEntity.status(200).body(this.membroService.enviarContribuicaoEvento(idEvento,req));
    }*/

}
