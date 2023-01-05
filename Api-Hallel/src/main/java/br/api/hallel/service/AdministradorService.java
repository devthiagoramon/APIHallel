package br.api.hallel.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.api.hallel.dto.AdministradorDTO;
import br.api.hallel.model.ERole;
import br.api.hallel.model.Role;
import br.api.hallel.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.payload.resposta.MessageResposta;
import br.api.hallel.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.api.hallel.model.Administrador;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.repository.AdministradorRepository;
import br.api.hallel.service.interfaces.AdministradorInterface;

@Service
public class AdministradorService implements AdministradorInterface {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> inserirAdministrador(@Valid CadAdministradorRequerimento administradorReq) {
        if(repository.existsByEmail(administradorReq.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResposta("Error: email já existente"));
        }

        Administrador administrador = new Administrador();

        administrador.setNome(administradorReq.getNome());
        administrador.setEmail(administradorReq.getEmail());
        administrador.setSenha(encoder.encode(administradorReq.getSenha()));
        administrador.setSenhaAcesso(administradorReq.getSenhaAcesso());
        administrador.setStatus(StatusMembro.ATIVO);

        Set<String> strRoles = administradorReq.getRoles();
        Set<Role> roles = new HashSet<>();

        if(strRoles==null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else{
            strRoles.forEach(role -> {
                switch (role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                        break;
                }
            });
        }

        administrador.setRoles(roles);
        repository.save(administrador);
        return ResponseEntity.ok().body(new MessageResposta("Administrador adicionado com sucesso!"));
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


}
