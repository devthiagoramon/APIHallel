package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membro extends Usuario implements UserDetails {
    
    private String nome;
    private String senha;
    private String email;
    private String dataNascimento;
    private StatusMembro status;
    private Integer idade;
    private String image;
    private String funcao;
    private Boolean doador;
    private Integer quantidadeDoacoes;

    private Map<String, Object> atributtes;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Membro(String nome, String senha, String email, String dataNascimento, StatusMembro status){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        getRoles().stream()
                .map(Role::getName)
                .forEach(eRole ->
                        authorities.add(new SimpleGrantedAuthority(eRole.toString())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
