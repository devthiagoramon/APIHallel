package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.EscalaMinisterio;
import br.api.hallel.moduloAPI.repository.custom.CustomEscalaMinisterioRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscalaMinisterioRepository extends MongoRepository<EscalaMinisterio, String>, CustomEscalaMinisterioRepository {

    Optional<EscalaMinisterio> findByEventoId(String idEvento);
}
