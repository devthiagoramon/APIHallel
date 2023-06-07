package br.api.hallel.moduloAPI.payload.resposta;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Produto;
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
    private Integer estoque;
    private Double promocao;

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
        response.setEstoque(produto.getEstoque());
        response.setPreco(produto.getPromocao());

        return response;
    }

}

