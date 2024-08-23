package br.api.hallel.ministerios.unittest;

import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MinisterioResponse;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CRUDMinisterioTest implements WithAssertions {

    @Autowired
    private MinisterioService ministerioService;

    @Test
    public void createMinisterio() {
        MinisterioDTO dto = new MinisterioDTO("Dança", "", "");

        MinisterioResponse ministerioResponse = ministerioService.createMinisterio(dto);
        assertThat(ministerioResponse.getNome()).isEqualTo(dto.getNome());
        assertThat(ministerioResponse.getId()).isNotNull();

        ministerioService.deleteMinisterio(ministerioResponse.getId());
    }

    @Test
    public void editMinisterio() {
        MinisterioDTO dto = new MinisterioDTO("Dança", "", "");
        MinisterioResponse ministerioResponse = ministerioService.createMinisterio(dto);
        MinisterioDTO dtoEdit = new MinisterioDTO("Música", "2313", "1231231");
        MinisterioResponse ministerioResponseEdit = ministerioService.editMinisterio(ministerioResponse.getId(), dtoEdit);
        assertThat(ministerioResponseEdit.getId()).isEqualTo(ministerioResponse.getId());
        assertThat(ministerioResponseEdit.getNome()).isNotEqualTo(ministerioResponse.getNome());
        assertThat(ministerioResponseEdit.getCoordenadorId()).isNotEqualTo(ministerioResponse.getCoordenadorId());
        assertThat(ministerioResponseEdit.getViceCoordenadorId()).isNotEqualTo(ministerioResponse.getViceCoordenadorId());
        ministerioService.deleteMinisterio(ministerioResponseEdit.getId());
    }

    @NotNull
    private static MinisterioDTO mockMinisterio() {
        return new MinisterioDTO("Dança", "23123", "213213");
    }

    @Test
    public void listMinisterio() {
        MinisterioDTO dto1 = mockMinisterio();
        MinisterioDTO dto2 = mockMinisterio();
        MinisterioDTO dto3 = mockMinisterio();
        MinisterioDTO dto4 = mockMinisterio();

        MinisterioResponse ministerio1 = ministerioService.createMinisterio(dto1);
        MinisterioResponse ministerio2 = ministerioService.createMinisterio(dto2);
        MinisterioResponse ministerio3 = ministerioService.createMinisterio(dto3);
        MinisterioResponse ministerio4 = ministerioService.createMinisterio(dto4);

        ArrayList<MinisterioResponse> list = (ArrayList<MinisterioResponse>) ministerioService.listMinisterios();
        assertThat(list).isNotNull();
        assertThat(list).hasSize(4);

        ministerioService.deleteMinisterio(ministerio1.getId());
        ministerioService.deleteMinisterio(ministerio2.getId());
        ministerioService.deleteMinisterio(ministerio3.getId());
        ministerioService.deleteMinisterio(ministerio4.getId());
    }


}
