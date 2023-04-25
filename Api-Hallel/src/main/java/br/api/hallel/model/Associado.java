package br.api.hallel.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

// Associado que precisa de uma transação para se tornar um associado
@Document
@Data
public class Associado extends Membro{

    private Eventos eventoParticipando;
    private Boolean isPago;
    private Transacao transacao;
    private AssociadoRole isAssociado;
    private String dataFinalPagamento;
    private Recompensa recompensa;
    private String dataNascimentoAssociado;

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
