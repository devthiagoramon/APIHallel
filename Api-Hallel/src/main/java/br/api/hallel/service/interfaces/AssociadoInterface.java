package br.api.hallel.service.interfaces;

import br.api.hallel.model.Associado;

import java.util.List;

public interface AssociadoInterface {

    List<Associado> listAllAssociado();
    Associado listAssociadoById(String id);
    void deleteAssociado(String id);

}
