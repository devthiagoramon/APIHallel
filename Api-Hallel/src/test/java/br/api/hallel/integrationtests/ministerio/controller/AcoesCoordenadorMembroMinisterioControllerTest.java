package br.api.hallel.integrationtests.ministerio.controller;

import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.integrationtests.ministerio.MinisterioIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcoesCoordenadorMembroMinisterioControllerTest extends
        MinisterioIntegrationTest implements WithAssertions {

    @Order(1)
    @Test
    void listMembrosAdicionaveis() throws IOException {
        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/membroMinisterio/disponivel/"+dummyMinisterioIds.get(0))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                .spec(specification)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var response = mapper.readValue(content, List.class);

        assertThat(response).isNotNull();
        assertThat(response).isNotEmpty();

    }


}
