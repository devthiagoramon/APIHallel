package br.api.hallel.moduloAPI.payload.requerimento;

import br.api.hallel.moduloAPI.model.Produto;
import lombok.Data;

@Data
public class ProdutoReq {

    private String nome;
    private String descricao;
    private String imagem;
    private Double preco;
    private Double promocao;
    public Produto toProduto(){
        Produto produto = new Produto();

        produto.setNome(getNome());
        produto.setDescricao(getDescricao());
        produto.setImagem(getImagem());
        produto.setPreco(getPreco());
        produto.setPromocao(getPromocao());
        return produto;
    }

}
