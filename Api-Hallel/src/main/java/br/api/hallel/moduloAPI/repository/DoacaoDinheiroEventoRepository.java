package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.DoacaoDinheiroEvento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoDinheiroEventoRepository extends MongoRepository<DoacaoDinheiroEvento,String> {


}
