package br.api.hallel.repository;

import br.api.hallel.model.ERole;
import br.api.hallel.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    public Optional<Role> findByName(ERole nome);

}
