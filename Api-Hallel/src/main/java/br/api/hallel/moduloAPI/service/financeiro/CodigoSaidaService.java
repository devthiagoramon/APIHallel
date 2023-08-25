package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.model.CodigosSaida;
import br.api.hallel.moduloAPI.payload.requerimento.CodigosSaidaReq;
import br.api.hallel.moduloAPI.repository.CodigosSaidaRepository;
import br.api.hallel.moduloAPI.service.interfaces.CodigoSaidaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CodigoSaidaService implements CodigoSaidaInterface {

    @Autowired
    private CodigosSaidaRepository repository;

    @Override
    public List<CodigosSaida> listarCodigosSaida() {
        return this.repository.findAll();
    }

    @Override
    public Boolean adicionarCodigoSaida(CodigosSaidaReq codigosSaidaReq) {

        boolean existe = false;

        for (CodigosSaida codigosSaida : this.repository.findAll()) {
            if (Objects.equals(codigosSaida.getNumCodigo(), codigosSaidaReq.getNumCodigo())) {
                existe = true;
            }
        }

        if (existe) {
            return false;
        } else {
            this.repository.insert(codigosSaidaReq.toCodigosSaida());
            return true;
        }
    }

    @Override
    public CodigosSaida listarCodigosSaidaPeloNumCodigo(Double numCodigo) {
        Optional<CodigosSaida> optional = this.repository.findByNumCodigo(numCodigo);
        return optional.orElse(null);
    }
}
