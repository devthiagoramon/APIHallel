package br.api.hallel.moodle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_enrolments")
public class UserMatriculas {
    @Id
    private Long id;
    private Long status;
    private Long userid;
    private Long enrolid; //id matricula

}
