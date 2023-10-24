package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mdl_role_assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolAssignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roleid;
    private Long contextid;
    private Long userid;
    private Long timemodified;
    private Long modifierid;
    private Long itemid;
    private Long sortorder;


}
