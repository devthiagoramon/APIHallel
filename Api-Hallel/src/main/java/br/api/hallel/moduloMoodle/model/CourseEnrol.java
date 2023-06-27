package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mdl_enrol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long status;
    private String enrol;
    private Long courseId;
    private Long timecreated;
    private Long timemodified;


}
