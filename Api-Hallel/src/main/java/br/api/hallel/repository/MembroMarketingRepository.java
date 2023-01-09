package br.api.hallel.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.MembroMarketing;

@Repository
public interface MembroMarketingRepository extends MongoRepository<MembroMarketing, String>{

    public Optional<MembroMarketing> findByEmail(String email);

}
