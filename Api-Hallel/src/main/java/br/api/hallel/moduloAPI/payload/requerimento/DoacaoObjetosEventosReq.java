package br.api.hallel.moduloAPI.payload.requerimento;


import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventosReq {



    private String id;
    private String nomeDoObjeto;
    private Integer quantidade;
    private String emailDoador;
    private String nomeDoador;




   public DoacaoObjetosEventosReq ToDoacaoObjetosEventosReq (DoacaoObjetosEventos doacaoObjetosEventos){
       DoacaoObjetosEventosReq req = new DoacaoObjetosEventosReq();
       req.setNomeDoObjeto(doacaoObjetosEventos.getNomeDoObjeto());
       req.setQuantidade(doacaoObjetosEventos.getQuantidade());
       req.setNomeDoObjeto(doacaoObjetosEventos.getNomeDoObjeto());
       req.setEmailDoador(doacaoObjetosEventos.getEmailDoador());

       return req;
   }

   public DoacaoObjetosEventos toDoacaoObjetosEventos(){
        Date data = new Date();
       return new DoacaoObjetosEventos(getNomeDoObjeto(),getQuantidade(),getEmailDoador(),null,data,false,getNomeDoador());

   }


}
