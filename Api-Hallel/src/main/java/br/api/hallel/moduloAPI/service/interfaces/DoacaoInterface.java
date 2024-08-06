package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.payload.requerimento.DoarMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoMembroReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarObjetoReq;
import br.api.hallel.moduloAPI.payload.requerimento.DoarReq;

public interface DoacaoInterface {
    public Doacao doar(DoarReq dto);
    public Doacao doarObjeto(DoarObjetoReq dto);

    public Doacao doarMembro(DoarMembroReq dto);
    public Doacao doarObjetoMembro(DoarObjetoMembroReq dto);

}
