package br.api.hallel.moduloAPI.service.quiz;

import br.api.hallel.moduloAPI.model.Quiz;
import br.api.hallel.moduloAPI.repository.QuizRepository;
import br.api.hallel.moduloAPI.service.interfaces.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService implements QuizInterface {

    @Autowired
    private QuizRepository repository;

    @Override
    public List<Quiz> ListAllQuizes() {
        return repository.findAll();
    }

    @Override
    public List<Quiz> ListQuizesDoDia() {
        Date today = new Date();
        return repository.findByDiaDoQuiz(today);
    }

    @Override
    public Quiz CreateQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    @Override
    public Boolean DeleteQuizById(String id) {
        Optional<Quiz> quiz = repository.findById(id);
        if (quiz.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean EditPontuacao(String id, int pontuacao) {
        Optional<Quiz> optionalQuiz = repository.findById(id);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            quiz.setPontuacao(pontuacao);
            repository.save(quiz);
            return true;
        }
        return false;
    }

    @Override
    public Quiz EditQuiz(Quiz quiz) {
        Optional<Quiz> optionalQuiz = repository.findById(quiz.getId());
        if (optionalQuiz.isPresent()) {
            Quiz existingQuiz = optionalQuiz.get();
            existingQuiz.setDiaDoQuiz(quiz.getDiaDoQuiz());
            existingQuiz.setPerguntasList(quiz.getPerguntasList());
            existingQuiz.setPontuacao(quiz.getPontuacao());
            return repository.save(existingQuiz);
        }
        return null;
    }
}
