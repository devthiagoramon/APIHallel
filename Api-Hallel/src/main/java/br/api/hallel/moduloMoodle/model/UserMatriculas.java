package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mdl_user_enrolments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMatriculas {
    @Id
    private Long id;
    private Long status;
    private Long userid;
    private Long enrolid; //id matricula
    private Long timestart;
    private Long timeend;
    private Long modifierId;
    private Long timecreated;
    private Long timemodified;

}
