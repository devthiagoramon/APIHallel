package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import lombok.Data;

import java.util.Date;

@Data
public class CriarEditarDoacaoReq {

    private Boolean isAnonimo;
    private String idDonator;
    private String nameDonator;
    private String telefoneDonator;
    private String emailDonator;
    private Double valor;
    private Date date;
    private Date dateEntregue;
    private Boolean isObjeto;
    private String nameObjeto;
    private StatusDoacao status;
    private String idEvento;
    private String idRetiro;

    public Doacao toDoacao() {
        return new Doacao(getIsAnonimo(),
                getIdDonator(),
                getNameDonator(),
                getTelefoneDonator(),
                getEmailDonator(),
                getValor(),
                getDate(),
                getDateEntregue(),
                getIsObjeto(),
                getNameObjeto(),
                getStatus(),
                getIdEvento(),
                getIdRetiro()
        );
    }
}
