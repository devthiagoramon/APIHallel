package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.VoluntarioEvento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoluntarioRepository extends MongoRepository <VoluntarioEvento,String> {


}
