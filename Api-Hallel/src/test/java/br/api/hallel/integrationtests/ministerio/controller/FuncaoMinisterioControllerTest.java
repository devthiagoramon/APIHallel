package br.api.hallel.integrationtests.ministerio.controller;

import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.integrationtests.ministerio.MinisterioIntegrationTest;
import br.api.hallel.integrationtests.ministerio.dto.FuncaoMinisterioDTO;
import br.api.hallel.moduloAPI.model.FuncaoMinisterio;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuncaoMinisterioControllerTest
        extends MinisterioIntegrationTest implements WithAssertions {

    private static FuncaoMinisterioDTO dto;
    private static String funcaoministerio1Id;

    @BeforeAll
    public static void setUp() {
        dto = new FuncaoMinisterioDTO();
    }

    @Order(0)
    @Test
    void validateCoordToken() {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao")
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        RestAssured.given().spec(specification)
                   .get()
                   .then()
                   .statusCode(403);
    }


    @Order(1)
    @Test
    void createFuncao() throws IOException {
        mockFuncao();
        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao")
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given().spec(specification)
                                 .body(dto)
                                 .post()
                                 .then()
                                 .statusCode(201)
                                 .extract().body().asString();

        var response = mapper.readValue(content, FuncaoMinisterio.class);

        funcaoministerio1Id = response.getId();

        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isNotEmpty();
        assertThat(response.getNome()).isEqualTo("Baterista");
        assertThat(response.getDescricao()).isEqualTo("Baterista da banda");
        assertThat(response.getIcone()).isEqualTo("...");
        assertThat(response.getCor()).isEqualTo("#FAFAFA");
        assertThat(response.getMinisterioId()).isEqualTo(dummyMinisterioIds.get(0));
    }

    @Order(2)
    @Test
    void listFuncaoByMinisterio() throws IOException {
        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao/ministerio/" + dummyMinisterioIds.get(0))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given().spec(specification)
                                 .get()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var response = mapper.readValue(content, List.class);

        assertThat(response).isNotNull();
        assertThat(response).isNotEmpty();
        assertThat(response.get(0)).isNotNull();
        assertThat(response.size()).isEqualTo(1);

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao/ministerio/" + dummyMinisterioIds.get(1))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content2 = RestAssured.given().spec(specification)
                                  .get()
                                  .then()
                                  .statusCode(200)
                                  .extract().body().asString();

        var response2 = mapper.readValue(content2, List.class);

        assertThat(response2).isNotNull();
        assertThat(response2).isEmpty();
    }

    @Order(3)
    @Test
    void listFuncaoById() throws IOException {
        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao/" + funcaoministerio1Id)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given().spec(specification)
                                 .get()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var response = mapper.readValue(content, FuncaoMinisterio.class);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isNotEmpty();
        assertThat(response.getId()).isEqualTo(funcaoministerio1Id);
        assertThat(response.getNome()).isEqualTo("Baterista");
        assertThat(response.getDescricao()).isEqualTo("Baterista da banda");
        assertThat(response.getIcone()).isEqualTo("...");
        assertThat(response.getCor()).isEqualTo("#FAFAFA");
        assertThat(response.getMinisterioId()).isEqualTo(dummyMinisterioIds.get(0));
    }

    @Order(4)
    @Test
    void editFuncao() throws IOException {

        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao/" + funcaoministerio1Id)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        dto.setNome("Cantor");
        dto.setDescricao("Cantor da banda");
        dto.setCor("#252525");

        var content = RestAssured.given().spec(specification)
                                 .body(dto)
                                 .put()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var response = mapper.readValue(content, FuncaoMinisterio.class);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
        assertThat(response.getId()).isNotEmpty();
        assertThat(response.getId()).isEqualTo(funcaoministerio1Id);
        assertThat(response.getNome()).isEqualTo("Cantor");
        assertThat(response.getDescricao()).isEqualTo("Cantor da banda");
        assertThat(response.getIcone()).isEqualTo("...");
        assertThat(response.getCor()).isEqualTo("#252525");
        assertThat(response.getMinisterioId()).isEqualTo(dummyMinisterioIds.get(0));

    }

    @Order(5)
    @Test
    void deleteFuncao() throws IOException {
        String coordMinisterio1Token = generateCoordToken(membrosTest.get(0)
                                                                     .getToken(), dummyMinisterioIds.get(0), membrosTest.get(0)
                                                                                                                        .getId());
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membrosTest.get(0)
                                                                             .getToken())
                .addHeader(TestConfig.HEADER_PARAM_COORDENADOR_TOKEN, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/coordenador/funcao/" + funcaoministerio1Id)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        RestAssured.given().spec(specification)
                   .delete()
                   .then()
                   .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void mockFuncao() {
        dto.setMinisterioId(dummyMinisterioIds.get(0));
        dto.setNome("Baterista");
        dto.setDescricao("Baterista da banda");
        dto.setIcone("...");
        dto.setCor("#FAFAFA");
    }

}
