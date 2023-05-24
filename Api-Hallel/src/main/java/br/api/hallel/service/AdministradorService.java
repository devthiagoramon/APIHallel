package br.api.hallel.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.api.hallel.controller.AdministradorController;
import br.api.hallel.model.ERole;
import br.api.hallel.model.Role;
import br.api.hallel.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.payload.resposta.MessageResposta;
import br.api.hallel.repository.RoleRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AdministradorService.class);



    //FAZ O CADASTRO DE UM ADIMISTRADOR
    @Override
    public ResponseEntity<?> inserirAdministrador(@Valid CadAdministradorRequerimento administradorReq) {
        if(repository.existsByEmail(administradorReq.getEmail())){
            logger.error("EMAIL JÁ EXISTE!");
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
                    case "associado":
                        Role associadoRole = roleRepository.findByName(ERole.ROLE_ASSOCIADO)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(associadoRole);
                        break;
                }
            });
        }

        administrador.setRoles(roles);
        repository.save(administrador);
        logger.info("ADMIN SALVO!");

        return ResponseEntity.ok().body(new MessageResposta("Administrador adicionado com sucesso!"));
    }


    //LISTA TODOS OS ADIMISTRADORES
    @Override
    public List<Administrador> listarTodosAdministradores() {
        return this.repository.findAll();
    }

    //LISTA UM ADMINISTRADOR PELO ID DELE
    @Override
    public Administrador findAdministrador(String id) {

        Optional<Administrador> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Administrador administrador = optional.get();
            administrador.setSenhaAcesso("");
            logger.info("ADMIN ENCONTRADO!");

            return administrador;

        } else {
            logger.error("ADMIN NÃO  ENCONTRADO!");

            throw new IllegalArgumentException("Administrador não encontrado");
        }

    }

    //LISTA UM ADMINISTRADOR PELO EMAIL DELE
    @Override
    public Administrador findAdministradorEmail(String email) {
        logger.info("ADMIN ENCONTRADO!");

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

    //REMOVE UM ADMINISTRADOR
    @Override
    public String deletarAdministrador(String id) {

        Administrador administrador = findAdministrador(id);

        this.repository.delete(administrador);
        logger.info("ADMIN REMOVIDO!");

        return "Professor deletado com sucesso";
    }

    //ALTERA INFORMAÇÕES DESSE ADMINISTRADOR
    @Override
    public String alterarAdministrador(String id, Administrador administradorNovo) {

        Administrador administrador = findAdministrador(id);

        Administrador administradorAlterado = alterarAtributos(administrador, administradorNovo);        

        this.repository.save(administradorAlterado);

        logger.info("ADMIN ALTERADO");

        return "Administrador alterado com sucesso";
    }

    //MÉTODO AUXILIAR PARA ALTERAR AS INFORMAÇÕES DO ADMINISTRADOR
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

        logger.info("INFORMAÇÕES DO ADM SALVO COM SUCESSO!");

        return administrador;
    }


}
