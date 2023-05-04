package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curso {

    @Id
    private String id;
    private String nome;
    private String image;
    private String descricao;

    private ArrayList<String> requisitos;
    private ArrayList<String> tags;
    private ArrayList<String> aprendizado;
    private ArrayList<String> conteudo;

    private ArrayList<AtividadesCurso> atividadesCursos;
    private ArrayList<ModulosCurso> modulos;
    private ArrayList<Associado> participantes;

    public Curso(String nome, String image, ArrayList<String> requisitos){
        this.nome = nome;
        this.image = image;
        this.requisitos = requisitos;
    }

}
