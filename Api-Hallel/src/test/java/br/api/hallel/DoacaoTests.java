package br.api.hallel;


import static org.assertj.core.api.Assertions.assertThat;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.DoarMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarReq;
import br.api.hallel.moduloAPI.repository.DoacaoRepository;
import br.api.hallel.moduloAPI.service.financeiro.DoacaoService;
import jakarta.annotation.Priority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DoacaoTests {

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Test
    void testDoar() {
        DoarReq doarReq = new DoarReq();
        doarReq.setEmailDonator("thiagoramonbarros@gmail.com");
        doarReq.setValor(29.00);
        doarReq.setTelefoneDonator("(92) 99234-3423");
        doarReq.setNomeDonator("Thiago Ramon");

        doacaoService.doar(doarReq);
    }

    @Test
    void testDoarObjeto() {
        DoarObjetoReq doarObjetoReq = new DoarObjetoReq();
        doarObjetoReq.setNameObjeto("Arroz");
        doarObjetoReq.setValor(3.00);
        doarObjetoReq.setNomeDonator("Thiago Ramon");
        doarObjetoReq.setTelefoneDonator("(92) 99234-3423");
        doarObjetoReq.setEmailDonator("thiagoramonbarros@gmail.com");
        doacaoService.doarObjeto(doarObjetoReq);
    }

    @Test
    void testDoarComoMembro(){
        DoarMembroReq doarMembroReq = new DoarMembroReq();
        doarMembroReq.setIdMembro("654b98ec9654ca2d9f677fec");
        doarMembroReq.setValor(40.50);

        doacaoService.doarMembro(doarMembroReq);
    }

    @Test
    void listDoacoes(){
        List<Doacao> doacaoList = doacaoService.listarDoacao();
        for (Doacao doacao :
                doacaoList) {
            System.out.println(doacao.toString());
        }
    }

    @Test
    void finalizarDoacao(){
        listDoacoes();
        doacaoService.finalizarDoacao("66b3a9f1e012513877705e9e");
        listDoacoes();
    }

    @Test
    void finalizarDoacaoObjeto(){
        listDoacoes();
        doacaoService.finalizarDoacaoObjeto("66b3ba2301289e27c7a3f0b4");
        listDoacoes();
    }

    @Test
    void deleteDoacao() {
        List<Doacao> doacaoList = doacaoService.listarDoacao();

        for (Doacao doacao :
                doacaoList) {
            doacaoService.deleteDoacao(doacao.getId());
        }

        List<Doacao> doacaoListAtualizada = doacaoService.listarDoacao();
        System.out.println("Tamanho do array: " + doacaoListAtualizada.size());
    }


}
