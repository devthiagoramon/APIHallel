package br.api.hallel.moduloAPI.controller.membro;

import br.api.hallel.moduloAPI.security.ministerio.TokenCoordenadorMinisterio;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/membros/ministerio")
@RestController
@Tag(name = "Membro ministerio", description = "Endpoints para os membros do ministério")
public class MembroMinisterioController {

    @Autowired
    private MinisterioService ministerioService;

    @Autowired
    private TokenCoordenadorMinisterio tokenCoordenadorMinisterio;


    @GetMapping("/token")
    @Operation(summary = "Valida e gera um token para o coordenador")
    public String generateTokenCoordenador(
            @RequestParam(name = "ministerioId") String ministerioId,
            @RequestParam(name = "membroId") String membroId) {
        if (!ministerioService.validateCoordenadorInMinisterio(ministerioId, membroId)) {
            throw new RuntimeException("Can't generate the token for coordenador, invalid ministerio or membro id");
        }

        return tokenCoordenadorMinisterio.generateToken(ministerioId, membroId);
    }


}
