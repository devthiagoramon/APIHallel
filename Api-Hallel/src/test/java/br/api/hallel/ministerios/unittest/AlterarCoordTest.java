package br.api.hallel.ministerios.unittest;

import br.api.hallel.moduloAPI.dto.v1.EditCoordMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.MinisterioResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlterarCoordTest implements WithAssertions {

    @Autowired
    private MinisterioService ministerioService;

    @Test
    public void alterarCoordInMinisterio() {
        MinisterioDTO dto = new MinisterioDTO("Dança", "231321", "312312");
        MinisterioResponse ministerioResponse = ministerioService.createMinisterio(dto);
        EditCoordMinisterioDTO editCoordDTO = new EditCoordMinisterioDTO("123123", "1231231");
        MinisterioResponse ministerioResponseEdit = ministerioService.alterarCoordenadoresInMinisterio(ministerioResponse.getId(), editCoordDTO);
        assertThat(ministerioResponseEdit.getId()).isEqualTo(ministerioResponse.getId());
        assertThat(ministerioResponseEdit.getCoordenadorId()).isNotEqualTo(ministerioResponse.getCoordenadorId());
        assertThat(ministerioResponseEdit.getViceCoordenadorId()).isNotEqualTo(ministerioResponse.getViceCoordenadorId());

    }
}
