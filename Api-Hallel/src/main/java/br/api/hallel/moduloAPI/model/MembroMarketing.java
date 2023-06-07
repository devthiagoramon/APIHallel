package br.api.hallel.moduloAPI.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class MembroMarketing extends Membro {

    private String senhaAcesso;


    public MembroMarketing() {
    }

    public MembroMarketing(String nome, String senha, String email, String dataAniversario,
            StatusMembro status, String senhaAcesso) {
        super(nome, senha, email, dataAniversario, status);
        this.senhaAcesso = senhaAcesso;
    }

}
