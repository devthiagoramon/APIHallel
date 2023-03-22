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
    private Boolean isPago;
    private Transacao transacao;
    private AssociadoRole isAssociado;

    private String dataFinalPagamento;

    public Associado(){

    }


    @Override
    public String toString() {
        return "Associado{" +
                "eventoParticipando=" + eventoParticipando +
                ", mensalidadePaga=" + isPago +
                ", transacao=" + transacao +
                ", isAssociado=" + isAssociado +
                '}';
    }
}
