package br.api.hallel.moduloMoodle.controller;


import br.api.hallel.moduloMoodle.model.UserEnrolments;
import br.api.hallel.moduloMoodle.payload.request.UserEnrolmentsReq;
import br.api.hallel.moduloMoodle.payload.response.UserEnrolmentsResponse;
import br.api.hallel.moduloMoodle.service.UserEnrolmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario/matriculas/moodle")
public class UserEnrolmentsController {

    @Autowired
    private UserEnrolmentsService service;

    @PostMapping("/criar/{idUser}")
    public ResponseEntity<UserEnrolments> createCourseMoodle(@RequestBody UserEnrolmentsReq request,
                                                             @PathVariable(value = "idUser") Long idUser) {

        this.service.create(request, idUser);
        return ResponseEntity.status(201).body(request.toUserMatriculas());
    }

    @GetMapping("")
    public ResponseEntity<List<UserEnrolmentsResponse>> listAllCourseMoodle(){

        return ResponseEntity.status(201).body(this.service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEnrolmentsResponse> listCourseMoodleById(@PathVariable(value = "id") Long id){

        return ResponseEntity.status(201).body(this.service.listById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserEnrolmentsResponse> updateCourseById(@PathVariable(value = "id") Long id,
                                                                 @RequestBody UserEnrolmentsReq request){
        return ResponseEntity.status(200).body(this.service.updateById(id,request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable(value = "id") Long id){

        this.service.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
