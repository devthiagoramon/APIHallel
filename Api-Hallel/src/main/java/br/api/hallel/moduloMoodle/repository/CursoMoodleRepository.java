package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoMoodleRepository extends JpaRepository<CursoMoodle, Long> {
}
