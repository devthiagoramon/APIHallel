package br.api.hallel.moodle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name =  "user")
public class UserMoodle {
    @Id
    private Long id;
    private String username;
    private String firtname;
    private String lasname;
    private String email;
}
