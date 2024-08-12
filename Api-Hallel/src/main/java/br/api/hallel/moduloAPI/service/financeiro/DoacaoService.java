package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import br.api.hallel.moduloAPI.payload.requerimento.*;
import br.api.hallel.moduloAPI.repository.DoacaoRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.service.interfaces.DoacaoInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService implements DoacaoInterface {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private MembroRepository membroRepository;


    @Override
    public Doacao criarDoacao(CriarEditarDoacaoReq dto) {
        return doacaoRepository.insert(dto.toDoacao());
    }

    @Override
    public Doacao editarDoacao(String idDoacao,
                               CriarEditarDoacaoReq dto) {

        Optional<Doacao> optional = doacaoRepository.findById(idDoacao);

        if (optional.isEmpty()) {
            throw new RuntimeException("Doação não encontrado");
        }

        Doacao doacaoOld = optional.get();
        doacaoOld = dto.toDoacao();
        doacaoOld.setId(idDoacao);
        return doacaoRepository.save(doacaoOld);
    }

    @Override
    public Doacao doar(DoarReq dto) {
        return doacaoRepository.insert(dto.toDoacao());
    }

    @Override
    public Doacao doarObjeto(DoarObjetoReq dto) {
        return doacaoRepository.insert(dto.toDoacao());
    }

    @NotNull
    private Doacao getDoacaoWithMembro(Doacao doacao) {
        Optional<Membro> optional = membroRepository.findById(doacao.getIdDonator());
        if (optional.isEmpty()) {
            throw new NullPointerException("Usuário não encontrado");
        }
        Membro membro = optional.get();
        doacao.setEmailDonator(membro.getEmail());
        doacao.setNameDonator(membro.getNome());
        doacao.setTelefoneDonator(membro.getTelefone());
        return doacaoRepository.insert(doacao);
    }

    @Override
    public Doacao doarMembro(DoarMembroReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }


    @Override
    public Doacao doarObjetoMembro(DoarObjetoMembroReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }

    @Override
    public Doacao doarEvento(DoarEventoReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }

    @Override
    public Doacao doarObjetoEvento(DoarObjetoEventoReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }

    @Override
    public Doacao doarRetiro(DoarRetiroReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }

    @Override
    public Doacao doarObjetoRetiro(DoarObjetoRetiroReq dto) {
        Doacao doacao = dto.toDoacao();
        return getDoacaoWithMembro(doacao);
    }

    @NotNull
    private Doacao getDoacao(String idDoacao) throws
            IllegalArgumentException {
        Optional<Doacao> optional = doacaoRepository.findById(idDoacao);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Id de doação inexistente");
        }
        return optional.get();
    }

    @Override
    public Doacao finalizarDoacao(String idDoacao) {
        Doacao doacao = getDoacao(idDoacao);
        if (doacao.getStatus() == StatusDoacao.FINALIZADO) {
            return doacao;
        }
        doacao.setStatus(StatusDoacao.FINALIZADO);
        return doacaoRepository.save(doacao);
    }

    @Override
    public Doacao finalizarDoacaoObjeto(String idDoacao) {
        Doacao doacao = getDoacao(idDoacao);
        if (doacao.getStatus() == StatusDoacao.ENTREGUE) {
            return doacao;
        }
        doacao.setStatus(StatusDoacao.ENTREGUE);
        doacao.setDateEntregue(new Date());
        return doacaoRepository.save(doacao);
    }


    @Override
    public Boolean deleteDoacao(String id) throws
            IllegalArgumentException {
        Optional<Doacao> doacao = doacaoRepository.findById(id);

        if (doacao.isEmpty()) {
            throw new IllegalArgumentException("Id de doação inexistente");
        }
        doacaoRepository.delete(doacao.get());
        return true;
    }

    @Override
    public List<Doacao> listarDoacao() {
        return doacaoRepository.findAll();
    }

    @Override
    public Doacao listarDoacaoPorId(String idDoacao) {
        return doacaoRepository.findById(idDoacao)
                               .orElseThrow(() -> new RuntimeException("Doação não encontrada"));
    }

    @Override
    public List<Doacao> listarDoacaoAnonima() {
        return doacaoRepository.findByIsAnonimoIsTrue();
    }

    @Override
    public List<Doacao> listarDoacaoMembros() {
        return doacaoRepository.findByIdDonatorIsNotNull();
    }

    @Override
    public List<Doacao> listarDoacaoMembroPorId(String id) {
        return doacaoRepository.findByIdDonator(id);
    }

    @Override
    public List<Doacao> listarDoacaoObjeto() {
        return doacaoRepository.findByIsObjetoIsTrue();
    }

    @Override
    public List<Doacao> listarDoacaoEventoPorId(String idEvento) {
        return doacaoRepository.findByIdEvento(idEvento);
    }

    @Override
    public List<Doacao> listarDoacaoRetiroPorId(String idRetiro) {
        return doacaoRepository.findByIdRetiro(idRetiro);
    }

    @Override
    public List<Doacao> listarDoacaoError() {
        return doacaoRepository.findByStatus(StatusDoacao.ERROR);
    }

    @Override
    public List<Doacao> listarDoacaoPendentes() {
        return doacaoRepository.findByStatus(StatusDoacao.PENDENTE);
    }

    @Override
    public List<Doacao> listarDoacaoFinalizadas() {
        return doacaoRepository.findByStatus(StatusDoacao.FINALIZADO);
    }

    @Override
    public List<Doacao> listarDoacaoEntregues() {
        return doacaoRepository.findByStatus(StatusDoacao.ENTREGUE);
    }


}
