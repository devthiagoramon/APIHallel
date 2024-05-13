package br.api.hallel.moduloAPI.payload.requerimento;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuscarIdAssociadoReq {

    String email;

}
