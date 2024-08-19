package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.NaoConfirmadoEscalaMinisterio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NaoConfirmadoEscalaMinisterioRepository extends MongoRepository<NaoConfirmadoEscalaMinisterio, String> {
    List<NaoConfirmadoEscalaMinisterio> findAllByIdMembroMinisterio(String idMembroMinisterio);
}
