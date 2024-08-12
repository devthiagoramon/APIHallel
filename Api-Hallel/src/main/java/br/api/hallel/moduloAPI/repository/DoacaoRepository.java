package br.api.hallel.moduloAPI.repository;

import br.api.hallel.moduloAPI.model.Doacao;
import br.api.hallel.moduloAPI.model.StatusDoacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {
    public List<Doacao> findByNameDonator(String name);

    public List<Doacao> findByIsAnonimoIsTrue();
    public List<Doacao> findByIdDonator(String id);
    public List<Doacao> findByIsObjetoIsTrue();
    public List<Doacao> findByIdEvento(String idEvento);
    public List<Doacao> findByIdRetiro(String idRetiro);
    public List<Doacao> findByStatus(StatusDoacao status);
    public List<Doacao> findByIdDonatorIsNotNull();
}
