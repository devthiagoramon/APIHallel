package br.api.hallel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Membro;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.service.interfaces.MembroInterface;

@Service
public class MembroService implements MembroInterface {

    @Autowired
    private MembroRepository repository;

    @Override
    public Membro createMembro(Membro membro) {
        return this.repository.insert(membro);
    }

    @Override
    public List<Membro> listAllMembros() {
        List<Membro> membros = this.repository.findAll();
        return membros;
    }

    @Override
    public Membro listMembroId(String id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Membro não existe"));
    }

    @Override
    public Membro updatePerfilMembro(String id, Membro membroModel) {
        Optional<Membro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Membro membro = optional.get();
            membro.setNome(membroModel.getNome());
            membro.setIdade(membroModel.getIdade());
            membro.setEmail(membroModel.getEmail());
            membro.setSenha(membroModel.getSenha());
            membro.setDataAniversario(membroModel.getDataAniversario());

            return this.repository.save(membroModel);
        }

        return null;
    }

    @Override
    public void deleteMembroById(String id) {
        Optional<Membro> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            this.repository.deleteById(id);

        }

    }

}