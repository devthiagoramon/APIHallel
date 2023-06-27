package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.UserEnrolments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEnrolmentsRepository extends JpaRepository<UserEnrolments, Long> {
}
