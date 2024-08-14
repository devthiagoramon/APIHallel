package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventosResponse {


    private String id;
    private String nomeDoObjeto;
    private Integer quantidade;
    private String nomeDoador;
    private Date dataDoacao;
    private boolean isRecebido;
    private String emailDoador;


    public DoacaoObjetosEventosResponse toResponse (DoacaoObjetosEventos doacaoObjetosEventos){
        return new DoacaoObjetosEventosResponse(doacaoObjetosEventos.getId(),doacaoObjetosEventos.getNomeDoObjeto(),
                doacaoObjetosEventos.getQuantidade(),doacaoObjetosEventos.getNomeDoador(),
                doacaoObjetosEventos.getDataDoacao(),doacaoObjetosEventos.getIsRecebido(),doacaoObjetosEventos.getEmailDoador()
                );

    }

}
