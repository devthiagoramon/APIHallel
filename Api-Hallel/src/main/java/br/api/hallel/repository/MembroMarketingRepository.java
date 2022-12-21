package br.api.hallel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroMarketingRepository extends MongoRepository<MembroMarketingRepository, String>{
    
}
