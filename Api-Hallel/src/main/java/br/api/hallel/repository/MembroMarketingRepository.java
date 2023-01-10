package br.api.hallel.repository;

import br.api.hallel.model.MembroMarketing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembroMarketingRepository extends MongoRepository<MembroMarketing, String> {
    Optional<MembroMarketing> findByEmail(String email);

}
