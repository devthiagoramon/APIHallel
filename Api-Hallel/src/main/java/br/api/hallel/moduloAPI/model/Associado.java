package br.api.hallel.moduloAPI.model;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

// Associado que precisa de uma transação para se tornar um associado
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class
Associado extends Membro {

    private Eventos eventoParticipando;
    private AssociadoStatus isAssociado;
    private Date dataExpiroAssociacao;
    private List<Recompensa> recompensas;
    private String dataNascimentoAssociado;
    private Double desempenhoTotalCursos;
    private ArrayList<Curso> historicoCurso;
    private CartaoCredito cartaoCredito;
    private HashMap<AtividadesCurso, Boolean> associadoAtividadesCurso;
    private ArrayList<Curso> cursosFavoritos;
    private ArrayList<Curso> cursosInscritos;
    private ArrayList<ModulosCurso> modulosCursosCompletos;
    private Boolean mensalidadePaga;
    private List<Date> mesesPagos;

    @DBRef
    private List<PagamentosAssociado> pagamentosAssociados;

}
