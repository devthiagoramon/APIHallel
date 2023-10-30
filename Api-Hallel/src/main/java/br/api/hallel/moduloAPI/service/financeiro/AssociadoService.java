package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.financeiroNovo.model.CodigoEntradaFinanceiro;
import br.api.hallel.moduloAPI.financeiroNovo.model.PagamentosAssociado;
import br.api.hallel.moduloAPI.financeiroNovo.payload.response.PagamentoAssociadoResponse;
import br.api.hallel.moduloAPI.financeiroNovo.service.PagamentoAssociadoService;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.PagamentoAssociadoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.PagarAssociacaoRequest;
import br.api.hallel.moduloAPI.payload.requerimento.VirarAssociadoRequest;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPagamentosRes;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoPerfilResponse;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponseList;
import br.api.hallel.moduloAPI.payload.resposta.PagamentoAssociadoPerfilResponse;
import br.api.hallel.moduloAPI.repository.AssociadoRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.repository.RoleRepository;
import br.api.hallel.moduloAPI.service.interfaces.AssociadoInterface;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
@Log

public class AssociadoService implements AssociadoInterface {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private MembroRepository membroRepository;
    @Autowired
    private PagamentoAssociadoService pagamentoAssociadoService;

    @Autowired
    private RoleRepository roleRepository;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<Associado> listAllAssociado() {
        return this.associadoRepository.findAll().isEmpty() ? this.associadoRepository.findAll() : null;
    }

    @Override
    public List<AssociadoResponseList> listAllAssociadoByMesAno(String mes, String ano) {
        List<Associado> associadosDB = this.associadoRepository.findAll();
        List<AssociadoResponseList> associadosLoadad = new ArrayList<>();

        for (Associado associado : associadosDB) {
            boolean hasPago = false;
            AssociadoResponseList associadoResponseProv = new AssociadoResponseList();
            associadoResponseProv.setId(associado.getId());
            associadoResponseProv.setNome(associado.getNome());
            if (associado.getMesesPagos() != null) {
                for (Date mesPagamento : associado.getMesesPagos()) {
                    if (mesPagamento != null) {
                        if (formatter.format(mesPagamento).substring(3).equals(mes + "/" + ano)) {
                            hasPago = true;
                            associadoResponseProv.setStatus(AssociadoStatus.PAGO);
                            associadoResponseProv.setDataPagamento(mesPagamento);
                        }
                    }
                }
            }
            if (!hasPago) {
                associadoResponseProv.setStatus(AssociadoStatus.NAO_PAGO);
            }
            associadosLoadad.add(associadoResponseProv);
        }
        Collections.sort(associadosLoadad);
        return associadosLoadad;
    }

    //LISTA TODOS OS ASSOCIADOS
    @Override
    public Associado listAssociadoById(String id) throws AssociadoNotFoundException {

        log.info("ASSOCIADO LISTADO!");

        if (this.associadoRepository.findById(id).isPresent()) {
            return this.associadoRepository.findById(id).get();

        } else {
            throw new AssociadoNotFoundException("Associado não encontrado");

        }


    }

    //DELETA UM ASSOCIADO PELO ID DELE
    @Override
    public void deleteAssociado(String id) throws AssociadoNotFoundException {
        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associado = optional.get();

            log.info("ASSOCIADO REMOVIDO!");

            this.associadoRepository.delete(associado);
        }

        throw new AssociadoNotFoundException("Não foi possível remover o Associado Id("+id+")");

    }

    //ATUALIZA INFORMAÇÕES SOBRE O ASSOCIADO
    @Override
    public Associado updateAssociadoById(String id, Associado associado) throws AssociadoNotFoundException {

        Optional<Associado> optional = this.associadoRepository.findById(id);

        if (optional.isPresent()) {
            Associado associadoOptional = optional.get();

            associadoOptional = associado;

            log.info("ASSOCIADO ATUALIZADO!");

            return this.associadoRepository.save(associado);
        }
        throw new AssociadoNotFoundException("Não foi possível remover o Associado Id("+id+")");

    }

    @Override
    public List<AssociadoPagamentosRes> getAllPagamentosAssociados() {

        List<Associado> associados = this.associadoRepository.findAll();

        List<AssociadoPagamentosRes> pagamentosRes = new ArrayList<>();

        associados.forEach(associado -> {
            AssociadoPagamentosRes pagamento = new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsAssociado());
            pagamentosRes.add(pagamento);
        });

        return pagamentosRes;
    }

    @Override
    public AssociadoPagamentosRes getAssociadoPagamentoById(String id) throws AssociadoNotFoundException {
        Associado associado = listAssociadoById(id);

        return new AssociadoPagamentosRes(associado.getNome(), associado.getEmail(), associado.getIsAssociado());
    }

    @Override
    public Associado findByEmail(String email) {

        Optional<Associado> optional = associadoRepository.findByEmail(email);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    @Override
    public List<Associado> listAssociadosByPago() {

        return this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PAGO).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PAGO) : null;
    }

    @Override
    public List<Associado> listAssociadosByPendente() {

        return associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PENDENTE).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.PENDENTE) : null;
    }

    @Override
    public List<Associado> listAssociadosByNaoPago() {
        return this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.NAO_PAGO).isEmpty() ?
                this.associadoRepository.findByIsAssociadoEquals(AssociadoStatus.NAO_PAGO) : null;
    }

    @Override
    public List<Transacao> listPagamentoCredito() {
        List<Transacao> transacaos = new ArrayList<>();
//        associadoRepository.findAll().stream().forEach(associado -> {
//            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.CARTAO_CREDITO) {
//                transacaos.add(associado.getTransacao());
//            }
//        });
        return transacaos;
    }

    @Override
    public List<Transacao> listPagamentoDebito() {
        List<Transacao> transacaos = new ArrayList<>();
//        associadoRepository.findAll().stream().forEach(associado -> {
//            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.CARTAO_DEBITO) {
//                transacaos.add(associado.getTransacao());
//            }
//        });
        return transacaos;
    }

    @Override
    public List<Transacao> listPagamentoDinheiro() {
        List<Transacao> transacaoList = new ArrayList<>();
//        this.associadoRepository.findAll().stream().forEach(associado -> {
//            if (associado.getTransacao().getMetodoPagamento() == MetodoPagamento.DINHEIRO) {
//                transacaoList.add(associado.getTransacao());
//            }
//        });
        return transacaoList;
    }

    @Override
    public Boolean pagarAssociacao(PagarAssociacaoRequest pagarAssociacaoRequest) {
        Optional<Associado> optional = this.associadoRepository.findById(pagarAssociacaoRequest.getIdAssociado());

        if (optional.isPresent()) {
            Associado associado = optional.get();

            LocalDate dataExpiroLocalDate = associado.getDataExpiroAssociacao().toInstant().atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
            LocalDate dataUltimoPagamento = associado.getPagamentosAssociados().get(associado.getPagamentosAssociados().size() - 1).getDataPaga().toInstant().atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
            Period period = Period.between(dataUltimoPagamento, dataExpiroLocalDate);

            if (period.getMonths() < 2) {
                PagamentoAssociadoRequest pagamentoAssociadoRequest = new PagamentoAssociadoRequest();
                CodigoEntradaFinanceiro codigoEntradaFinanceiro = new CodigoEntradaFinanceiro();
                codigoEntradaFinanceiro.setId("Teste");
                codigoEntradaFinanceiro.setNomeCodigo("PagamentoAssociado");
                codigoEntradaFinanceiro.setNumeroCodigo(10.3);
                pagamentoAssociadoRequest.setCodigo(codigoEntradaFinanceiro);
                pagamentoAssociadoRequest.setMetodoPagamentoNum(pagarAssociacaoRequest.getNumMetodoPagamento());
                LocalDate dataAtual = LocalDate
                        .of(Integer.parseInt(pagarAssociacaoRequest.getAno()),
                                Integer.parseInt(pagarAssociacaoRequest.getMes()),
                                LocalDate.now(ZoneId.of("America/Puerto_Rico")).getDayOfMonth());
                pagamentoAssociadoRequest
                        .setDataPaga(Date.from(dataAtual.atStartOfDay(ZoneId.of("America/Puerto_Rico"))
                                .toInstant()));
                PagamentosAssociado pagamentoAssociadoOBJ = pagamentoAssociadoService.cadastrar(pagamentoAssociadoRequest);
                associado.setDataExpiroAssociacao(getDataExpiroAssociacao(pagamentoAssociadoOBJ));
                associado.getPagamentosAssociados().add(pagamentoAssociadoOBJ);
            } else {
                for (int i = 1; i <= period.getMonths(); i++) {
                    PagamentoAssociadoRequest pagamentoAssociadoRequest = new PagamentoAssociadoRequest();
                    CodigoEntradaFinanceiro codigoEntradaFinanceiro = new CodigoEntradaFinanceiro();
                    codigoEntradaFinanceiro.setId("Teste");
                    codigoEntradaFinanceiro.setNomeCodigo("PagamentoAssociado");
                    codigoEntradaFinanceiro.setNumeroCodigo(10.3);
                    pagamentoAssociadoRequest.setCodigo(codigoEntradaFinanceiro);
                    pagamentoAssociadoRequest.setMetodoPagamentoNum(pagarAssociacaoRequest.getNumMetodoPagamento());
                    LocalDate dataAtualFor = LocalDate
                            .of(Integer.parseInt(pagarAssociacaoRequest.getAno()),
                                    Integer.parseInt(pagarAssociacaoRequest.getMes()),
                                    LocalDate.now(ZoneId.of("America/Puerto_Rico")).getDayOfMonth());
                    dataAtualFor = dataAtualFor.minusMonths(period.getMonths() - i);
                    pagamentoAssociadoRequest
                            .setDataPaga(Date.from(dataAtualFor.atStartOfDay(ZoneId.of("America/Puerto_Rico"))
                                    .toInstant()));
                    PagamentosAssociado pagamentoAssociadoOBJ = pagamentoAssociadoService.cadastrar(pagamentoAssociadoRequest);
                    associado.setDataExpiroAssociacao(getDataExpiroAssociacao(pagamentoAssociadoOBJ));
                    associado.getPagamentosAssociados().add(pagamentoAssociadoOBJ);
                }
            }

            associado.getMesesPagos().add(new Date());

            this.associadoRepository.save(associado);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean pagarAlguemAssociado(PagarAssociacaoRequest pagarRequest) {

        Optional<Associado> optional = this.associadoRepository.findByEmail(pagarRequest.getEmail());

        if (optional.isPresent()) {
            Associado associado = optional.get();

            PagamentoAssociadoRequest pagamentoAssociadoRequest = new PagamentoAssociadoRequest();
            CodigoEntradaFinanceiro codigoEntradaFinanceiro = new CodigoEntradaFinanceiro();
            codigoEntradaFinanceiro.setId("Teste");
            codigoEntradaFinanceiro.setNomeCodigo("PagamentoAssociado");
            codigoEntradaFinanceiro.setNumeroCodigo(10.3);
            pagamentoAssociadoRequest.setCodigo(codigoEntradaFinanceiro);
            pagamentoAssociadoRequest.setMetodoPagamentoNum(pagarRequest.getNumMetodoPagamento());
            PagamentosAssociado pagamentoAssociadoOBJ = pagamentoAssociadoService.cadastrar(pagamentoAssociadoRequest);

            this.associadoRepository.save(associado);
            return true;
        }


        return false;
    }

    @Override
    public Boolean criarAssociado(VirarAssociadoRequest virarAssociadoRequest) throws AssociadoNotFoundException {
        PagamentoAssociadoRequest pagamentoAssociadoRequest = new PagamentoAssociadoRequest();

        Optional<Membro> optionalMembro = membroRepository.findById(virarAssociadoRequest.getIdMembro());
        boolean retorno = true;
        if (optionalMembro.isEmpty()) {
            retorno = false;
            throw new AssociadoNotFoundException("Membro não encontrado");
        }

        Membro membro = optionalMembro.get();

        /*
            Configurando o codigo de entrada do financeiro e salvando no PagamentoAssociadoRequest e
            em seguida inserindo esse objeto no banco de dados
         */

        pagamentoAssociadoRequest.setMetodoPagamentoNum(virarAssociadoRequest.getMetodoPagamentoNum());
        pagamentoAssociadoRequest.setPara(null);
        CodigoEntradaFinanceiro codigoEntradaFinanceiro = new CodigoEntradaFinanceiro();
        codigoEntradaFinanceiro.setId("643536fash27743");
        codigoEntradaFinanceiro.setNomeCodigo("Virando associado");
        codigoEntradaFinanceiro.setNumeroCodigo(2.3);
        pagamentoAssociadoRequest.setCodigo(codigoEntradaFinanceiro);
        LocalDate dataToPaga = LocalDate.now(ZoneId.of("America/Puerto_Rico"));
        pagamentoAssociadoRequest
                .setDataPaga(Date.from(dataToPaga.atStartOfDay(ZoneId.of("America/Puerto_Rico"))
                        .toInstant()));
        PagamentosAssociado pagamentoAssociadoOBJ = pagamentoAssociadoService.cadastrar(pagamentoAssociadoRequest);

        /*
            Pegar a idade do associado
         */
        LocalDate dataAniversario = virarAssociadoRequest
                .getDataNascimento()
                .toInstant()
                .atZone(ZoneId.of("America/Puerto_Rico")).toLocalDate();
        LocalDate dataAtual = LocalDate.now(ZoneId.of("America/Puerto_Rico"));
        Period period = Period.between(dataAniversario, dataAtual);

        /*
            Criamos um array de pagamento do associado e colocamos o objeto do bd
            em que foi feito o cadastro
         */
        ArrayList<PagamentosAssociado> pagamentosAssociados = new ArrayList<>();
        pagamentosAssociados.add(pagamentoAssociadoOBJ);

        /*
            Criamos um array de meses pagos e colocamos a data em que foi feito a transação
         */
        List<Date> mesesPagos = new ArrayList<>();
        mesesPagos.add(new Date());

        // Configurando todos os dados do novo associado

        Associado associadoNovo = virarAssociadoRequest.toAssociado();
        associadoNovo.setPagamentosAssociados(pagamentosAssociados);
        associadoNovo.setIdade(period.getYears());
        associadoNovo.setIsAssociado(AssociadoStatus.PAGO);
        associadoNovo.setDataExpiroAssociacao(getDataExpiroAssociacao(pagamentoAssociadoOBJ));
        associadoNovo.setMensalidadePaga(true);
        associadoNovo.setMesesPagos(mesesPagos);
        associadoNovo.setCartaoCredito(virarAssociadoRequest.toCartaoAssociado());
        associadoNovo.setSenha(membro.getSenha());

        /*
            Definindo a roles do novo associado para acesso a novas urls e novas funcionalidades
            da API
         */
        Set<Role> roles = new HashSet<>();
        Optional<Role> optionalAssociado = roleRepository.findByName(ERole.ROLE_ASSOCIADO);
        Optional<Role> optionalUser = roleRepository.findByName(ERole.ROLE_USER);
        Role roleUser = null;
        Role roleAssociado = null;
        if (optionalAssociado.isPresent() && optionalUser.isPresent()) {
            roleUser = optionalUser.get();
            roleAssociado = optionalAssociado.get();
        }
        roles.add(roleAssociado);
        roles.add(roleUser);
        associadoNovo.setRoles(roles);

        Associado associadoSalvoBD = this.associadoRepository.insert(associadoNovo);

        return retorno;
    }

    @Override
    public List<Date> listarDatasPagas(String idAssociado) {
        Optional<Associado> optional = this.associadoRepository.findById(idAssociado);
        if (optional.isEmpty()) {
            return null;
        }
        Associado associado = optional.get();
        return associado.getMesesPagos();
    }

    @Override
    public PagamentoAssociadoResponse listarPagamentoByMesAno(String idAssociado, String mes, String ano) {
        Optional<Associado> optional = this.associadoRepository.findById(idAssociado);
        if (optional.isEmpty()) {
            return null;
        }
        Associado associado = optional.get();
        PagamentosAssociado pagamentosAssociado = null;
        if (associado.getPagamentosAssociados() != null) {
            for (PagamentosAssociado pagamentoAssociadoObj : associado.getPagamentosAssociados()) {
                if (formatter.format(pagamentoAssociadoObj.getDate()).substring(3).equals(mes + "/" + ano)) {
                    pagamentosAssociado = pagamentoAssociadoObj;
                }
            }
        }


        return new PagamentoAssociadoResponse(pagamentosAssociado);
    }

    @Override
    public AssociadoPerfilResponse visualizarPerfilAssociado(String idAssociado) {
        Optional<Associado> optional = associadoRepository.findById(idAssociado);
        if (optional.isEmpty()) {
            return null;
        }
        Associado associado = optional.get();
        return new AssociadoPerfilResponse().toAssociadoPerfilResponse(associado);
    }

    @Override
    public PagamentoAssociadoPerfilResponse listarPagamentoPerfilByMesAno(String idAssociado, String mes, String ano) {
        Optional<Associado> optional = this.associadoRepository.findById(idAssociado);
        if (optional.isEmpty()) {
            return null;
        }
        Associado associado = optional.get();
        PagamentosAssociado pagamentosAssociado = null;
        if (associado.getPagamentosAssociados() != null) {
            for (PagamentosAssociado pagamentoAssociadoObj : associado.getPagamentosAssociados()) {
                if (formatter.format(pagamentoAssociadoObj.getDataPaga()).substring(3).equals(mes + "/" + ano)) {
                    pagamentosAssociado = pagamentoAssociadoObj;
                }
            }
        }

        if (pagamentosAssociado != null) {
            return new PagamentoAssociadoPerfilResponse(pagamentosAssociado);
        } else {
            return null;
        }
    }

    @Override
    public CartaoCredito cartaoAssociado(String idAssociado) {
        Optional<Associado> optional = this.associadoRepository.findById(idAssociado);
        if (optional.isEmpty()) {
            return null;
        }
        Associado associado = optional.get();
        return associado.getCartaoCredito();
    }

    private Date getDataExpiroAssociacao(PagamentosAssociado pagamentosAssociado) {
        LocalDate dataExpiro = pagamentosAssociado
                .getDataPaga()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        dataExpiro = dataExpiro.plusDays(30);
        return Date.from(dataExpiro.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
