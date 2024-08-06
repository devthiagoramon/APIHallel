package br.api.hallel.moduloAPI.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "doacaoh")
public class Doacao {
    @Id
    private String id;

    @Schema(description = "Se verdadeiro, a doação é anonima!")
    private Boolean isAnonimo;

    @Schema(description = "Id do usuário cadastrado no sistema que fez a doação")
    private String idDonator;

    @Schema(description = "Caso a doação seja anonima, o nome da pessoa", example = "Felipe Soares")
    private String nameDonator;

    @Schema(description = "Caso a doação seja anonima, o telefone da pessoa", example = "(DDD) 998324223")
    private String telefoneDonator;

    @Schema(description = "Caso a doação seja anonima, o email da pessoa", example = "felipesoares@gmail.com")
    private String emailDonator;

    @Schema(description = "Valor da doação / Quantidade de objetos", example = "R$ 10,00 / 10")
    private Double valor;

    @Schema(description = "Data da doação", example = "2024-09-20")
    private Date date;

    @Schema(description = "Caso seja objeto, data da entrega do objeto", example = "2024-09-30")
    private Date dateEntregue;

    @Schema(description = "Caso verdadeiro, a doação é um objeto")
    private Boolean isObjeto;

    @Schema(description = "Caso seja objeto, o nome do objeto", example = "Papel A4")
    private String nameObjeto;

    @Schema(description = "Status da doação")
    private StatusDoacao status;
    @Schema(description = "Id do evento que foi feito a doação")
    private String idEvento;
    @Schema(description = "Id do retiro que foi feito a doação")
    private String idRetiro;

    public Doacao() {
    }

    public Doacao(String id, Boolean isAnonimo, String idDonator,
                  String nameDonator, String telefoneDonator,
                  String emailDonator, Double valor, Date date,
                  Date dateEntregue, Boolean isObjeto,
                  String nameObjeto,
                  StatusDoacao status, String idEvento,
                  String idRetiro) {
        this.id = id;
        this.isAnonimo = isAnonimo;
        this.idDonator = idDonator;
        this.nameDonator = nameDonator;
        this.telefoneDonator = telefoneDonator;
        this.emailDonator = emailDonator;
        this.valor = valor;
        this.date = date;
        this.dateEntregue = dateEntregue;
        this.isObjeto = isObjeto;
        this.nameObjeto = nameObjeto;
        this.status = status;
        this.idEvento = idEvento;
        this.idRetiro = idRetiro;
    }

    public Doacao(Boolean isAnonimo, String idDonator,
                  String nameDonator,
                  String telefoneDonator, String emailDonator,
                  Double valor, Date date, Date dateEntregue,
                  Boolean isObjeto, String nameObjeto,
                  StatusDoacao status, String idEvento,
                  String idRetiro) {
        this.isAnonimo = isAnonimo;
        this.idDonator = idDonator;
        this.nameDonator = nameDonator;
        this.telefoneDonator = telefoneDonator;
        this.emailDonator = emailDonator;
        this.valor = valor;
        this.date = date;
        this.dateEntregue = dateEntregue;
        this.isObjeto = isObjeto;
        this.nameObjeto = nameObjeto;
        this.status = status;
        this.idEvento = idEvento;
        this.idRetiro = idRetiro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAnonimo() {
        return isAnonimo;
    }

    public void setAnonimo(Boolean anonimo) {
        isAnonimo = anonimo;
    }

    public String getIdDonator() {
        return idDonator;
    }

    public void setIdDonator(String idDonator) {
        this.idDonator = idDonator;
    }

    public String getNameDonator() {
        return nameDonator;
    }

    public void setNameDonator(String nameDonator) {
        this.nameDonator = nameDonator;
    }

    public String getTelefoneDonator() {
        return telefoneDonator;
    }

    public void setTelefoneDonator(String telefoneDonator) {
        this.telefoneDonator = telefoneDonator;
    }

    public String getEmailDonator() {
        return emailDonator;
    }

    public void setEmailDonator(String emailDonator) {
        this.emailDonator = emailDonator;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateEntregue() {
        return dateEntregue;
    }

    public void setDateEntregue(Date dateEntregue) {
        this.dateEntregue = dateEntregue;
    }

    public Boolean getObjeto() {
        return isObjeto;
    }

    public void setObjeto(Boolean objeto) {
        isObjeto = objeto;
    }

    public String getNameObjeto() {
        return nameObjeto;
    }

    public void setNameObjeto(String nameObjeto) {
        this.nameObjeto = nameObjeto;
    }

    public StatusDoacao getStatus() {
        return status;
    }

    public void setStatus(StatusDoacao status) {
        this.status = status;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdRetiro() {
        return idRetiro;
    }

    public void setIdRetiro(String idRetiro) {
        this.idRetiro = idRetiro;
    }
}
