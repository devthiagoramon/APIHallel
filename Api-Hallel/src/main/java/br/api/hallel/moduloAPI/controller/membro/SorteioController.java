package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.payload.resposta.PerfilAssociadoSorteiosResponse;
import br.api.hallel.moduloAPI.service.main.SorteioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sorteios")
@CrossOrigin("*")
public class SorteioController {

    @Autowired
    private SorteioService sorteioService;

    @GetMapping("/perfil/associado")
    public ResponseEntity<List<PerfilAssociadoSorteiosResponse>> listAllSorteiosPerfilAssociado(@RequestParam(value = "mes") String mes,
                                                                                                @RequestParam(value = "ano") String ano) {
        return ResponseEntity.ok().body(this.sorteioService.listAllSorteioPerfilAssociado(mes, ano));
    }
}
