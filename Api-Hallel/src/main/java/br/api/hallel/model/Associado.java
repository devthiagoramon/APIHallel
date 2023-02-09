package br.api.hallel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

// Associado que precisa de uma transação para se tornar um associado
@Document
@Getter
@Setter
public class Associado extends Membro{

    private Eventos eventoParticipando;
    private Boolean mensalidadePaga;
    private Transacao transacao;
    private Boolean isAssociado;

    public Associado(){

    }

    public Boolean setMensalidadePaga(){

        if(transacao.getPago()){
            return true;
        }

        return false;

    }

    @Override
    public String toString() {
        return "Associado{" +
                "eventoParticipando=" + eventoParticipando +
                ", mensalidadePaga=" + mensalidadePaga +
                ", transacao=" + transacao +
                ", isAssociado=" + isAssociado +
                '}';
    }
}
