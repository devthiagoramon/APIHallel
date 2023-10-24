package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
}
