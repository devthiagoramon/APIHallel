package br.api.hallel.controller;

import br.api.hallel.model.Produto;
import br.api.hallel.payload.requerimento.ProdutoReq;
import br.api.hallel.payload.resposta.ProdutoResponse;
import br.api.hallel.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loja/produto")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/create")
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoReq produtoReq) {
        return ResponseEntity.status(200).body(this.service.createProduto(produtoReq));
    }

    @GetMapping("")
    public ResponseEntity<List<ProdutoResponse>> listProduto() {
        return ResponseEntity.status(204).body(this.service.listAllProdutos());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> listProdutoById(@PathVariable(value = "idProduto") String idProduto) {
        return ResponseEntity.status(204).body(this.service.listProdutoById(idProduto));
    }

    @PostMapping("/updateProduto/{idProduto}")
    public ResponseEntity<ProdutoResponse> updateProdutoById(@PathVariable(value = "idProduto") String idProduto,
                                                             @RequestBody ProdutoReq produtoReq) {
        return ResponseEntity.status(200).body(this.service.updateProduto(idProduto, produtoReq));
    }

    @PostMapping("/deleteProduto/{idProduto}")
    public ResponseEntity<?> deleteProduto(@PathVariable(value = "idProduto") String idProduto) {
        this.service.deleteProduto(idProduto);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/getProdutoByMembro/{idMembro}")
    public ResponseEntity<List<ProdutoResponse>> listProdutoByMembros(@PathVariable(value = "idMembro") String idMembro) {
        return ResponseEntity.status(204).body(this.service.getProdutosByMembro(idMembro));
    }
}
