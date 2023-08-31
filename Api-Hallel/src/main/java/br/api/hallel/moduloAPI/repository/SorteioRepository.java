package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Sorteio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface SorteioRepository extends MongoRepository<Sorteio, String> {
}
