package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
