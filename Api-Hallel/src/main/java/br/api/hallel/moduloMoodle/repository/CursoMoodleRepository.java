package br.api.hallel.moduloMoodle.repository;

import br.api.hallel.moduloMoodle.model.CursoMoodle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoMoodleRepository extends JpaRepository<CursoMoodle, Long> {

}
