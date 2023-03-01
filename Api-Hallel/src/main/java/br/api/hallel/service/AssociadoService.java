package br.api.hallel.service;

import br.api.hallel.model.Associado;
import br.api.hallel.repository.AssociadoRepository;
import br.api.hallel.service.interfaces.AssociadoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository repository;

    @Override
    public List<Associado> listAllAssociado() {
        return this.repository.findAll();
    }

    Logger logger =  LoggerFactory.getLogger(AssociadoService.class);


    //LISTA TODOS OS ASSOCIADOS
    @Override
    public Associado listAssociadoById(String id) {

        logger.info("ASSOCIADO LISTADO!");

        return this.repository.findById(id).isPresent() ? this.repository.findById(id).get() : null;
    }

    //DELETA UM ASSOCIADO PELO ID DELE
    @Override
    public void deleteAssociado(String id) {
        Optional<Associado> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Associado associado = optional.get();

            logger.info("ASSOCIADO REMOVIDO!");

            this.repository.delete(associado);
        }

    }

    //ATUALIZA INFORMAÇÕES SOBRE O ASSOCIADO
    @Override
    public Associado updateAssociadoById(String id, Associado associado) {

        Optional<Associado> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Associado associadoOptional = optional.get();

            associadoOptional = associado;

            logger.info("ASSOCIADO ATUALIZADO!");

            System.out.println("Associado atualizado");
            return this.repository.save(associado);
        }else{
            logger.warn("ASSOCIADO NÃO ENCONTRADO!");

            return null;
        }

    }

}
