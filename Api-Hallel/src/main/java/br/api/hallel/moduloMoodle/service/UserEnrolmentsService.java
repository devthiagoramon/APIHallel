package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloMoodle.model.UserEnrolments;
import br.api.hallel.moduloMoodle.payload.request.UserEnrolmentsReq;
import br.api.hallel.moduloMoodle.payload.response.UserEnrolmentsResponse;
import br.api.hallel.moduloMoodle.repository.UserEnrolmentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEnrolmentsService {

    @Autowired
    UserEnrolmentsRepository repository;

    public UserEnrolments create(UserEnrolmentsReq request, Long id){
        UserEnrolmentsReq userRequest = request;
        userRequest.setUserid(id);
        userRequest.setTimemodified(0L);

        return this.repository.save(userRequest.toUserMatriculas());
    }

    public List<UserEnrolmentsResponse> listAll(){
        List<UserEnrolmentsResponse> responsesList = new ArrayList<>();
        this.repository.findAll().forEach(matriculas ->{
            UserEnrolmentsResponse responseObj = new UserEnrolmentsResponse();
            responsesList.add(responseObj.toResponse(matriculas));
        });

        log.info("Cursos Listado!");
        return responsesList;
    }

    public UserEnrolmentsResponse listById(Long id){
        Optional<UserEnrolments> optional = this.repository.findById(id);

        UserEnrolments matriculas = optional.isPresent() ? optional.get() : null;
        UserEnrolmentsResponse response = new UserEnrolmentsResponse();

        log.info(matriculas == null ? "Matricula listada!" : "Matricula com id "+id+" não encontrada!");

        return response.toResponse(matriculas);
    }

    public UserEnrolmentsResponse updateById(Long id, UserEnrolmentsReq request){

        UserEnrolments matriculaOld = request.toUserMatriculas();
        matriculaOld.setId(id);
        UserEnrolments matriculaResponse = this.listById(id) != null ? this.repository.save(matriculaOld) : null;

        return new UserEnrolmentsResponse().toResponse(matriculaResponse);
    }

    public void deleteById(Long id){
        if(listById(id) != null){
            this.repository.deleteById(id);
        }
    }

}
