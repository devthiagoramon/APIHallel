package br.api.hallel.payload.requerimento;

import br.api.hallel.model.Produto;
import lombok.Data;

@Data
public class ProdutoReq {

    private String nome;
    private String descricao;
    private String imagem;
    private Double preco;

    public Produto toProduto(){
        Produto produto = new Produto();

        produto.setNome(getNome());
        produto.setDescricao(getDescricao());
        produto.setImagem(getImagem());
        produto.setPreco(getPreco());

        return produto;
    }

}
