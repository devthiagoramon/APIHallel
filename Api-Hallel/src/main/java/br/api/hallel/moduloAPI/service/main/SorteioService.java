package br.api.hallel.moduloAPI.service.main;


import br.api.hallel.moduloAPI.model.Sorteio;
import br.api.hallel.moduloAPI.payload.requerimento.SorteioRequest;
import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;
import br.api.hallel.moduloAPI.repository.SorteioRepository;
import br.api.hallel.moduloAPI.service.interfaces.SorteioInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SorteioService implements SorteioInterface {

    @Autowired
    private SorteioRepository repository;

    @Override
    public Sorteio createSorteio(SorteioRequest sorteio) {
        return this.repository.insert(sorteio.toSorteio());
    }

    @Override
    public List<SorteioResponse> listAllSorteio() {
        List<SorteioResponse> listaSorteio = new ArrayList<>();

        this.repository.findAll().stream().forEach(sorteios -> {
            SorteioResponse sorteioResponse = new SorteioResponse();
            listaSorteio.add(sorteioResponse.toSorteioResponse(sorteios));
        });

        return listaSorteio;
    }

    @Override
    public SorteioResponse listSorteioById(String idSorteio) {

        Optional<Sorteio> optional = this.repository.findById(idSorteio);

        if (optional.isPresent()) {
            Sorteio sorteio = optional.get();

            return new SorteioResponse().toSorteioResponse(sorteio);
        }

        return null;
    }

    @Override
    public SorteioResponse updateSorteioById(String idSorteio, SorteioRequest sorteioRequest) {

        Sorteio sorteioAux = sorteioRequest.toSorteio();
        sorteioAux.setId(idSorteio);

        Sorteio sorteioResponse = this.listSorteioById(idSorteio) == null ?
                this.repository.save(sorteioAux) : null;

        return new SorteioResponse().toSorteioResponse(sorteioResponse);
    }

    @Override
    public void deleteSorteioById(String idSorteio) {

        if(listAllSorteio() == null){
            this.repository.deleteById(idSorteio);
        }

    }
}
