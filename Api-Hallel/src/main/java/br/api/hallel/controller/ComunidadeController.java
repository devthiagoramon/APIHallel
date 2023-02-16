package br.api.hallel.controller;


import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Eventos;
import br.api.hallel.service.ComunidadeService;
import com.mongodb.client.DistinctIterable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/comunidade")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ComunidadeController {

    @Autowired
    ComunidadeService service;

    @GetMapping("/lucro/eventos")
    public Double listLucroEvento(){
        List<Comunidade> comunidades = service.getLucroEvento();
        List<Double> lucro = new ArrayList<>();

        Double soma = 0.0;
        for (Comunidade c : comunidades) {
            lucro.addAll(c.getLucroEventos());
        }

        for (int i = 0; i < lucro.size(); i++) {
            soma += lucro.get(i);
        }

        return soma;

    }

    @GetMapping("/despesa/evento")
    public Double listDespesEvento(){
        List<Comunidade> comunidades = service.getDepesaEventos();
        List<Double> despesa = new ArrayList<>();

        Double soma = 0.0;
        for (Comunidade c : comunidades) {
            despesa.addAll(c.getDespesaEventos());
        }

        for (int i = 0; i < despesa.size(); i++) {
            soma += despesa.get(i);
        }

        return soma;

    }

    @GetMapping("/lucro/doacao")
    public Double listDoacaoTotal(){
        List<Comunidade> comunidades = service.getDoacaoTotal();
        List<Double> doacao = new ArrayList<>();

        Double soma = 0.0;
        for (Comunidade c : comunidades) {
            doacao.addAll(c.getLucroDoacao());
        }

        for (int i = 0; i < doacao.size(); i++) {
            soma += doacao.get(i);
        }

        return soma;

    }

    @GetMapping("/lucro/transacao")
    public Double listLucroTransacao(){
        List<Comunidade> comunidades = service.getLucroTransacao();
        List<Double> lucro = new ArrayList<>();

        Double soma = 0.0;

        for (Comunidade c : comunidades) {
            lucro.addAll(c.getLucroTransacao());
        }

        for (int i = 0; i < lucro.size(); i++) {
            soma += lucro.get(i);
        }

        return soma;

    }

    @GetMapping("/lucro/eventos/save")
    public String saveLucroEventos(){

        Eventos eventos = new Eventos();
        eventos.setId("12");
        eventos.setTitulo("Teu pai de teste2");
        eventos.setDate("23/12/2006");
        eventos.setLucro(50.0);
        this.service.salvarLucroEventos(eventos);

        return "deu bom";
    }

}
