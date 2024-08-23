package br.api.hallel.ministerios.unittest;

import br.api.hallel.moduloAPI.dto.v1.ministerio.AddMembroMinisterioDTO;
import br.api.hallel.moduloAPI.dto.v1.ministerio.MembroMinisterioWithInfosResponse;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.service.ministerio.MinisterioService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MembroMinisterioTest implements WithAssertions {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private MinisterioService ministerioService;

    @Test
    public void listMembrosWithNoMinisterio() {
        String idMinisterio = "66be22c8f669ec5a41cead04";
        List<MembroResponse> membroResponses = membroRepository.findMembrosWithNoParticipationInThisMinisterio(idMinisterio);

        membroResponses.forEach(membroResponse -> System.out.println(membroResponse.toString()));
        System.out.println(membroResponses.size());
    }

    @Test
    public void addMembroIntoMinisterio() {
        String idMembroTeste1 = "63c5e468d5c4d5516bddc256";
        String idMembroTeste2 = "63c5e7e2d5c4d5516bddc257";
        String idMembroTeste3 = "6470df9a92c04e381ba4446b";
        String idMinisterio = "66be22c8f669ec5a41cead04";
        AddMembroMinisterioDTO dto = new AddMembroMinisterioDTO(idMembroTeste2, idMinisterio, null);
        ministerioService.addMembroMinisterio(dto);
    }

    @Test
    public void deleteMembroIntoMinisterio() {
        ministerioService.removerMembroMinisterio("66bf75a0a7c7570db70abb2e");
    }

    @Test
    public void listMembrosFromMinisterio() {
        String idMinisterio = "66be22c8f669ec5a41cead04";
        List<MembroMinisterioWithInfosResponse> membroMinisterioWithInfosResponses = ministerioService.listMembrosFromMinisterio(idMinisterio);
        membroMinisterioWithInfosResponses.forEach(membro -> System.out.println(membro.toString()));
    }
}
