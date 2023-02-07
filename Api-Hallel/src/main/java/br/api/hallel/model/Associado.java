package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

// Associado que precisa de uma transação para se tornar um associado
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Associado extends Membro{

    private Eventos eventoParticipando;
    private Boolean mensalidadePaga;
    private Transacao transacao;
    private Boolean isAssociado;

    public Boolean setMensalidadePaga(){

        if(transacao.getPago()){
            return true;
        }

        return false;

    }

}
