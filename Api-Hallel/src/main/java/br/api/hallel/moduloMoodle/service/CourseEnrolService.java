package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloMoodle.model.CourseEnrol;
import br.api.hallel.moduloMoodle.payload.request.CourseEnrolRequest;
import br.api.hallel.moduloMoodle.payload.response.CourseEnrolResponse;
import br.api.hallel.moduloMoodle.repository.CourseEnrolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseEnrolService {

    @Autowired
    private CourseEnrolRepository repository;

    public CourseEnrol create(Long idUser, Long idCourse) {
        CourseEnrolRequest request = new CourseEnrolRequest();
        request.setCourseId(idCourse);
        request.setStatus(1L);
        request.setEnrol("manual");

        return this.repository.save(request.toCourseEnrolRequest());
    }

    public List<CourseEnrolResponse> listAllCourses() {

        List<CourseEnrolResponse> responseList = new ArrayList<>();

        this.repository.findAll().forEach(courseEnrol -> {
            CourseEnrolResponse response = new CourseEnrolResponse();
            responseList.add(response.toCourseEnrolResponse(courseEnrol));

        });

        return responseList;
    }

    public CourseEnrolResponse listById(Long id) {
        Optional<CourseEnrol> optional = this.repository.findById(id);

        CourseEnrol cursosEnrol = optional.isPresent() ? optional.get() : null;
        CourseEnrolResponse response = new CourseEnrolResponse();

        log.info(cursosEnrol == null ? "Matricula de um curso listada!" : "Matricula com id " + id + " não encontrada!");

        return response.toCourseEnrolResponse(cursosEnrol);
    }

    public CourseEnrolResponse updateById(Long id, CourseEnrolRequest request) {

        CourseEnrol matriculaOld = request.toCourseEnrolRequest();
        matriculaOld.setId(id);
        CourseEnrol matriculaResponse = this.listById(id) != null ? this.repository.save(matriculaOld) : null;

        return new CourseEnrolResponse().toCourseEnrolResponse(matriculaResponse);
    }

    public void deleteById(Long id) {
        if(listById(id) != null){
            this.repository.deleteById(id);
        }
    }

}
