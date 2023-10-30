package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.CodigosSaida;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodigosSaidaRepository extends MongoRepository<CodigosSaida, String> {

    Optional<CodigosSaida> findByNumCodigo(Double numCodigo);
}
