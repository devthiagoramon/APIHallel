package br.api.hallel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.Administrador;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador,String>{

    public Optional<Administrador> findByEmail(String email);
    public Optional<Administrador> findByEmailAndSenhaAcesso(String email, String senhaAcesso);

    public boolean existsByEmail(String email);

    public Optional<Administrador> findByNome(String username);
}
