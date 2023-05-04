package br.api.hallel.payload.requerimento;

import br.api.hallel.model.DesempenhoAssociadoCurso;
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
