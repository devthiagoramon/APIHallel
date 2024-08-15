package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.MembroMinisterio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroMinisterioRepository extends MongoRepository<MembroMinisterio, String> {
    List<MembroMinisterio> findByMinisterioId(String ministerioId);
}
