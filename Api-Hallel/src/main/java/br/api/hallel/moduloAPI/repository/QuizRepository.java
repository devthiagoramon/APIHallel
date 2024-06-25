package br.api.hallel.moduloAPI.repository;


import br.api.hallel.moduloAPI.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {

    List<Quiz> findByDiaDoQuiz(Date diaDoQuiz); // Consulta personalizada


}
