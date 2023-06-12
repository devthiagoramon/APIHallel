package br.api.hallel.moduloAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.moduloAPI.model.Administrador;

import java.util.Optional;

public interface AdministradorRepository extends MongoRepository<Administrador,String>{

    public Optional<Administrador> findByEmail(String email);
    public Optional<Administrador> findByEmailAndSenhaAcesso(String email, String senhaAcesso);

    public boolean existsByEmail(String email);

    public Optional<Administrador> findByNome(String username);
}
