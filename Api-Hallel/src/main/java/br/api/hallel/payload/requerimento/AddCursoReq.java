package br.api.hallel.payload.requerimento;

import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Curso;
import br.api.hallel.model.ModulosCurso;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AddCursoReq {

    private String nome;
    private String image;
    private ArrayList<String> requisitos;

    private ArrayList<ModulosCurso> modulos;
    private ArrayList<AtividadesCurso> atividadesCursos;

    public Curso toCurso(){
        Curso curso = new Curso();
        curso.setNome(this.getNome());
        curso.setImage(this.getImage());
        curso.setRequisitos(this.getRequisitos());
        curso.setModulos(this.getModulos());
        curso.setAtividadesCursos(this.getAtividadesCursos());
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
