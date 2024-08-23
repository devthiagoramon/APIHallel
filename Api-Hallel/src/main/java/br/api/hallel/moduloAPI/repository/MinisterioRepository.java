package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioWithCoordsResponse;
import br.api.hallel.moduloAPI.model.Ministerio;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinisterioRepository extends MongoRepository<Ministerio, String> {

    @Aggregation(pipeline = {
            "{$addFields: {coordIdOID:  {$toObjectId: '$coordenadorId'}, viceCoordIdOID:  {$toObjectId: '$viceCoordenadorId'}}}",
            "{$lookup: {from: 'membro', localField: 'coordIdOID', foreignField: '_id', as: 'coordenador'}}",
            "{$lookup: {from: 'membro', localField: 'viceCoordIdOID', foreignField:'_id', as: 'viceCoordenador'}}",
            "{$unwind: '$coordenador'}",
            "{$unwind: '$viceCoordenador'}"})
    AggregationResults<MinisterioWithCoordsResponse> findAllWithCoords();
}
