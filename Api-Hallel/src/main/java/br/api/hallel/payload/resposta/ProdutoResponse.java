package br.api.hallel.payload.resposta;

import br.api.hallel.model.Membro;
import br.api.hallel.model.Produto;
import lombok.Data;

@Data
public class ProdutoResponse {
    private String id;
    private String nome;
    private String descricao;
    private String imagem;
    private Double preco;
    private String dataCadastrado;
    private String dataComprado;
    private Membro membro;

    public ProdutoResponse toProdutoResponse(Produto produto){

        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setImagem(produto.getImagem());
        response.setPreco(produto.getPreco());
        response.setDataCadastrado(produto.getDataCadastrado());
        response.setDataCadastrado(produto.getDataComprado());
        response.setMembro(produto.getMembro());

        return response;
    }

}

