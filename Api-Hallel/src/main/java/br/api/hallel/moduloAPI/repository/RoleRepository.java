package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.ERole;
import br.api.hallel.moduloAPI.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

     Optional<Role> findByName(ERole nome);

}
