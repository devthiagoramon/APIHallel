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
    private AssociadoRole isAssociado;


    public Associado(){

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
