package br.api.hallel.moduloAPI.controller;

import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.CursosAssociadoRes;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.repository.RoleRepository;
import br.api.hallel.moduloAPI.service.AssociadoService;
import br.api.hallel.moduloAPI.service.CursoService;
import br.api.hallel.moduloAPI.service.RecompensaService;
import br.api.hallel.moduloAPI.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/associados")
@CrossOrigin("*")
public class AssociadoController {

    @Autowired
    private AssociadoService service;
    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CursoService cursoService;
    @Autowired
    private MembroRepository repository;
    @Autowired
    private RecompensaService recompensaService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<Associado> listAllAssociados() {
        return this.service.listAllAssociado();
    }

    @PostMapping("/criar/{email}")
    public String createAssociado(@PathVariable String email) {


        //PARA OCORRER A CRIAÇÃO DE ASSOCIADO, DEVE TER UMA TRANSAÇÃO

        //EXEMPLO DE COMO TAVA FUNCIONANDO


        //CRIANDO UAM TRANSAÇÃO

        Transacao transacaoProv = new Transacao();
        transacaoProv.setNomeTransacao("Transação muhaha");
        transacaoProv.setMetodoPagamento(MetodoPagamento.CARTAO_CREDITO);
        transacaoProv.setDataExp("08/02/2023");

        //CRIANDO OBJ DE ASSOCIADO

        Associado associadoProv = new Associado();

        //VERIFICANDO SE O MEMBRO EXISTE NO SISTEMA

        Optional<Membro> optional = this.repository.findByEmail("tramon@gmail.com");

        if (optional.isPresent()) {

            //SE EXISTE, PEGA AS INFORMAÇÕES E BOTA NA DE ASSOCIADO, E SALVA NO 'createAssociado()'

            Membro membro = optional.get();
            associadoProv.setId(membro.getId());
            associadoProv.setNome(membro.getNome());
            associadoProv.setEmail(membro.getEmail());
            associadoProv.setStatus(membro.getStatus());

            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            Role associadoRole = roleRepository.findByName(ERole.ROLE_ASSOCIADO)
                    .orElseThrow(() -> new RuntimeException("Error: Role Associado is not found."));
            roles.add(associadoRole);

            associadoProv.setRoles(roles);
            associadoProv.setTransacao(transacaoProv);
            associadoProv.setMensalidadePaga(true);
            associadoProv.setDataNascimentoAssociado(membro.getDataNascimento());
            this.transacaoService.createAssociado(associadoProv);

        }

        return associadoProv.toString();
    }

    @GetMapping("/{id}")
    public Associado listAssociadoById(@PathVariable String id) {
        return this.service.listAssociadoById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteAssociadById(@PathVariable String id) {
        this.service.deleteAssociado(id);
    }

    @GetMapping("/update/{id}")
    public Associado updateAssociado(@PathVariable String id, @RequestBody Associado associado) {
        return this.service.updateAssociadoById(id, associado);
    }

    @GetMapping("/getAllPagamentos")
    public List<AssociadoPagamentosRes> getAssociadosPagamento() {
        return this.service.getAllPagamentosAssociados();
    }

    @GetMapping("/getPagamentoAssociado/{id}")
    public AssociadoPagamentosRes getAssociadoPagamentoById(@PathVariable String id) {
        return this.service.getAssociadoPagamentoById(id);
    }

    @GetMapping("/meusCursos/{id}")
    public ResponseEntity<List<CursosAssociadoRes>> listCursoCadastradoByAssociado(@PathVariable String id) {
        return ResponseEntity.status(201).body(this.cursoService.listCursoByAssociado(id));
    }

    @GetMapping("/pagos")
    public ResponseEntity<List<Associado>> listAssociadosPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPago());
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Associado>> listAssociadosPendentes() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByPendente());
    }

    @GetMapping("/naopagos")
    public ResponseEntity<List<Associado>> listAssociadosNaoPago() {
        return ResponseEntity.status(201).body(this.service.listAssociadosByNaoPago());
    }

    @GetMapping("/metodo/credito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoCredito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoCredito());
    }

    @GetMapping("/metodo/debito")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDebito() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDebito());
    }

    @GetMapping("/metodo/dinheiro")
    public ResponseEntity<List<Transacao>> listMetodoPagamentoDinheiro() {
        return ResponseEntity.status(201).body(this.service.listPagamentoDinheiro());
    }

    @GetMapping("/pagarAssociacao/{idAssociado}")
    public ResponseEntity<Boolean> pagarAssociacao(@PathVariable String idAssociado) {
        if (this.service.pagarAssociacao(idAssociado)) {
            return ResponseEntity.status(200).body(true);
        } else {
            return ResponseEntity.status(402).body(false);
        }
    }

}
