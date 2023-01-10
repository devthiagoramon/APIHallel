package br.api.hallel.service;

import br.api.hallel.model.Administrador;
import br.api.hallel.model.ERole;
import br.api.hallel.model.Role;
import br.api.hallel.model.StatusMembro;
import br.api.hallel.payload.requerimento.CadAdministradorRequerimento;
import br.api.hallel.payload.resposta.MessageResposta;
import br.api.hallel.repository.AdministradorRepository;
import br.api.hallel.repository.RoleRepository;
import br.api.hallel.service.interfaces.AdministradorInterface;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdministradorService implements AdministradorInterface {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;
    private Logger logger = LoggerFactory.getLogger(AdministradorService.class);

    @Override
    public ResponseEntity<?> inserirAdministrador(@Valid CadAdministradorRequerimento administradorReq) {
        if (repository.existsByEmail(administradorReq.getEmail())) {
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

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
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
        logger.info("Listando todos os Administradores -- Adm logado: "+getLogado());
        return this.repository.findAll();
    }

    @Override
    public Administrador findAdministrador(String id) {

        Optional<Administrador> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Administrador administrador = optional.get();
            administrador.setSenhaAcesso("");
            logger.info("Administrador encontrador: "+administrador.getNome()+" -- Adm logado: "+getLogado());

            return administrador;
        } else {
            throw new IllegalArgumentException("Administrador não encontrado");
        }

    }

    @Override
    public Administrador findAdministradorEmail(String email) {
        logger.info("Administrador encontrado por email -- Adm logado: "+getLogado());

        return this.repository.findByEmail(email).isPresent() ? this.repository.findByEmail(email).get() : null;
    }

    @Override
    public Administrador acessarAdministrador(String email, String senhaAcesso) {

        Optional<Administrador> optional = this.repository.findByEmail(email);

        if (!optional.isPresent()) {
            logger.error("Adminitrador não encontrado");

            return null;
        }
        if (!optional.get().getSenhaAcesso().equals(senhaAcesso)) {
            logger.error("As credenciais informadas estão diferentes ");
            return null;
        }
        Administrador administrador = optional.get();
        logger.info("Acessando como adiministrador!");
        return administrador;
    }

    @Override
    public String deletarAdministrador(String id) {

        Administrador administrador = findAdministrador(id);

        this.repository.delete(administrador);
        logger.info("Deletando Administrador -- Adm logado: "+getLogado());

        return "Administrador deletado com sucesso";
    }

    @Override
    public String alterarAdministrador(String id, Administrador administradorNovo) {

        Administrador administrador = findAdministrador(id);

        Administrador administradorAlterado = alterarAtributos(administrador, administradorNovo);

        this.repository.save(administradorAlterado);
        logger.info("Administrador alterado: "+administradorAlterado.getNome()+" -- Adm logado: "+getLogado());
        return "Administrador alterado com sucesso";
    }

    private Administrador alterarAtributos(Administrador administrador, Administrador administradorNovo) {
        if (administradorNovo.getNome() == null) {
            logger.error("Credencial 'nome' está vázio! ");
            throw new IllegalArgumentException("Nome não preenchido");
        }
        if (administradorNovo.getSenhaAcesso() == null) {
            logger.error("Credencial 'senha' está vazio");
            throw new IllegalArgumentException("Senha de acesso não preenchida");
        }
        if (administradorNovo.getStatus() != StatusMembro.ATIVO) {
            logger.error("O Administrador deve ser ativo");
            throw new IllegalArgumentException("Status invalido para um administrador, deve ser ativo");
        }
        administrador = administradorNovo;
        logger.info("Dados do Administrador alterados! -- "+administrador.getNome());
        return administrador;
    }

    private String getLogado() {
        Authentication adminLogado = SecurityContextHolder.getContext().getAuthentication();
        if (!(adminLogado instanceof AnonymousAuthenticationToken)) {
            return adminLogado.getName();
        }
        return "null";

    }

}
