package br.api.hallel.moduloAPI.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddMembroMinisterioDTO {
    private String membroId;
    private String ministerioId;
    private List<String> funcaoMinisterioIds;
}
