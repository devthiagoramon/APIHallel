package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Alimentos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlimentosRepository extends MongoRepository<Alimentos,String> {
}
