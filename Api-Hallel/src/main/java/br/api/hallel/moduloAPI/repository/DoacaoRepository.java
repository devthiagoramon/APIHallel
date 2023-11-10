package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Doacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoacaoRepository extends MongoRepository<Doacao, String> {
    List<Doacao> findByEmailDoador(String emailDoador);
}