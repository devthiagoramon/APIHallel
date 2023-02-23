package br.api.hallel.service;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;
import br.api.hallel.model.Eventos;
import br.api.hallel.repository.ComunidadeRepository;
import br.api.hallel.service.interfaces.ComunidadeInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunidadeService implements ComunidadeInterface {

    @Autowired
    private ComunidadeRepository repository;

    @Override
    public void atualizarDoacao(Doacao doacao) {
        Comunidade comunidade = getComunidade();

        if (comunidade.getDoacaoTotal() != null) {
            comunidade.getDoacaoTotal().add(doacao);
        } else {
            ArrayList<Doacao> doacoes = new ArrayList<>();
            doacoes.add(doacao);
            comunidade.setDoacaoTotal(doacoes);
        }

        if (comunidade.getDoacaoMensais() != null) {
            comunidade.getDoacaoMensais().add(doacao);
        } else {
            ArrayList<Doacao> doacoes = new ArrayList<>();
            doacoes.add(doacao);
            comunidade.setDoacaoMensais(doacoes);

        }
        repository.save(comunidade);
    }

    @Override
    public Comunidade getComunidade() {
        return repository.findById("63e28aa8543a7bca82109218").get() != null ?
                repository.findById("63e28aa8543a7bca82109218").get() :
                null;
    }

    @Override
    public void salvarLucroEventos(Eventos eventos) {
        Comunidade comunidade = getComunidade();

        if (comunidade.getLucroEventos() != null) {
            comunidade.getLucroEventos().add(eventos.getLucro());
        } else {
            ArrayList<Double> eventosArrayList = new ArrayList<>();
            eventosArrayList.add(eventos.getLucro());
            comunidade.setLucroEventos(eventosArrayList);
        }
        this.repository.save(comunidade);
    }

    @Override
    public void salvarDespesaEventos(Eventos eventos) {
        Comunidade comunidade = getComunidade();
        if (comunidade.getDespesaEventos() != null) {
            comunidade.getDespesaEventos().add(eventos.getDespesas());
        } else {
            ArrayList<Double> eventosArrayList = new ArrayList<>();
            eventosArrayList.add(eventos.getDespesas());
            comunidade.setDespesaEventos(eventosArrayList);
        }

        this.repository.save(comunidade);
    }

    @Override
    public void saveDoacao(Doacao doacao) {
        Comunidade comunidade = getComunidade();

        if (comunidade.getLucroDoacao() != null) {
            comunidade.getLucroDoacao().add(doacao.getValorDoacao());
        } else {
            ArrayList<Double> doacoes = new ArrayList<>();
            doacoes.add(doacao.getValorDoacao());
            comunidade.setLucroDoacao(doacoes);

        }
        this.repository.save(comunidade);
    }

    @Override
    public void salvarTransacao() {
        Comunidade comunidade = getComunidade();

        if (comunidade.getLucroTransacao() != null) {
            comunidade.getLucroTransacao().add(50.0);
        } else {
            ArrayList<Double> transacoes = new ArrayList<>();
            transacoes.add(50.0);
            comunidade.setLucroTransacao(transacoes);

        }
        this.repository.save(comunidade);
    }

    @Override
    public List<Comunidade> getLucroEvento() {

        return this.repository.findAll();
    }

    @Override
    public List<Comunidade> getDepesaEventos() {
        return this.repository.findAll();
    }

    @Override
    public List<Comunidade> getDoacaoTotal() {
        return this.repository.findAll();
    }

    @Override
    public List<Comunidade> getLucroTransacao() {
        return this.repository.findAll();
    }


}
