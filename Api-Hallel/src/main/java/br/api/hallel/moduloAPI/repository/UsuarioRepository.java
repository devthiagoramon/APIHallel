package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
