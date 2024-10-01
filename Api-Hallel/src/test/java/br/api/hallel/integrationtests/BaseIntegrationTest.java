package br.api.hallel.integrationtests;

import br.api.hallel.integrationtests.config.TestConfig;
import br.api.hallel.integrationtests.ministerio.dto.MembroTestDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.model.ERole;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Role;
import br.api.hallel.moduloAPI.payload.requerimento.AdministradorLoginRequest;
import br.api.hallel.moduloAPI.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.moduloAPI.payload.requerimento.LoginRequerimento;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.moduloAPI.payload.resposta.AuthenticationResponse;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.repository.RoleRepository;
import br.api.hallel.moduloAPI.service.main.AdministradorService;
import br.api.hallel.moduloAPI.service.main.MainService;
import br.api.hallel.moduloAPI.service.main.MembroService;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = MongoTestContainerConfig.class)
public abstract class BaseIntegrationTest {

    public static ObjectMapper mapper;
    public static RequestSpecification specification;
    public static List<MembroTestDTO> membrosTest;
    public static String adm_login_token = "";

    @BeforeAll
    public static void setUp(@Autowired RoleRepository roleRepository,
                             @Autowired
                             AdministradorService administradorService,
                             @Autowired MembroService membroService,
                             @Autowired
                             MainService mainService, @Autowired
                             MinisterioService ministerioService) throws
            AssociadoNotFoundException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8888;

        mapper = new ObjectMapper();

        membrosTest = new ArrayList<>();

        roleRepository.insert(new Role(ERole.ROLE_USER));
        roleRepository.insert(new Role(ERole.ROLE_ADMIN));

        var admDTO = new CadAdministradorRequerimento();
        admDTO.setEmail("administrador@gmail.com");
        admDTO.setNome("Thiago Ramon");
        admDTO.setSenha("hallel2024");
        administradorService.inserirAdministrador(admDTO);

        mockMembros(mainService, membroService, 8);
    }

    public static void mockMembros(MainService mainService,
                                   MembroService membroService,
                                   int quantidade) throws
            AssociadoNotFoundException {
        for (int i = 0; i < quantidade; i++) {
            String nome = "membro" + (i + 1);
            String senha = "membro" + (i + 1);
            String email = "membro" + (i + 1);
            Membro membroByEmail = membroService.findByEmail(email);
            if (membroByEmail != null){
                var response = mainService.logar(new LoginRequerimento(membroByEmail.getEmail(), senha));
                var membro = membroService.listMembroByName(nome);
                membrosTest.add(new MembroTestDTO(response.getToken(), membro.getId()));
                continue;
            }
            var response = mainService.solicitarCadastro(
                    new SolicitarCadastroRequerimento(nome, senha, email));
            var membro = membroService.listMembroByName(nome);
            membrosTest.add(new MembroTestDTO(response.getToken(), membro.getId()));
        }
    }

    @BeforeEach
    public void loginAdm() throws IOException {
        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_CONTENT_TYPE, TestConfig.APPLICATION_JSON)
                .setPort(TestConfig.SERVER_PORT)
                .setBasePath("/api/administrador/login")
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = RestAssured.given()
                                 .spec(specification)
                                 .body(new AdministradorLoginRequest("administrador@gmail.com", "hallel2024"))
                                 .when().post()
                                 .then()
                                 .statusCode(200)
                                 .extract().body().asString();

        AuthenticationResponse authenticationResponse = mapper.readValue(content, AuthenticationResponse.class);
        adm_login_token = authenticationResponse.getToken();
    }
}
