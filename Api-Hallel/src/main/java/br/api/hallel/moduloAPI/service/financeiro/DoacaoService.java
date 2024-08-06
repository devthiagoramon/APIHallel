package br.api.hallel.moduloAPI.service.financeiro;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.requerimento.DoarMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarReq;
import br.api.hallel.moduloAPI.repository.DoacaoRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.service.interfaces.DoacaoInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoacaoService implements DoacaoInterface {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private MembroRepository membroRepository;


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
        if (optional.isEmpty()){
            throw new NullPointerException("Usuário não encontrado");
        }
        Membro membro = optional.get();
        doacao.setEmailDonator(membro.getEmail());
        doacao.setNameObjeto(membro.getNome());
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


}
