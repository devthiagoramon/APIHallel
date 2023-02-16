package br.api.hallel.controller;


import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Eventos;
import br.api.hallel.service.ComunidadeService;
import br.api.hallel.service.EventosService;
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
        return null;
    }

    @GetMapping("/lucroMensal")
    public Double listLucroMensal(){

        return null;
    }


}
