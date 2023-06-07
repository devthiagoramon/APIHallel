package br.api.hallel.moduloMoodle.service;

import br.api.hallel.moduloMoodle.model.UserMoodle;
import br.api.hallel.moduloMoodle.payload.UserMoodleReq;
import br.api.hallel.moduloMoodle.repository.UserMoodleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserMoodleService {
    @Autowired
    private UserMoodleRepository repository;

    public UserMoodle createUserMoodle(UserMoodleReq user){
        log.info("Usuário Criado!");
        return this.repository.save(user.toUserMoodle());
    }

    public List<UserMoodle> listAllUserMoodle(){
        log.info("Usuário listado");
        return this.repository.findAll();
    }

}

