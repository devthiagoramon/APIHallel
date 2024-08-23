package br.api.hallel.ministerios.unittest;

import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidateCoordInMinisterioTest implements WithAssertions {

    @Autowired
    private MinisterioService ministerioService;

    @Test
    public void validateCoord() {

        String emmersonId = "63b70f05fdfe3109efdaaf2a";
        String luziaId = "6470df9a92c04e381ba4446b";
        String felipeId = "63c6bc557b00bc7614cf7a5d";

        MinisterioDTO ministerioDTO = new MinisterioDTO("Dança",
                emmersonId,
                luziaId);

        MinisterioResponse ministerioResponse = ministerioService.createMinisterio(ministerioDTO);

        assertThat(ministerioService.validateCoordenadorInMinisterio(ministerioResponse.getId(), emmersonId)).isTrue();
        assertThat(ministerioService.validateCoordenadorInMinisterio(ministerioResponse.getId(), luziaId)).isTrue();
        assertThat(ministerioService.validateCoordenadorInMinisterio(ministerioResponse.getId(), felipeId)).isFalse();

        ministerioService.deleteMinisterio(ministerioResponse.getId());
    }

}
