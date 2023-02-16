package br.api.hallel.repository;

import br.api.hallel.model.Comunidade;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComunidadeRepository extends MongoRepository<Comunidade, String> {

    List<Comunidade> findByLucroEventos();
    List<Comunidade> findByDespesaEventos();
    List<Comunidade> findByDoacaoTotal();


}
