package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.CourseEnrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEnrolRepository extends JpaRepository<CourseEnrol, Long> {
}
