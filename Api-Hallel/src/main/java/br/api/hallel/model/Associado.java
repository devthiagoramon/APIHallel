package br.api.hallel.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;

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
    private Double desempenho;
    private DesempenhoAssociadoCurso desAssociado;
    private ArrayList<Curso>historicoCurso;
    private HashMap<AtividadesCurso, Boolean> associadoAtividadesCurso;
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
