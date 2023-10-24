package br.api.hallel.moduloMoodle.payload.request;

import br.api.hallel.moduloMoodle.model.UserMoodle;
import lombok.Data;

@Data
public class UserMoodleReq {

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

    public UserMoodle toUserMoodle(){
        UserMoodle user = new UserMoodle();
        user.setFirtname(getFirtname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setPhone1(getPhone1());
        user.setPhone2(getPhone2());
        user.setCity(getCity());
        user.setLang(getLang());
        user.setDescription(getDescription());

        return user;
    }

}
