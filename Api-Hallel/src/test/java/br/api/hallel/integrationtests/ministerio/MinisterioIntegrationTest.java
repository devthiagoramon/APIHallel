package br.api.hallel.integrationtests.ministerio;

import br.api.hallel.integrationtests.BaseIntegrationTest;
import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class MinisterioIntegrationTest
        extends BaseIntegrationTest {
    public static List<String> dummyMinisterioIds;

    @BeforeAll
    public static void setup(
            @Autowired MinisterioService ministerioService) {
        dummyMinisterioIds = new ArrayList<>();
        mockUpMinisterio(ministerioService);
    }

    private static void mockUpMinisterio(
            MinisterioService ministerioService) {
        MinisterioDTO ministerioDTO1 = new MinisterioDTO();
        ministerioDTO1.setNome("Ministerio da dança");
        ministerioDTO1.setCoordenadorId(membrosTest.get(0).getId());
        ministerioDTO1.setViceCoordenadorId(membrosTest.get(1)
                                                       .getId());
        MinisterioResponse ministerioResponse1 = ministerioService.createMinisterio(ministerioDTO1);

        MinisterioDTO ministerioDTO2 = new MinisterioDTO();
        ministerioDTO1.setNome("Ministerio da música");
        ministerioDTO1.setCoordenadorId(membrosTest.get(1).getId());
        ministerioDTO1.setViceCoordenadorId(membrosTest.get(2)
                                                       .getId());
        MinisterioResponse ministerioResponse2 = ministerioService.createMinisterio(ministerioDTO2);

        dummyMinisterioIds.add(ministerioResponse1.getId());
        dummyMinisterioIds.add(ministerioResponse2.getId());


    }

    public static String generateCoordToken(String membroToken,
                                            String ministerioId,
                                            String membroId) {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .addHeader(TestConfig.HEADER_PARAM_AUTHORIZATION, membroToken)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/membros/ministerio/token")
                .addParam("ministerioId", ministerioId)
                .addParam("membroId", membroId)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
        return RestAssured.given().spec(specification)
                          .get().then().statusCode(200).extract()
                          .body()
                          .asString();
    }

}
