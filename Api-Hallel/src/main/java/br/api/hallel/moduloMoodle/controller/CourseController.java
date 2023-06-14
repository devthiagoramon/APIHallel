package br.api.hallel.moduloMoodle.controller;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import br.api.hallel.moduloMoodle.payload.request.CursoMoodleReq;
import br.api.hallel.moduloMoodle.payload.response.CourseMoodleResponse;
import br.api.hallel.moduloMoodle.service.CursoMoodleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cursos/moodle")
public class CourseController {
    @Autowired
    private CursoMoodleService service;

    @PostMapping("/criar")
    public ResponseEntity<CursoMoodle> createCourseMoodle(@RequestBody CursoMoodleReq cursoReq) {

        this.service.createCursoMoodle(cursoReq);
        return ResponseEntity.status(201).body(cursoReq.toCursoMoodle());
    }

    @GetMapping("")
    public ResponseEntity<List<CourseMoodleResponse>> listAllCourseMoodle(){

        return ResponseEntity.status(201).body(this.service.listCursoMoodle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseMoodleResponse> listCourseMoodleById(@PathVariable(value = "id") Long id){

        return ResponseEntity.status(201).body(this.service.listCourseById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CourseMoodleResponse> updateCourseById(@PathVariable(value = "id") Long id,
                                                                 @RequestBody CursoMoodleReq cursoMoodleReq){
        return ResponseEntity.status(200).body(this.service.updateCourseById(id,cursoMoodleReq));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable(value = "id") Long id){

        this.service.deleteCourseById(id);
        return ResponseEntity.status(204).build();
    }
}
