package br.api.hallel.moduloAPI.controller.membro;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/membros/ministerio/coordenador")
@RestController
@Tag(name = "Ministerio", description = "Endpoints para o ministério")
public class MembroCoordenadorMinisterioController {
}
