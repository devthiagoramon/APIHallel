package br.api.hallel.service;

import br.api.hallel.model.Comunidade;
import br.api.hallel.model.Doacao;
import br.api.hallel.repository.ComunidadeRepository;
import br.api.hallel.service.interfaces.ComunidadeInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunidadeService implements ComunidadeInterface {

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
        }else{
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
    public List<Comunidade> visualizarLucroMensal() {
        return this.repository.getLucroMensal();
    }

    @Override
    public List<Comunidade> visualizarGastoMensal() {
        return this.repository.getGastoMensal();
    }

}
