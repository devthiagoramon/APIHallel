package br.api.hallel.moduloAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.moduloAPI.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
