package br.api.hallel.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

// Transacao para um membro se tornar associado
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transacao {

    @Id
    private String id;
    private String nomeTransacao;
    private String data;
    private MetodoPagamento metodoPagamento;
    private Double mensalidade;
    private Boolean pago;
    private Associado associado;


    public Associado setPagamentoEfetivo(Boolean isPago){

        if(isPago){
            System.out.println("Mensalidade Paga");
            return new Associado();
        }else{
            System.out.println("Não foi pago a mensalidade");
            return null;
        }

    }

}
