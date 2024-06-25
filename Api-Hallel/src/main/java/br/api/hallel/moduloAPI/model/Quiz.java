package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    String id;
    Date diaDoQuiz;
    List<Perguntas> perguntasList;
    int Pontuacao;


}
