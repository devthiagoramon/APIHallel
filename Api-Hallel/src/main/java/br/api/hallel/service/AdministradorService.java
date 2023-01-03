package br.api.hallel.service;

import java.util.List;
import java.util.Optional;

import br.api.hallel.dto.AdministradorDTO;
import br.api.hallel.security.Token;
import br.api.hallel.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Administrador;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.repository.AdministradorRepository;
import br.api.hallel.service.interfaces.AdministradorInterface;

@Service
public class AdministradorService implements AdministradorInterface {

    @Autowired
    private AdministradorRepository repository;

    @Override
    public String inserirAdministrador(Administrador administrador) {
        this.repository.insert(administrador);
        return "Administrador inserido";
    }

    @Override
    public List<Administrador> listarTodosAdministradores() {
        return this.repository.findAll();
    }

    @Override
    public Administrador findAdministrador(String id) {

        Optional<Administrador> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Administrador administrador = optional.get();
            administrador.setSenhaAcesso("");
            return administrador;
        } else {
            throw new IllegalArgumentException("Administrador não encontrado");
        }

    }

    @Override
    public Administrador findAdministradorEmail(String email) {
        return this.repository.findByEmail(email).isPresent() ? this.repository.findByEmail(email).get() : null;
    }

    @Override
    public Administrador acessarAdministrador(String email, String senhaAcesso) {

        Optional<Administrador> optional = this.repository.findByEmail(email);

        if (!optional.isPresent()) {
            return null;
        }
        if (!optional.get().getSenhaAcesso().equals(senhaAcesso)) {
            return null;
        }
        Administrador administrador = optional.get();
        return administrador;
    }

    @Override
    public String deletarAdministrador(String id) {

        Administrador administrador = findAdministrador(id);

        this.repository.delete(administrador);

        return "Professor deletado com sucesso";
    }

    @Override
    public String alterarAdministrador(String id, Administrador administradorNovo) {

        Administrador administrador = findAdministrador(id);

        Administrador administradorAlterado = alterarAtributos(administrador, administradorNovo);        

        this.repository.save(administradorAlterado);

        return "Administrador alterado com sucesso";
    }

    private Administrador alterarAtributos(Administrador administrador, Administrador administradorNovo){
        if(administradorNovo.getNome()==null){
            throw new IllegalArgumentException("Nome não preenchido");
        }
        if(administradorNovo.getSenhaAcesso()==null){
            throw new IllegalArgumentException("Senha de acesso não preenchida");
        }
        if(administradorNovo.getStatus() != StatusMembro.ATIVO){
            throw new IllegalArgumentException("Status invalido para um administrador, deve ser ativo");
        }
        administrador = administradorNovo;
        return administrador;
    }


    public Token gerarToken(AdministradorDTO administradorDTO) {

        Optional<Administrador> optional = this.repository.findByEmailAndSenhaAcesso(administradorDTO.getEmail(), administradorDTO.getSenhaAcesso());

        if(optional.isPresent()){
            return new Token(TokenUtil.createToken(administradorDTO.toAdministrador()));
        }else{
            return null;
        }

    }
}
