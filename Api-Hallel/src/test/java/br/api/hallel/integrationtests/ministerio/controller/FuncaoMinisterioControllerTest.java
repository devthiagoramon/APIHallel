package br.api.hallel.integrationtests.ministerio.controller;

import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.integrationtests.ministerio.MinisterioIntegrationTest;
import br.api.hallel.integrationtests.ministerio.dto.FuncaoMinisterioDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

class FuncaoMinisterioControllerTest
        extends MinisterioIntegrationTest {

    private static RequestSpecification spec;
    private static ObjectMapper mapper;

    private static FuncaoMinisterioDTO dto;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dto = new FuncaoMinisterioDTO();
    }

    @Test
    void createFuncao(){
        mockFuncao();
        String coordMinisterio1Token = membrosTest.get(0).getToken();
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, coordMinisterio1Token)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/administrador/login")
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

    }

    private void mockFuncao(){
        dto.setMinisterioId("1234");
        dto.setNome("Baterista");
        dto.setDescricao("Baterista da banda");
        dto.setIcone("...");
        dto.setCor("#FAFAFA");
    }

}
