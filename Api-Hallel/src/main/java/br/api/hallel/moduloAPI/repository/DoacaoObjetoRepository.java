package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.DoacaoObjeto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoacaoObjetoRepository extends MongoRepository<DoacaoObjeto, String> {
    List<DoacaoObjeto> findByEmailDoador(String emailDoador);
}