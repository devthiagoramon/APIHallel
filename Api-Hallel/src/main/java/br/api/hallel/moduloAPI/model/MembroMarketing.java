package br.api.hallel.moduloAPI.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class MembroMarketing extends Membro {

    private String senhaAcesso;


    public MembroMarketing() {
    }

    public MembroMarketing(String nome, String senha, String email, Date dataAniversario,
            StatusMembro status, String senhaAcesso) {
        super(nome, senha, email, dataAniversario, status);
        this.senhaAcesso = senhaAcesso;
    }

}
