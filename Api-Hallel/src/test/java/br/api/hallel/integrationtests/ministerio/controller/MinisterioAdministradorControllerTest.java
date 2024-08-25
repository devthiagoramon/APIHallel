package br.api.hallel.integrationtests.ministerio.controller;

import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.integrationtests.ministerio.MinisterioIntegrationTest;
import br.api.hallel.integrationtests.ministerio.dto.MinisterioDTO;
import br.api.hallel.integrationtests.ministerio.dto.MinisterioResponse;
import br.api.hallel.moduloAPI.dto.v1.ministerio.EditCoordMinisterioDTO;
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
public class MinisterioAdministradorControllerTest extends
        MinisterioIntegrationTest implements WithAssertions {

    private static String id_ministerio1 = "";

    @Order(1)
    @Test
    void createMinisterio() throws IOException {
        MinisterioDTO ministerioDTO1 = new MinisterioDTO();
        ministerioDTO1.setNome("Dança");
        ministerioDTO1.setCoordenadorId(membrosTest.get(0).getId());
        ministerioDTO1.setViceCoordenadorId(membrosTest.get(1).getId());
        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio")
                .setPort(TestConfig.SERVER_PORT)
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        var content = RestAssured.given()
                                 .spec(specification)
                                 .body(ministerioDTO1)
                                 .when().post()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();
        MinisterioResponse ministerio = mapper.readValue(content, MinisterioResponse.class);

        assertThat(ministerio.getId()).isNotNull();
        assertThat(ministerio.getNome()).isNotEmpty();
        assertThat(ministerio.getNome()).isEqualTo("Dança");
        assertThat(ministerio.getCoordenadorId()).isNotEmpty();
        assertThat(ministerio.getCoordenadorId()).isEqualTo(membrosTest.get(0).getId());
        assertThat(ministerio.getViceCoordenadorId()).isNotEmpty();
        assertThat(ministerio.getViceCoordenadorId()).isEqualTo(membrosTest.get(1).getId());
        id_ministerio1 = ministerio.getId();
    }

    @Order(2)
    @Test
    void listMinisterio() throws IOException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio")
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                                 .spec(specification)
                                 .when().get()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var ministerioCoords = mapper.readValue(content, List.class);

        assertThat(ministerioCoords).isNotNull();
        assertThat(ministerioCoords.size()).isEqualTo(3);
        assertThat(ministerioCoords.get(0)).isNotNull();

    }


    @Order(3)
    @Test
    void listMinisterioById() throws IOException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio/" + id_ministerio1)
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                                 .spec(specification)
                                 .when().get()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var ministerioResponse = mapper.readValue(content, MinisterioResponse.class);

        assertThat(ministerioResponse.getId()).isNotNull();
        assertThat(ministerioResponse.getNome()).isNotEmpty();
        assertThat(ministerioResponse.getNome()).isEqualTo("Dança");
        assertThat(ministerioResponse.getId()).isEqualTo(id_ministerio1);
        assertThat(ministerioResponse.getCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getCoordenadorId()).isEqualTo(membrosTest.get(0).getId());
        assertThat(ministerioResponse.getViceCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getViceCoordenadorId()).isEqualTo(membrosTest.get(1).getId());

    }


    @Order(4)
    @Test
    void editarMinisterio() throws IOException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio/" + id_ministerio1 + "/edit")
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        MinisterioDTO ministerioDTOEdit = new MinisterioDTO();
        ministerioDTOEdit.setNome("Ministerio da Dança");
        ministerioDTOEdit.setCoordenadorId(membrosTest.get(0).getId());
        ministerioDTOEdit.setViceCoordenadorId(membrosTest.get(1).getId());

        var content = RestAssured.given()
                                 .spec(specification)
                                 .body(ministerioDTOEdit)
                                 .put()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var ministerioResponse = mapper.readValue(content, MinisterioResponse.class);

        assertThat(ministerioResponse.getId()).isNotNull();
        assertThat(ministerioResponse.getNome()).isNotEmpty();
        assertThat(ministerioResponse.getNome()).isEqualTo("Ministerio da Dança");
        assertThat(ministerioResponse.getCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getCoordenadorId()).isEqualTo(membrosTest.get(0).getId());
        assertThat(ministerioResponse.getViceCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getViceCoordenadorId()).isEqualTo(membrosTest.get(1).getId());

    }

    @Order(5)
    @Test
    void alterarCoordenadores() throws IOException {

        EditCoordMinisterioDTO editCoordMinisterioDTO = new EditCoordMinisterioDTO();
        editCoordMinisterioDTO.setCoordenadorId(membrosTest.get(1).getId());
        editCoordMinisterioDTO.setViceCoordenadorId(membrosTest.get(2).getId());

        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio/" + id_ministerio1 + "/edit/coordenadores")
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                                 .spec(specification)
                                 .body(editCoordMinisterioDTO)
                                 .patch()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        var ministerioResponse = mapper.readValue(content, MinisterioResponse.class);
        assertThat(ministerioResponse.getId()).isNotNull();
        assertThat(ministerioResponse.getNome()).isNotEmpty();
        assertThat(ministerioResponse.getNome()).isEqualTo("Ministerio da Dança");
        assertThat(ministerioResponse.getCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getCoordenadorId()).isEqualTo(membrosTest.get(1).getId());
        assertThat(ministerioResponse.getViceCoordenadorId()).isNotEmpty();
        assertThat(ministerioResponse.getViceCoordenadorId()).isEqualTo(membrosTest.get(2).getId());
    }

    @Order(6)
    @Test
    void deleteMinisterio() throws IOException {
        specification = new RequestSpecBuilder()
                .setBasePath("/api/administrador/v1/ministerio/" + id_ministerio1)
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, adm_login_token)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        RestAssured.given().spec(specification)
                   .delete()
                   .then()
                   .statusCode(HttpStatus.NO_CONTENT.value());
    }

}

