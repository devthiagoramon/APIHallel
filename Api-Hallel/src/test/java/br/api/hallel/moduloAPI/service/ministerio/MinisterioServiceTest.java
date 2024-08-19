package br.api.hallel.moduloAPI.service.ministerio;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinisterioServiceTest implements WithAssertions {

    @Autowired
    private MinisterioService ministerioService;

    @Test
    void listMembroMinisterioById() {
        var membro = ministerioService.listMembroMinisterioById("66bf7ccc14ada5739ba7faf4");

        assertNotNull(membro.getMinisterio());
        assertNotNull(membro.getMembro());
        assertNotNull(membro.getFuncaoMinisterio());

        System.out.println(membro.toString());
    }
}