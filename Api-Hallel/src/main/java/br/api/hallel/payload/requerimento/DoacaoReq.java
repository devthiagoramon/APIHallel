package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Doacao;
import br.api.hallel.model.TipoDoacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class DoacaoReq {

    @NotNull(message = "Email deve ser preenchido")
    @NotBlank(message = "Email deve ser preenchido")
    private String emailDoador;
    private String descricao;
    @NotNull(message = "tipo da doação deve ser preenchido")
    @NotBlank(message = "Tipo da doação deve ser preenchido")
    private TipoDoacao tipo;

    private String dataDoacao;
    @DecimalMin(value = "0.10", message = "Valor da doação tem que superior a 0.10 centavos")
    private double valorDoacao;

    public Doacao toDoacao() {
        Doacao doacaoDTO = new Doacao();
        doacaoDTO.setEmailDoador(this.getEmailDoador());
        doacaoDTO.setDescricao(this.getDescricao());
        doacaoDTO.setTipo(this.getTipo());
        doacaoDTO.setValorDoacao(this.getValorDoacao());
        doacaoDTO.setDataDoacao(getDataAtual());
        return doacaoDTO;
    }

    public String getDataAtual(){
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        return dataFormatada;
    }
}
