package br.api.hallel.controller;

import br.api.hallel.model.Associado;
import br.api.hallel.model.AtividadesCurso;
import br.api.hallel.model.Transacao;
import br.api.hallel.payload.requerimento.RecompensaRequest;
import br.api.hallel.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.service.AssociadoService;
import br.api.hallel.service.RecompensaService;
import br.api.hallel.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associado")
@CrossOrigin("*")
public class AssociadoController {


    @Autowired
    private AssociadoService service;
    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private MembroRepository repository;
    @Autowired
    private RecompensaService recompensaService;

    @GetMapping("")
    public List<Associado> listAllAssociados(){
        return this.service.listAllAssociado();
    }

    @GetMapping("/criar/{email}")
    public String createAssociado(@RequestBody Associado associado, @RequestBody Transacao transacao,
                                  @PathVariable String email ) {

        //PARA OCORRER A CRIAÇÃO DE ASSOCIADO, DEVE TER UMA TRANSAÇÃO

        //EXEMPLO DE COMO TAVA FUNCIONANDO

    /*

        //CRIANDO UAM TRANSAÇÃO

        Transacao transacao = new Transacao();
        transacao.setNomeTransacao("Transação muhaha");
        transacao.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        transacao.setDataExp("08/02/2023");

        //CRIANDO OBJ DE ASSOCIADO

        Associado associado = new Associado();

        //VERIFICANDO SE O MEMBRO EXISTE NO SISTEMA

        Optional<Membro> optional = this.repository.findByEmail("miguel@gmail.com");

        if(optional.isPresent()){

            //SE EXISTE, PEGA AS INFORMAÇÕES E BOTA NA DE ASSOCIADO, E SALVA NO 'createAssociado()'

            Membro membro = optional.get();
            associado.setId(membro.getId());
            associado.setNome(membro.getNome());
            associado.setEmail(membro.getEmail());
            associado.setStatus(membro.getStatus());

            associado.setTransacao(transacao);
            associado.setMensalidadePaga(true);
            associado.setDataNascimnetoAssociado(membro.getDataNascimento());
            associado.getTransacao().setDataExp(transacao.getDataExp());
            this.transacaoService.createAssociado(associado);

        }
    */

        return associado.toString();
    }

    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id){
        return this.service.listAssociadoById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id){
        this.service.deleteAssociado(id);
    }

    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado){
        return this.service.updateAssociadoById(id, associado);
    }

    @GetMapping("/getAllPagamentos")
    public List<AssociadoPagamentosRes> getAssociadosPagamento(){
        return this.service.getAllPagamentosAssociados();
    }

    @GetMapping("/getPagamentoAssociado/{id}")
    public AssociadoPagamentosRes getAssociadoPagamentoById(@PathVariable String id){
        return this.service.getAssociadoPagamentoById(id);
    }

    @GetMapping("/recompensa/{id}")
    public ResponseEntity<Associado> sendRecompensa(@RequestBody RecompensaRequest recompensa, @PathVariable String id) {

        Associado associado = this.service.listAssociadoById(id);

        return ResponseEntity.status(201).body(this.recompensaService.sendRecompensa(recompensa, associado));
    }

    @PostMapping("/curso/concluir/{idCurso}/{idAssociado}")
    public ResponseEntity<Associado> concluirCurso(@PathVariable String idCurso, @PathVariable String idAssociado) {

        return ResponseEntity.status(204).body(this.service.concluirCurso(idCurso,idAssociado));
    }

    @PostMapping("/curso/atividade/concluir/{idCurso}/{idAssociado}")
    public ResponseEntity<Associado> concluirAtvidade(@PathVariable String idCurso, @PathVariable String idAssociado
    , @RequestBody AtividadesCurso atividadesCurso){

        return ResponseEntity.status(204).body(this.service.concluirAtividade(atividadesCurso.getTituloAtividade() ,idAssociado, idCurso));
    }

    @GetMapping("/curso/desempenho/{id}")
    public ResponseEntity<Double> desempenhoCurso(@PathVariable String id){
        return ResponseEntity.status(200).body(this.service.desempenhoCurso(id));
    }
}
