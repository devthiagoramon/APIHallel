package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AtividadesCurso;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import br.api.hallel.moduloAPI.service.cursos.CursoService;
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
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping("/matricular/{idAssociado}/{idCurso}")
    public ResponseEntity<?> addParticipante(@PathVariable(value = "idAssociado") String idAssociado, @PathVariable(value = "idCurso") String idCurso) throws AssociadoNotFoundException {
        this.service.addAssociadoCurso(idAssociado, idCurso);
        return ResponseEntity.status(204).build();
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


        Associado associado=null;
        Curso curso =null;
        String header = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDate + ".pdf";
        response.setHeader(header, headerValue);

        try {
            this.service.generatePDF(response,associado,curso);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/concluir")
    public ResponseEntity<Associado> concluirCurso(@RequestParam(value = "idCurso") String idCurso,
                                                   @RequestParam(value = "idAssociado") String idAssociado) {
        try {
            return ResponseEntity.status(204).body(this.service.concluirCurso(idCurso, idAssociado));
        } catch (AssociadoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/atividade/concluir")
    public ResponseEntity<Associado> concluirAtvidade(@RequestParam(value = "idAssociado") String idAssociado,
                                                      @RequestParam(value = "idCurso") String idCurso, @RequestBody AtividadesCurso atividadesCurso) throws AssociadoNotFoundException {

        return ResponseEntity.status(204).body(this.service.concluirAtividade(atividadesCurso.getTituloAtividade(), idAssociado, idCurso));
    }

    @PostMapping("/favorite")
    public ResponseEntity<Associado> favoriteCurso(@RequestParam(value = "idAssociado") String idAssociado,
                                                   @RequestParam(value = "idCurso") String idCurso) throws AssociadoNotFoundException {
        return ResponseEntity.ok().body(this.service.favoriteCurso(idAssociado, idCurso));
    }

    @GetMapping("/desempenho/{id}")
    public ResponseEntity<Double> desempenhoCurso(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.service.desempenhoCurso(id));
    }

    @PostMapping("/concluirModulo/{idAssociado}")
    public ResponseEntity<Associado> concluirModulo(@RequestBody ModulosCurso modulosCurso,
                                                    @PathVariable String idAssociado) throws AssociadoNotFoundException {
        return ResponseEntity.ok().body(this.service.concluirModuloCurso(modulosCurso, idAssociado));
    }

    @GetMapping("/desempenhoCurso")
    public ResponseEntity<String> desempenhoDoCurso(@RequestParam(value = "idCurso") String idCurso,
                                                    @RequestParam(value = "idAssociado") String idAssociado) {
        return ResponseEntity.ok().body(this.service.desempenhoDoCurso(idAssociado, idCurso));
    }

}
