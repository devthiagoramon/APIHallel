package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.service.interfaces.AssociadoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository repository;


    @Override
    public List<Associado> listAllAssociado() {
        return this.repository.findAll();
    }

    @Override
    public Associado listAssociadoById(String id) {
        return this.repository.findById(id).isPresent() ? this.repository.findById(id).get() : null;
    }

    @Override
    public void deleteAssociado(String id) {
        Optional<Associado> optional = this.repository.findById(id);

        if(optional.isPresent()){
            Associado associado = optional.get();
            this.repository.delete(associado);
        }

    }
}
