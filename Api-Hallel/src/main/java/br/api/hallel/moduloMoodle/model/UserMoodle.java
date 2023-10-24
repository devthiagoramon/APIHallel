package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name =  "mdl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMoodle {

    @EmbeddedId
    private UserKeyComposta id;
    private String firtname;
    private String lastname;
    private String email;
    private String password;
    private String phone1;
    private String phone2;
    private String city;
    private String country;
    private String lang;
    private String description;
    private int descriptionformat;
    private byte confirmed;
    private byte policyagreed;
    private byte deleted;
    private byte suspended;

}
