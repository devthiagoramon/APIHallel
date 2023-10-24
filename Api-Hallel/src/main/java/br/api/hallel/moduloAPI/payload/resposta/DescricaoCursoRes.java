package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoCursoRes {

    private String id;
    private String nome;
    private String image;
    private String descricao;

    private ArrayList<String> requisitos;
    private ArrayList<String> tags;
    private ArrayList<String> aprendizado;
    private ArrayList<String> conteudo;

    private ArrayList<ModulosCurso> modulos;

    private Integer qtdMateriais;
    private Integer qtdAtividades;

    public DescricaoCursoRes toDescricaoCursoRes(Curso curso){
        DescricaoCursoRes descricaoCursoRes = new DescricaoCursoRes();
        descricaoCursoRes.setId(curso.getId());
        descricaoCursoRes.setNome(curso.getNome());
        descricaoCursoRes.setDescricao(curso.getDescricao());
        descricaoCursoRes.setImage(curso.getImage());
        descricaoCursoRes.setModulos(curso.getModulos());
        descricaoCursoRes.setRequisitos(curso.getRequisitos());
        descricaoCursoRes.setAprendizado(curso.getAprendizado());

        return descricaoCursoRes;
    }


}
