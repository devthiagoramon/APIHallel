package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Curso;
import br.api.hallel.moduloAPI.model.ModulosCurso;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CursoReq {

    private String nome;
    private String image;
    private String descricao;
    private ArrayList<String> requisitos;

    private ArrayList<ModulosCurso> modulos;


    public Curso toCurso(){
        Curso curso = new Curso();
        curso.setNome(this.getNome());
        curso.setImage(this.getImage());
        curso.setDescricao(this.getDescricao());
        curso.setRequisitos(this.getRequisitos());
        curso.setModulos(this.getModulos());
        return curso;
    }

    @Override
    public String toString() {
        return "AddCursoReq{" +
                "nome='" + nome + '\'' +
                ", image='" + image + '\'' +
                ", requisitos=" + requisitos +
                '}';
    }
}
