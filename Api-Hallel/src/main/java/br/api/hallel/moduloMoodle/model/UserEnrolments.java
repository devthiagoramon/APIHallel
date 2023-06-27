package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mdl_user_enrolments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEnrolments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
