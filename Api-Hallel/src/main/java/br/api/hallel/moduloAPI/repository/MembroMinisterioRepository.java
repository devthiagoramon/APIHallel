package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.dto.v1.ministerio.MembroMinisterioWithInfosResponse;
import br.api.hallel.moduloAPI.model.MembroMinisterio;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembroMinisterioRepository extends MongoRepository<MembroMinisterio, String> {
    List<MembroMinisterio> findByMinisterioId(String ministerioId);

    @Aggregation(
            pipeline = {
                "{$addFields: {idMinisterioOID: {$toObjectId: '$ministerioId'},idMembroOID: {$toObjectId: '$membroId'},idsFuncaoMinisterio:{$map: {input: '$funcaoMinisterioIds',as: 'stringId',in: {$toObjectId: '$$stringId'}}}}}",
                "{$lookup: {from: 'membro',localField: 'idMembroOID',foreignField: '_id',as: 'membro'}}",
                "{$lookup: {from: 'ministerio',localField: 'idMinisterioOID',foreignField: '_id',as: 'ministerio'}}",
                "{$lookup: {from: 'funcaoMinisterio',localField: 'funcaoMinisterioIds',foreignField: '_id',as: 'funcaoMinisterio'}}",
                "{$unwind: '$membro'}",
                "{$unwind: '$ministerio'}"
            }
    )
    List<MembroMinisterioWithInfosResponse> findWithInfosByMinisterioId(String ministerioId);
    Optional<MembroMinisterio> findByMinisterioIdAndMembroId(String idMinisterio, String idMembro);

    @Aggregation(
            pipeline = {
                    "{$addFields: {idMinisterioOID: {$toObjectId: '$ministerioId'},idMembroOID: {$toObjectId: '$membroId'},idsFuncaoMinisterio:{$map: {input: '$funcaoMinisterioIds',as: 'stringId',in: {$toObjectId: '$$stringId'}}}}}",
                    "{$lookup: {from: 'membro',localField: 'idMembroOID',foreignField: '_id',as: 'membro'}}",
                    "{$lookup: {from: 'ministerio',localField: 'idMinisterioOID',foreignField: '_id',as: 'ministerio'}}",
                    "{$lookup: {from: 'funcaoMinisterio',localField: 'funcaoMinisterioIds',foreignField: '_id',as: 'funcaoMinisterio'}}",
                    "{$unwind: '$membro'}",
                    "{$unwind: '$ministerio'}",
                    "{$match: {'_id':  ?0}}"
            }
    )
    Optional<MembroMinisterioWithInfosResponse> findWithInfosById(
            String idMembroMinisterio);
}
