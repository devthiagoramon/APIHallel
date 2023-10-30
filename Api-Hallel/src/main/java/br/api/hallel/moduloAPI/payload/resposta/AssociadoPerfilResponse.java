package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.model.Associado;
import br.api.hallel.moduloAPI.model.AssociadoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoPerfilResponse {
    private String id;
    private List<Date> mesesPagos;
    private AssociadoStatus isAssociado;
    private Date dataExpiroAssociado;
    private List<PagamentosAssociado> pagamentosAssociado;
    private String nome;
    private int idade;
    private Date dataNascimento;
    private String email;
    private String imagem;
    private String telefone;
    private String cpf;

    public AssociadoPerfilResponse toAssociadoPerfilResponse(Associado associado) {
        AssociadoPerfilResponse associadoPerfilResponse = new AssociadoPerfilResponse();
        associadoPerfilResponse.setId(associado.getId());
        associadoPerfilResponse.setMesesPagos(associado.getMesesPagos());
        associadoPerfilResponse.setIsAssociado(associado.getIsAssociado());
        associadoPerfilResponse.setDataExpiroAssociado(associado.getDataExpiroAssociacao());
        associadoPerfilResponse.setCpf(associado.getCpf());
        associadoPerfilResponse.setPagamentosAssociado(associado.getPagamentosAssociados());
        associadoPerfilResponse.setIdade(associado.getIdade());
        associadoPerfilResponse.setNome(associado.getNome());
        associadoPerfilResponse.setDataNascimento(associado.getDataNascimento());
        associadoPerfilResponse.setEmail(associado.getEmail());
        associadoPerfilResponse.setImagem(associado.getImage());
        associadoPerfilResponse.setTelefone(associado.getTelefone());
        return associadoPerfilResponse;
    }

}
