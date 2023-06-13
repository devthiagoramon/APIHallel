package br.api.hallel.moduloMoodle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
public class UserKeyComposta implements Serializable {

    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    public UserKeyComposta(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserKeyComposta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
