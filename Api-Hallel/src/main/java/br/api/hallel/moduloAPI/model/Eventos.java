package br.api.hallel.moduloAPI.model;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentoEntradaEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventos {

    @Id
    private String id;
    private List<Associado> associadosParticipando;
    private String descricao;
    private Long quantidadeMembros;
    private Long maxMembros;
    private String titulo;
    private List<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private Date date;
    private LocalEvento localEvento;
    private String horario;
    private String imagem;
    private Long participantesEspeciais;
    private Boolean destaque;

    private List<DespesaEvento> despesas;
    private List<String> palestrantes;
    private List<PagamentoEntradaEvento> pagamentoEntradaEventoList;
    private List<ContribuicaoEvento> contribuicaoEventoList;
    public Eventos(String descricao, String titulo, Date date, LocalEvento localEvento, String horario, String imagem, List<String> palestrantes) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.date = date;
        this.localEvento = localEvento;
        this.horario = horario;
        this.imagem = imagem;
        this.palestrantes = palestrantes;
    }

    public Eventos(List<Associado> membroParticipando,
                   String descricao,
                   Long quantidadeMembros,
                   Long maxMembros,
                   String titulo,
                   ArrayList<Membro> integrantes,
                   MembroMarketing membroMarketing,
                   Administrador administrador,
                   Date date,
                   LocalEvento localEvento,
                   String horario,
                   String imagem,
                   Long participantesEspeciais,
                   boolean destaque,
                   List<String> palestrantes
                   ) {

        this.associadosParticipando = associadosParticipando;
        this.descricao = descricao;
        this.quantidadeMembros = quantidadeMembros;
        this.maxMembros = maxMembros;
        this.titulo = titulo;
        this.integrantes = integrantes;
        this.membroMarketing = membroMarketing;
        this.administrador = administrador;
        this.date = date;
        this.localEvento = localEvento;
        this.horario = horario;
        this.imagem = imagem;
        this.participantesEspeciais = participantesEspeciais;
        this.destaque = destaque;
        this.palestrantes = palestrantes;
    }


}
