package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.CodigosSaida;
import br.api.hallel.moduloAPI.payload.requerimento.CodigosSaidaReq;

import java.util.List;

public interface CodigoSaidaInterface {

    List<CodigosSaida> listarCodigosSaida();
    Boolean adicionarCodigoSaida(CodigosSaidaReq codigosSaidaReq);

    CodigosSaida listarCodigosSaidaPeloNumCodigo(Double numCodigo);
}
