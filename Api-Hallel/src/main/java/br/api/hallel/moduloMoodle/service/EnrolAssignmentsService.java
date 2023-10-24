package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloMoodle.model.EnrolAssignments;
import br.api.hallel.moduloMoodle.payload.request.EnrolAssignmentsReq;
import br.api.hallel.moduloMoodle.payload.response.EnrolAssignmentsResponse;
import br.api.hallel.moduloMoodle.repository.EnrolAssignmentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EnrolAssignmentsService {

    @Autowired
    private EnrolAssignmentsRepository repository;

    public EnrolAssignments create(EnrolAssignmentsReq request, Long id) {
        EnrolAssignmentsReq enrolRequest = request;
        enrolRequest.setUserid(id);
        enrolRequest.setSortorder(0L);
        enrolRequest.setItemid(0L);
        enrolRequest.setTimemodified(0L);

        return this.repository.save(enrolRequest.toEnrolAssignmentsRequest());
    }

    public List<EnrolAssignmentsResponse> listAll() {
        List<EnrolAssignmentsResponse> responsesList = new ArrayList<>();

        this.repository.findAll().forEach(enrolAssignments -> {
            EnrolAssignmentsResponse responseObj = new EnrolAssignmentsResponse();
            responsesList.add(responseObj.toEnrolAssignmentsResponse(enrolAssignments));
        });

        log.info("Cursos Listado!");
        return responsesList;
    }

    public EnrolAssignmentsResponse listById(Long id) {
        Optional<EnrolAssignments> optional = this.repository.findById(id);

        EnrolAssignments enrolAssignments = optional.isPresent() ? optional.get() : null;
        EnrolAssignmentsResponse response = new EnrolAssignmentsResponse();

        log.info(enrolAssignments == null ? "Matricula listada!" : "Matricula com id " + id + " não encontrada!");

        return response.toEnrolAssignmentsResponse(enrolAssignments);
    }

    public EnrolAssignmentsResponse updateById(Long id, EnrolAssignmentsReq request) {

        EnrolAssignments enrolAssignmentsOld = request.toEnrolAssignmentsRequest();
        enrolAssignmentsOld.setId(id);
        EnrolAssignments enrolResponse =
                this.listById(id) != null ? this.repository.save(enrolAssignmentsOld) : null;

        return new EnrolAssignmentsResponse().toEnrolAssignmentsResponse(enrolResponse);
    }

    public void deleteById(Long id) {
        if(listById(id) != null){
            this.repository.deleteById(id);
        }
    }

}
