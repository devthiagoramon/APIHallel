package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.EnrolAssignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolAssignmentsRepository extends JpaRepository<EnrolAssignments, Long> {
}
