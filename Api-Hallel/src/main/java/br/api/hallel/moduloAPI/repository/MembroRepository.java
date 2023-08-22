package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Membro;
import br.api.hallel.moduloAPI.model.StatusMembro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MembroRepository extends MongoRepository<Membro, String>{
    
    public List<Membro> findByStatusEquals(Enum<StatusMembro> status);
    public Optional<Membro> findByEmailAndSenha(String email, String senha);
    public Optional<Membro> findByEmail(String email);
    public Optional<Membro> findByNome(String username);
    public boolean existsByEmail(String email);

    public boolean existsByNome(String nome);

    public Optional<Membro> findByNomeAndEmail(String nome, String email);
}
