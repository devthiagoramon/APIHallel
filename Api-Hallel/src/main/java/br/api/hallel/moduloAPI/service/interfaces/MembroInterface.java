package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;

import java.util.List;

public interface MembroInterface {

    public Membro createMembro(Membro membro);

    public List<MembroResponse> listAllMembros();

    public Membro listMembroId(String id);

    public Membro updatePerfilMembro(String id, Membro membroModel);

    public void deleteMembroById(String id);

    public Membro findByEmailAndPassword(String email, String senha);

    public List<Membro> findByStatusAtivo();

    public List<Membro> findByStatusPendente();

    public Membro findByEmail(String email);

    PerfilResponse visualizarPerfil(String id) throws IllegalAccessException;
    List<MembroResponse> listByPage(int pagina);
}