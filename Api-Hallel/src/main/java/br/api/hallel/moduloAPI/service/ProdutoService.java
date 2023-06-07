package br.api.hallel.moduloAPI.service;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.Produto;
import br.api.hallel.moduloAPI.payload.requerimento.ProdutoReq;
import br.api.hallel.moduloAPI.payload.resposta.ProdutoResponse;
import br.api.hallel.moduloAPI.repository.ProdutoRepository;
import br.api.hallel.moduloAPI.service.interfaces.ProdutoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProdutoService implements ProdutoInterface {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private MembroService membroService;

    @Override
    public Produto createProduto(ProdutoReq produto) {
        log.info("Produto Criado!");
        return this.repository.insert(produto.toProduto());
    }

    @Override
    public List<ProdutoResponse> listAllProdutos() {

        List<ProdutoResponse> listaResponse = new ArrayList<>();

        this.repository.findAll().forEach(produto -> {
            listaResponse.add(new ProdutoResponse().toProdutoResponse(produto));
        });

        log.info("Produtos listados");
        return listaResponse;
    }

    @Override
    public ProdutoResponse listProdutoById(String id) {
        ProdutoResponse response = new ProdutoResponse();
        Optional<Produto> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Produto produto = optional.get();

            log.info("Produto listado");
            return response.toProdutoResponse(produto);
        }

        return null;
    }

    @Override
    public ProdutoResponse updateProduto(String id, ProdutoReq produto) {

        Produto produtoAux = produto.toProduto();
        produtoAux.setId(id);
        Produto produtoResponse = this.listProdutoById(id) == null ?
                this.repository.save(produtoAux) : null;

        log.info("Produto Atualizado!");
        return new ProdutoResponse().toProdutoResponse(produtoResponse);
    }

    @Override
    public void deleteProduto(String id) {

        if (this.listProdutoById(id) != null) {

            log.info("Produto removido!");
            this.repository.deleteById(id);
        }

    }

    @Override
    public List<ProdutoResponse> getProdutosByMembro(String idMembro) {

        Membro membroAux = this.membroService.listMembroId(idMembro);

        log.info("Produtos listado por membros");
        return this.listAllProdutos().stream().map(produto -> {
            if (produto.getMembro() == membroAux) {
                return produto;
            } else {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponse> getProdutoPrecoDesc() {

        if (this.listAllProdutos() != null) {

            List<ProdutoResponse> produtoResponseList = new ArrayList<>();

            listAllProdutos().stream().sorted((o1,o2) -> o2.getPreco().compareTo(o1.getPreco()))
                    .forEach(produtos ->{
                        produtoResponseList.add(produtos);
                    });

            return produtoResponseList;
        }

        return null;
    }

    @Override
    public List<ProdutoResponse> getProdutoPrecoAsc() {

        if (this.listAllProdutos() != null) {

            List<ProdutoResponse> produtoResponseList = new ArrayList<>();

            listAllProdutos().stream().sorted(Comparator.comparing(ProdutoResponse::getPreco))
                    .forEach(produtos ->{
                produtoResponseList.add(produtos);
            });

            return produtoResponseList;
        }


        return null;
    }

    @Override
    public ProdutoResponse adicionarPromocao(String id, Double valorPromocao) {

        if(this.listProdutoById(id) != null){
            ProdutoResponse response = this.listProdutoById(id);

            ProdutoReq produtoReq = new ProdutoReq();
            produtoReq.setNome(response.getNome());
            produtoReq.setImagem(response.getImagem());
            produtoReq.setDescricao(response.getDescricao());
            produtoReq.setPromocao(valorPromocao);
            produtoReq.setPreco((produtoReq.getPreco()) * (valorPromocao/100));


            return updateProduto(id,produtoReq);
        }

        return null;
    }

    @Override
    public List<ProdutoResponse> getProdutoPromocao() {

        if (this.listAllProdutos() != null) {

            List<ProdutoResponse> produtoResponseList = new ArrayList<>();

            listAllProdutos().stream().filter(produtoResponse -> produtoResponse.getPromocao() != null)
                    .sorted(Comparator.comparing(ProdutoResponse::getPromocao))
                    .forEach(produtos ->{
                produtoResponseList.add(produtos);
            });

            return produtoResponseList;
        }


        return null;
    }
}
