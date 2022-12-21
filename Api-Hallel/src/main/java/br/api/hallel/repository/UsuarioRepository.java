package br.api.hallel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
    public Usuario findByIp(String ip);

}
