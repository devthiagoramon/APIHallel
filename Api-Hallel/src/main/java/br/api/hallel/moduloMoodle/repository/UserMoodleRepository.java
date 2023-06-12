package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.UserMoodle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserMoodleRepository extends JpaRepository<UserMoodle, Long> {

}
