package br.api.hallel.moduloAPI.service.main;

import br.api.hallel.moduloAPI.model.MembroGoogle;
import br.api.hallel.moduloAPI.repository.MembroGoogleRepository;
import br.api.hallel.moduloAPI.service.interfaces.GoogleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroGoogleService implements GoogleInterface {

    @Autowired
    MembroGoogleRepository googleRepository;

    @Override
    public MembroGoogle createMembro(MembroGoogle membroGoogle) {
        return this.googleRepository.insert(membroGoogle);
    }

    //POSSUI O CRUD PARA MEMBRO QUE FOR LOGAR COM O GOOGLE (OAUTH2)

    @Override
    public void deleteMembroGoogle(String id) {
        Optional<MembroGoogle> optional = this.googleRepository.findById(id);

        if (optional.isPresent()) {
            this.googleRepository.deleteById(id);

        }
    }

    @Override
    public MembroGoogle update(String id) {
        Optional<MembroGoogle> optional = this.googleRepository.findById(id);

        if (optional.isPresent()) {
            MembroGoogle membro = optional.get();
            return this.googleRepository.save(membro);
        }
        return null;

    }

    @Override
    public List<MembroGoogle> listMembroGoogle() {
        return this.googleRepository.findAll();
    }

    @Override
    public MembroGoogle findByEmail(String email) {
        return this.googleRepository.findByEmail(email).isPresent() ? this.googleRepository.findByEmail(email).get() : null;

    }



}
