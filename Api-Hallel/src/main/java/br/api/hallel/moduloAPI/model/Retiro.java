package br.api.hallel.moduloAPI.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Retiro {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private List<Membro> participantes;
    private Long maxParticipantes;
    private List<Alimentos> alimentos;
    private LocalRetiro localRetiro;
    private String dataRetiro;
    private String horaRetiro;

    public Retiro(String nome,
                  String descricao,
                  List<Membro> participantes,
                  Long maxParticipantes,
                  List<Alimentos> alimentos,
                  LocalRetiro localRetiro,
                  String dataRetiro,
                  String horaRetiro) {
        this.nome = nome;
        this.descricao = descricao;
        this.participantes = participantes;
        this.maxParticipantes = maxParticipantes;
        this.alimentos = alimentos;
        this.localRetiro = localRetiro;
        this.dataRetiro = dataRetiro;
        this.horaRetiro = horaRetiro;
    }
}
