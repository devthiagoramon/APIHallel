package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.Produto;
import br.api.hallel.moduloAPI.payload.requerimento.ProdutoReq;
import br.api.hallel.moduloAPI.payload.resposta.ProdutoResponse;
import br.api.hallel.moduloAPI.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loja/produto")
@CrossOrigin("*")
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/create")
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoReq produtoReq) {
        return ResponseEntity.status(200).body(this.produtoService.createProduto(produtoReq));
    }

    @GetMapping("")
    public ResponseEntity<List<ProdutoResponse>> listProduto() {
        return ResponseEntity.status(201).body(this.produtoService.listAllProdutos());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> listProdutoById(@PathVariable(value = "idProduto") String idProduto) {
        return ResponseEntity.status(201).body(this.produtoService.listProdutoById(idProduto));
    }

    @PostMapping("/updateProduto/{idProduto}")
    public ResponseEntity<ProdutoResponse> updateProdutoById(@PathVariable(value = "idProduto") String idProduto,
                                                             @RequestBody ProdutoReq produtoReq) {
        return ResponseEntity.status(200).body(this.produtoService.updateProduto(idProduto, produtoReq));
    }

    @PostMapping("/deleteProduto/{idProduto}")
    public ResponseEntity<?> deleteProduto(@PathVariable(value = "idProduto") String idProduto) {
        this.produtoService.deleteProduto(idProduto);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getProdutoByMembro/{idMembro}")
    public ResponseEntity<List<ProdutoResponse>> listProdutoByMembros(@PathVariable(value = "idMembro") String idMembro) {
        return ResponseEntity.status(204).body(this.produtoService.getProdutosByMembro(idMembro));
    }

    @GetMapping("/getProdutos/asc")
    public ResponseEntity<List<ProdutoResponse>> listProdutoAsc() {
        return ResponseEntity.status(201).body(this.produtoService.getProdutoPrecoAsc());
    }

    @GetMapping("/getProdutos/desc")
    public ResponseEntity<List<ProdutoResponse>> listProdutoDesc() {
        return ResponseEntity.status(201).body(this.produtoService.getProdutoPrecoDesc());
    }

    @PostMapping("/add/promocao/{idProduto}")
    public ResponseEntity<ProdutoResponse> addPromotion(@PathVariable(value = "idProduto") String idProduto,
    @RequestParam(value ="promocao") Double promocao){
        return ResponseEntity.status(201).body(this.produtoService.adicionarPromocao(idProduto,promocao));
    }

    @GetMapping("/getProdutos/promocao")
    public ResponseEntity<List<ProdutoResponse>> listProdutoPromocao(){
        return ResponseEntity.status(201).body(this.produtoService.getProdutoPromocao());
    }

}
