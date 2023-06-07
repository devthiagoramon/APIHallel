package br.api.hallel.moduloAPI.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    private ArrayList<AtividadesCurso> atividades;
    private ArrayList<ModulosCurso> modulos;
    private ArrayList<Associado> participantes;
    private String desempenhoDoCurso;
    private Boolean cursoCompleted;

    public Curso(String nome, String image, ArrayList<String> requisitos){
        this.nome = nome;
        this.image = image;
        this.requisitos = requisitos;
    }

}
