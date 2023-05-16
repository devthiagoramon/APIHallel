package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
import br.api.hallel.payload.requerimento.AddCursoReq;
import br.api.hallel.service.AssociadoService;
import br.api.hallel.service.CursoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin("*")
public class CursoController {

    @Autowired
    private CursoService service;
    @Autowired
    private AssociadoService associadoService;

    @PostMapping("/create")
    public ResponseEntity<Curso> createCurso(@RequestBody AddCursoReq cursoReq) {
        return ResponseEntity.status(201).body(this.service.createCurso(cursoReq));
    }

    @GetMapping("")
    public ResponseEntity<List<Curso>> listAllCursos() {
        return ResponseEntity.status(201).body(this.service.listAllCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> listCursoById(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.service.listCursoById(id));
    }
    @GetMapping("/usuario/cursosCadastrado/{id}")
    public ResponseEntity<List<Curso>> listCursoCadastradoByUsuario(@PathVariable String idUsuario){
        return ResponseEntity.status(201).body(this.service.listCursoByUser(idUsuario));
    }

    @GetMapping("/getModulo/{id}")
    public ResponseEntity<List<ModulosCurso>> listModuloByIdCurso(@PathVariable String id){
        return ResponseEntity.status(201).body(this.service.listModuloByIdCurso(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable String id, @RequestBody Curso curso) {
        return ResponseEntity.status(200).body(this.service.updateCurso(id, curso));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable String id) {
        this.service.deleteCurso(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/addParticipante/{idCurso}")
    public ResponseEntity<?> addParticipante(@RequestBody Associado associado, @PathVariable String idCurso) {

        this.service.addAssociadoCurso(associado, idCurso);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getParticipantes/{id}")
    public ResponseEntity<List<Associado>> listAssociadosCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.service.listUserContainsCurso(id));
    }

    @GetMapping("/getAtividades/{id}")
    public ResponseEntity<List<AtividadesCurso>> listAtividadeByCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.service.listAllAtividadesByCurso(id));
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> generatePDF(HttpServletResponse response) {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate = dateFormat.format(new Date());

        String header = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDate + ".pdf";
        response.setHeader(header, headerValue);

        try {
            this.service.generatePDF(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/concluir/{idCurso}/{idAssociado}")
    public ResponseEntity<Associado> concluirCurso(@PathVariable String idCurso, @PathVariable String idAssociado) {

        return ResponseEntity.status(204).body(this.associadoService.concluirCurso(idCurso, idAssociado));
    }

    @PostMapping("/atividade/concluir")
    public ResponseEntity<Associado> concluirAtvidade(@RequestParam(value = "idAssociado") String idAssociado, @RequestParam(value = "idCurso") String idCurso, @RequestBody AtividadesCurso atividadesCurso) {

        return ResponseEntity.status(204).body(this.associadoService.concluirAtividade(atividadesCurso.getTituloAtividade(), idAssociado, idCurso));
    }

    @PostMapping("/favorite/{idAssociado}/{idCurso}")
    public ResponseEntity<Associado> favoriteCurso(@PathVariable String idAssociado, @PathVariable String idCurso) {
        return ResponseEntity.ok().body(this.associadoService.favoriteCurso(idAssociado, idCurso));
    }

    @GetMapping("/desempenho/{id}")
    public ResponseEntity<Double> desempenhoCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.associadoService.desempenhoCurso(id));
    }

    @PostMapping("/concluirModulo/{idAssociado}")
    public ResponseEntity<Associado> concluirModulo(@RequestBody ModulosCurso modulosCurso, @PathVariable String idAssociado) {
        return ResponseEntity.ok().body(this.associadoService.concluirModuloCurso(modulosCurso, idAssociado));
    }

    @GetMapping("/desempenhoCurso/{idCurso}/{idAssociado}")
    public ResponseEntity<String> desempenhoDoCurso(@PathVariable String idCurso, @PathVariable String idAssociado) {
        return ResponseEntity.ok().body(this.service.desempenhoDoCurso(idAssociado, idCurso));
    }

}
