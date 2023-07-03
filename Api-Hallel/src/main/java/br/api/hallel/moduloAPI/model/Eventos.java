package br.api.hallel.moduloAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventos {

    @Id
    private String id;
    private List<Associado> associadoParticipando;
    private String descricao;
    private Long quantidadeMembros;
    private Long maxMembros;
    private String titulo;
    private ArrayList<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private String date;
    private LocalEvento localEvento;
    private String horario;
    private String imagem;
    private Long participantesEspeciais;
    private Boolean destaque;

    public Eventos(String descricao, String titulo, String date, LocalEvento localEvento, String horario, String imagem) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.date = date;
        this.localEvento = localEvento;
        this.horario = horario;
        this.imagem = imagem;
    }

    public Eventos(List<Associado> associadoParticipando,
                   String descricao,
                   Long quantidadeMembros,
                   Long maxMembros,
                   String titulo,
                   ArrayList<Membro> integrantes,
                   MembroMarketing membroMarketing,
                   Administrador administrador,
                   String date,
                   LocalEvento localEvento,
                   String horario,
                   String imagem,
                   Long participantesEspeciais,
                   Boolean destaque) {

        this.associadoParticipando = associadoParticipando;
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
    }
}
