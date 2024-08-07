package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.*;

import java.util.List;

public interface DoacaoInterface {

    public Doacao criarDoacao(CriarEditarDoacaoReq dto);
    public Doacao editarDoacao(String idDoacao, CriarEditarDoacaoReq dto);

    public Doacao doar(DoarReq dto);
    public Doacao doarObjeto(DoarObjetoReq dto);

    public Doacao doarMembro(DoarMembroReq dto);
    public Doacao doarObjetoMembro(DoarObjetoMembroReq dto);

    public Doacao doarEvento(DoarEventoReq dto);
    public Doacao doarObjetoEvento(DoarObjetoEventoReq dto);

    public Doacao doarRetiro(DoarRetiroReq dto);
    public Doacao doarObjetoRetiro(DoarObjetoRetiroReq dto);

    public Doacao finalizarDoacao(String idDoacao) throws
            IllegalArgumentException;
    public Doacao finalizarDoacaoObjeto(String idDoacao);

    public Boolean deleteDoacao(String id) throws
            IllegalArgumentException;

    public List<Doacao> listarDoacao();
    public Doacao listarDoacaoPorId(String idDoacao);

    public List<Doacao> listarDoacaoAnonima();

    public List<Doacao> listarDoacaoMembros();
    public List<Doacao> listarDoacaoMembroPorId(String id);

    public List<Doacao> listarDoacaoObjeto();

    public List<Doacao> listarDoacaoEventoPorId(String idEvento);

    public List<Doacao> listarDoacaoRetiroPorId(String idRetiro);

    public List<Doacao> listarDoacaoError();
    public List<Doacao> listarDoacaoPendentes();
    public List<Doacao> listarDoacaoFinalizadas();
    public List<Doacao> listarDoacaoEntregues();
}
