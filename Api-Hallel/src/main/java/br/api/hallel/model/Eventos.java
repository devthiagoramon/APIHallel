package br.api.hallel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
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
    private Double despesas;

    private ArrayList<Membro> integrantes;
    private MembroMarketing membroMarketing;
    private Administrador administrador;
    private String date;
    private String localidade;
    private String horario;
    private String imagem;
    private Long participantesEspeciais;

}
