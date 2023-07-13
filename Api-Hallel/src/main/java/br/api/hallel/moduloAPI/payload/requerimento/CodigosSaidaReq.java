package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.CodigosSaida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigosSaidaReq {

    private Double numCodigo;

    private String nomeCodigo;

    public CodigosSaida toCodigosSaida() {
        CodigosSaida codigosSaida = new CodigosSaida();
        codigosSaida.setNomeCodigo(this.getNomeCodigo());
        codigosSaida.setNumCodigo(this.getNumCodigo());
        return codigosSaida;
    }
}
