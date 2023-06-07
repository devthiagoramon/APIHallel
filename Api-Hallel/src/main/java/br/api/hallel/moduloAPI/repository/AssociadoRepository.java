package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado,String> {
    Optional<Associado> findByEmail(String email);
    List<Associado> findByIsAssociadoEquals(Enum<AssociadoRole> status);
}
