package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.payload.resposta.PerfilResponse;

import java.util.List;

public interface MembroInterface {

    Membro createMembro(Membro membro);

    List<MembroResponse> listAllMembros();

    Membro listMembroId(String id);
    Membro updateMembro(String idMembro,Membro membro);

    Membro updatePerfilMembro(String id, Membro membroModel);

    void deleteMembroById(String id);

    Membro findByEmailAndPassword(String email, String senha);

    Membro findByEmail(String email);

    PerfilResponse visualizarPerfil(String id) throws IllegalAccessException;

    List<MembroResponse> listByPage(int pagina);
}