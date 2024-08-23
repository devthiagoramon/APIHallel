package br.api.hallel.moduloAPI.dto.v1.ministerio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DefineFunctionsDTO {
    private String idMinisterioMembro;
    private List<String> idsFuncaoMinisterioAdd;
    private List<String> idsFuncaoMinisterioRemove;
}
