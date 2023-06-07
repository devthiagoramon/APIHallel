package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadesCurso {

    private String tituloAtividade;
    private String descricaoAtividade;
    private String arquivoAtividade;
    private Boolean isCompleted;

}
