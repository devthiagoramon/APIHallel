package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.AtividadesCurso;
import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoVisualizacaoResponse {

    private String nome; //
    private String image;  //
    private String descricao; //

    private ArrayList<String> requisitos;
    //private ArrayList<String> tags;
    private ArrayList<String> aprendizado; //
    private ArrayList<String> conteudo; //

    private ArrayList<AtividadesCurso> atividades; //
    private ArrayList<ModulosCurso> modulos; //


    public CursoVisualizacaoResponse toVisualizacaoResponse(Curso curso){
        CursoVisualizacaoResponse response = new CursoVisualizacaoResponse();
        response.setNome(curso.getNome());
        response.setImage(curso.getImage());
        response.setDescricao(curso.getDescricao());
        response.setRequisitos(curso.getRequisitos());
        response.setAprendizado(curso.getAprendizado());

        return response;
    }

}
