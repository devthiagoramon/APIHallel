package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mdl_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoMoodle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String shortname;
    private String idnumber;
    private String summary;
    private Integer summaryformat;
    private String format;
    private Integer showgrades;
    private boolean enablecompletion;
    private boolean completionnotify;


}
