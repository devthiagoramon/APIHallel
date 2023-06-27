package br.api.hallel.moduloMoodle.controller;

import br.api.hallel.moduloMoodle.model.EnrolAssignments;
import br.api.hallel.moduloMoodle.payload.request.EnrolAssignmentsReq;
import br.api.hallel.moduloMoodle.payload.response.EnrolAssignmentsResponse;
import br.api.hallel.moduloMoodle.service.EnrolAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/permissao/matriculas/moodle")
public class EnrolAssignmentsController {

    @Autowired
    private EnrolAssignmentsService service;

    @PostMapping("/criar/{idUser}")
    public ResponseEntity<EnrolAssignments> createCourseMoodle(@RequestBody EnrolAssignmentsReq request,
                                                               @PathVariable(value = "idUser") Long idUser) {

        this.service.create(request, idUser);
        return ResponseEntity.status(201).body(request.toEnrolAssignmentsRequest());
    }

    @GetMapping("")
    public ResponseEntity<List<EnrolAssignmentsResponse>> listAllCourseMoodle() {

        return ResponseEntity.status(201).body(this.service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrolAssignmentsResponse> listCourseMoodleById(@PathVariable(value = "id") Long id) {

        return ResponseEntity.status(201).body(this.service.listById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<EnrolAssignmentsResponse> updateCourseById(@PathVariable(value = "id") Long id,
                                                                     @RequestBody EnrolAssignmentsReq request) {

        return ResponseEntity.status(200).body(this.service.updateById(id, request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable(value = "id") Long id) {

        this.service.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
