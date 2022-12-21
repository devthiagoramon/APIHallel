package br.api.hallel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.Membro;

@Repository
public interface MembroRepository extends MongoRepository<Membro, String>{
    
}
