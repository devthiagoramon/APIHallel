package br.api.hallel.controller;


import br.api.hallel.model.Comunidade;
import br.api.hallel.service.ComunidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/comunidade")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ComunidadeController {

    @Autowired
    ComunidadeService service;

    @GetMapping("/gastoMensal")
    public List<Comunidade> listGastoMensal(){
        return this.service.visualizarGastoMensal();
    }

    @GetMapping("/lucroMensal")
    public List<Comunidade> listLucroMensal(){
        return this.service.visualizarLucroMensal();
    }


}
