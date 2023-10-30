package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.MembroMarketing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MembroMarketingRepository extends MongoRepository<MembroMarketing, String>{

    public Optional<MembroMarketing> findByEmail(String email);

}
