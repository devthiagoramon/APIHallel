package br.api.hallel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador,String>{
    
}
