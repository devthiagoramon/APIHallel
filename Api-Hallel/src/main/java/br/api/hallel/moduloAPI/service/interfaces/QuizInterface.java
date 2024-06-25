package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Quiz;

import java.util.List;

public interface QuizInterface {

    List<Quiz> ListAllQuizes();
    List<Quiz> ListQuizesDoDia();
    Quiz CreateQuiz(Quiz quiz);
    Boolean DeleteQuizById(String id); // Corrigido para aceitar um parâmetro id
    Boolean EditPontuacao(String id, int pontuacao); // Corrigido para aceitar parâmetros id e pontuacao
    Quiz EditQuiz(Quiz quiz); // Corrigido para aceitar um parâmetro quiz




}
