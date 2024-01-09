package br.api.hallel.moduloAPI.payload.requerimento;


import br.api.hallel.moduloAPI.model.DoacaoObjetosEventos;
import br.api.hallel.moduloAPI.model.Membro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoObjetosEventosReq {


    private String nomeDoObjeto;
    private Integer quantidade;
//    private Membro membroDoador;


   public DoacaoObjetosEventosReq ToDoacaoObjetosEventosReq (DoacaoObjetosEventos doacaoObjetosEventos){
       DoacaoObjetosEventosReq req = new DoacaoObjetosEventosReq();
       req.setNomeDoObjeto(doacaoObjetosEventos.getNomeDoObjeto());
       req.setQuantidade(doacaoObjetosEventos.getQuantidade());

       return req;
   }

   public DoacaoObjetosEventos toDoacaoObjetosEventos(){

       return new DoacaoObjetosEventos(getNomeDoObjeto(),getQuantidade());

   }


}
