package br.api.hallel.repository;

import java.lang.Enum;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.api.hallel.model.Membro;
import br.api.hallel.model.StatusMembro;

@Repository
public interface MembroRepository extends MongoRepository<Membro, String>{
    
    public List<Membro> findByStatusEquals(Enum<StatusMembro> status);
    public Optional<Membro> findByEmailAndSenha(String email, String senha);
    public Optional<Membro> findByEmail(String email);

}
