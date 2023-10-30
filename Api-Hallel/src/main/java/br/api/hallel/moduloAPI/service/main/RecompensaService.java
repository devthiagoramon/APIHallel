package br.api.hallel.moduloAPI.service.main;

import br.api.hallel.moduloAPI.payload.resposta.SorteioResponse;
import br.api.hallel.moduloAPI.repository.SorteioRepository;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.interfaces.RecompensaInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecompensaService implements RecompensaInterface {

    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private SorteioRepository sorteioRepository;


    @Override
    public SorteioResponse addToSort(String idSorteio, String idAssociado) {
        return null;
    }
}
