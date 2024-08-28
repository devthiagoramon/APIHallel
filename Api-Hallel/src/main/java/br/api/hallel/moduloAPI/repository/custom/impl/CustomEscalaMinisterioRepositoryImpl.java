package br.api.hallel.moduloAPI.repository.custom.impl;

import br.api.hallel.moduloAPI.dto.v1.ministerio.EscalaMinisterioResponseWithInfos;
import br.api.hallel.moduloAPI.dto.v1.ministerio.EscalaMinisterioWithEventoInfoResponse;
import br.api.hallel.moduloAPI.repository.custom.CustomEscalaMinisterioRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Date;
import java.util.List;

public class CustomEscalaMinisterioRepositoryImpl
        implements CustomEscalaMinisterioRepository {

    private final MongoTemplate mongoTemplate;

    public CustomEscalaMinisterioRepositoryImpl(
            MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfos() {

        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("eventoIdOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                                                                  .build();

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");
        UnwindOperation unwindOperation = UnwindOperation.newUnwind()
                                                         .path("$evento")
                                                         .noArrayIndex()
                                                         .skipNullAndEmptyArrays();

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation, lookupEvento, unwindOperation);

        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioWithEventoInfoResponse.class)
                            .getMappedResults();
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosRangeDate(
            Date start, Date end) {
        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("eventoIdOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                                                                  .build();

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");

        MatchOperation matchOperation = Aggregation.match(Criteria.where("date")
                                                                  .gte(start)
                                                                  .lt(end));

        UnwindOperation unwindOperation = UnwindOperation.newUnwind()
                                                         .path("$evento")
                                                         .noArrayIndex()
                                                         .skipNullAndEmptyArrays();

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation, lookupEvento, matchOperation, unwindOperation);
        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioWithEventoInfoResponse.class)
                            .getMappedResults();
    }

    @Override
    public EscalaMinisterioResponseWithInfos findWithAllInfosById(
            String idEscalaMinisterio) {

        // PIPELINE COMPLEXA, SE FOR ALTERAR, SIGA A LOGICA DA PIPELINE

        // Add fields principal
        AddFieldsOperation addFieldsOperation = AddFieldsOperation
                .addField("eventoIdOID")
                .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                .addField("idMinisterioOID")
                .withValue(ConvertOperators.ToObjectId.toObjectId("$ministerioId"))
                .build();

        // Lookups principais

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");

        LookupOperation lookupMinisterio = LookupOperation.newLookup()
                                                          .from("ministerio")
                                                          .localField("idMinisterioOID")
                                                          .foreignField("_id")
                                                          .as("ministerio");


        // Find by Id
        MatchOperation matchOperation = Aggregation.match(Criteria.where("id")
                                                                  .is(idEscalaMinisterio));

        // Unwinds principais
        UnwindOperation unwindOperationEventos = UnwindOperation.newUnwind()
                                                                .path("$evento")
                                                                .noArrayIndex()
                                                                .skipNullAndEmptyArrays();
        UnwindOperation unwindOperationMinisterio = Aggregation.unwind("$ministerio");

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation,
                lookupEvento,
                lookupMinisterio,
                matchOperation,
                unwindOperationEventos,
                unwindOperationMinisterio);


        // Pega o primeiro elemento do arraylist
        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioResponseWithInfos.class)
                            .getMappedResults().get(0);

    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosCanParticipateByMembroId(
            String membroId, Date start, Date end) {

        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("eventoIdOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                                                                  .build();

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");

        MatchOperation matchOperation = Aggregation.match(Criteria.where("$membrosMinisterioConvidadosIds")
                                                                  .is(membroId));

        MatchOperation matchDateOperation = Aggregation.match(Criteria.where("date")
                                                                      .gte(start)
                                                                      .lt(end));

        UnwindOperation unwindOperation = UnwindOperation.newUnwind()
                                                         .path("$evento")
                                                         .noArrayIndex()
                                                         .skipNullAndEmptyArrays();

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation, lookupEvento, matchOperation, matchDateOperation, unwindOperation);

        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioWithEventoInfoResponse.class)
                            .getMappedResults();
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosConfirmedByMembroId(
            String membroId, Date start, Date end) {
        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("eventoIdOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                                                                  .build();

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");

        MatchOperation matchOperation = Aggregation.match(Criteria.where("membrosMinisterioConfimadoIds")
                                                                  .is(membroId));

        MatchOperation matchDateOperation = Aggregation.match(Criteria.where("date")
                                                                      .gte(start)
                                                                      .lt(end));

        UnwindOperation unwindOperation = UnwindOperation.newUnwind()
                                                         .path("$evento")
                                                         .noArrayIndex()
                                                         .skipNullAndEmptyArrays();

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation, lookupEvento, matchOperation, matchDateOperation, unwindOperation);

        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioWithEventoInfoResponse.class)
                            .getMappedResults();
    }

    @Override
    public List<EscalaMinisterioWithEventoInfoResponse> findAllWithEventosInfosRangeDateByMinisterioId(
            String idMinisterio, Date start, Date end) {
        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("eventoIdOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$eventoId"))
                                                                  .build();

        LookupOperation lookupEvento = LookupOperation.newLookup()
                                                      .from("eventos")
                                                      .localField("eventoIdOID")
                                                      .foreignField("_id")
                                                      .as("evento");

        MatchOperation matchOperation = Aggregation.match(Criteria.where("$ministerioId")
                                                                  .is(idMinisterio));

        MatchOperation matchDateOperation = Aggregation.match(Criteria.where("date")
                                                                      .gte(start)
                                                                      .lt(end));

        UnwindOperation unwindOperation = UnwindOperation.newUnwind()
                                                         .path("$evento")
                                                         .noArrayIndex()
                                                         .skipNullAndEmptyArrays();

        Aggregation aggregation = Aggregation.newAggregation(addFieldsOperation, lookupEvento, matchOperation, matchDateOperation, unwindOperation);

        return mongoTemplate.aggregate(aggregation, "escalaMinisterio", EscalaMinisterioWithEventoInfoResponse.class)
                            .getMappedResults();
    }
}