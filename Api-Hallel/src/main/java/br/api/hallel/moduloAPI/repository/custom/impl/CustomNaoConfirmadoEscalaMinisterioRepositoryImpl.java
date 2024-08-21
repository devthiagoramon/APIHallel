package br.api.hallel.moduloAPI.repository.custom.impl;

import br.api.hallel.moduloAPI.dto.v1.NaoConfirmadoEscalaMinisterioWithInfos;
import br.api.hallel.moduloAPI.repository.custom.CustomNaoConfirmadoEscalaMinisterioRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class CustomNaoConfirmadoEscalaMinisterioRepositoryImpl
        implements CustomNaoConfirmadoEscalaMinisterioRepository {

    private final MongoTemplate mongoTemplate;

    public CustomNaoConfirmadoEscalaMinisterioRepositoryImpl(
            MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<NaoConfirmadoEscalaMinisterioWithInfos> findAllWithEscalaId(
            String idEscala) {
        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("membroMinisterioOID")
                                                                  .withValue(ConvertOperators.ToObjectId.toObjectId("$idMembroMinisterio"))
                                                                  .build();

        LookupOperation lookupMembroMinisterio = LookupOperation.newLookup()
                                                                .from("membroMinisterio")
                                                                .localField("membroMInisterioOID")
                                                                .foreignField("_id")
                                                                .as("membroMinisterio");

        AddFieldsOperation addFieldsOperation2 = AddFieldsOperation.addField("membroOID")
                                                                   .withValue(ConvertOperators.ToObjectId.toObjectId("$membroMinisterio.membroId"))
                                                                   .build();

        LookupOperation lookupMembro = LookupOperation.newLookup()
                                                      .from("membro")
                                                      .localField("membroOID")
                                                      .foreignField("_id")
                                                      .as("membro");

        Aggregation aggregation = Aggregation.newAggregation(
                addFieldsOperation,
                lookupMembroMinisterio,
                Aggregation.unwind("membroMinisterio"),
                addFieldsOperation2,
                lookupMembro,
                Aggregation.unwind("membro"),
                Aggregation.match(Criteria.where("idEscalaMinisterio")
                                          .is(idEscala))
                                                            );


        return mongoTemplate.aggregate(aggregation, "naoConfirmadoEscalaMinisterio", NaoConfirmadoEscalaMinisterioWithInfos.class)
                            .getMappedResults();
    }
}
