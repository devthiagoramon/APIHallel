package br.api.hallel.service.interfaces;

import br.api.hallel.model.Produto;
import br.api.hallel.payload.requerimento.ProdutoReq;
import br.api.hallel.payload.resposta.ProdutoResponse;

import java.util.List;

public interface ProdutoInterface {

    Produto createProduto(ProdutoReq produto);
    List<ProdutoResponse> listAllProdutos();
    ProdutoResponse listProdutoById(String id);
    ProdutoResponse updateProduto(String id, ProdutoReq produto);
    void deleteProduto(String id);
    List<ProdutoResponse> getProdutosByMembro(String id);
    List<ProdutoResponse> getProdutoPrecoDesc();
    List<ProdutoResponse> getProdutoPrecoAsc();
    ProdutoResponse adicionarPromocao(String id, Double valorPromocao);
    List<ProdutoResponse> getProdutoPromocao();
}
