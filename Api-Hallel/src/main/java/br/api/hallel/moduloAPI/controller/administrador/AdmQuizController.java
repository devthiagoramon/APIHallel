package br.api.hallel.moduloAPI.controller.administrador;

import br.api.hallel.moduloAPI.model.Quiz;
import br.api.hallel.moduloAPI.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador/Quiz")
@CrossOrigin("*")
public class AdmQuizController {

    @Autowired
    private QuizService service;

    @GetMapping("/list")
    public List<Quiz> listAllQuizes() {
        return service.ListAllQuizes();
    }

    @GetMapping("/list/today")
    public List<Quiz> listQuizesDoDia() {
        return service.ListQuizesDoDia();
    }

    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody Quiz quiz) {

        System.out.println("quiz criado");

        return service.CreateQuiz(quiz);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteQuizById(@PathVariable String id) {
        return service.DeleteQuizById(id);
    }

    @PutMapping("/edit/pontuacao/{id}")
    public Boolean editPontuacao(@PathVariable String id, @RequestParam int pontuacao) {
        return service.EditPontuacao(id, pontuacao);
    }

    @PutMapping("/edit")
    public Quiz editQuiz(@RequestBody Quiz quiz) {
        return service.EditQuiz(quiz);
    }


    @GetMapping("/create/AI")
    public Quiz CreateQuizAI(@RequestBody String prompt){


        return service.GenerateQuizAi(prompt);
    }




}
