package br.api.hallel.service.interfaces;

import br.api.hallel.model.MembroGoogle;

import java.util.List;

public interface GoogleInterface {

    MembroGoogle createMembro(MembroGoogle membroGoogle);

    void deleteMembroGoogle(String id);

    MembroGoogle update(String id);

    List<MembroGoogle> listMembroGoogle();

    MembroGoogle findByEmail(String email);

}
