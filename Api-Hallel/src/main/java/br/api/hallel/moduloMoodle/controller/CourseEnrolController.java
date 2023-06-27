package br.api.hallel.moduloMoodle.controller;

import br.api.hallel.moduloMoodle.model.CourseEnrol;
import br.api.hallel.moduloMoodle.payload.request.CourseEnrolRequest;
import br.api.hallel.moduloMoodle.payload.response.CourseEnrolResponse;
import br.api.hallel.moduloMoodle.service.CourseEnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cursos/matriculas/moodle")
public class CourseEnrolController {
    @Autowired
    private CourseEnrolService service;

    @PostMapping("/criar/{idUser}/{idCurso}")
    public ResponseEntity<CourseEnrol> createCourseMoodle(
            @PathVariable(value = "idUser") Long idUser,
            @PathVariable(value = "idCurso") Long idCurso) {

        return ResponseEntity.status(201).body(this.service.create(idUser, idCurso));
    }

    @GetMapping("")
    public ResponseEntity<List<CourseEnrolResponse>> listAllCourseMoodle() {

        return ResponseEntity.status(201).body(this.service.listAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEnrolResponse> listCourseMoodleById(@PathVariable(value = "id") Long id) {

        return ResponseEntity.status(201).body(this.service.listById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CourseEnrolResponse> updateCourseById(@PathVariable(value = "id") Long id,
                                                                   @RequestBody CourseEnrolRequest request) {
        return ResponseEntity.status(200).body(this.service.updateById(id, request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable(value = "id") Long id) {

        this.service.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}