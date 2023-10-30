package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.DesempenhoAssociadoCurso;
import lombok.Data;

@Data
public class DesAssociadoCursoReq {
    private Boolean atividadeCompleted;
    private Boolean cursoCompleted;

    public DesempenhoAssociadoCurso toDesempenhoAssociadoCurso(){
        DesempenhoAssociadoCurso desempenho = new DesempenhoAssociadoCurso();
        desempenho.setCursoCompleted(cursoCompleted);
        desempenho.setAtividadeCompleted(atividadeCompleted);

        return desempenho;
    }

}
