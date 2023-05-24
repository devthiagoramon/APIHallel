package br.api.hallel.service;

import br.api.hallel.exceptions.SolicitarCadastroException;
import br.api.hallel.exceptions.SolicitarLoginException;
import br.api.hallel.model.*;
import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AdministradorResponse;
import br.api.hallel.payload.resposta.AssociadoResponse;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import br.api.hallel.payload.resposta.MembroResponse;
import br.api.hallel.repository.AdministradorRepository;
import br.api.hallel.repository.MembroGoogleRepository;
import br.api.hallel.repository.MembroRepository;
import br.api.hallel.repository.RoleRepository;
import br.api.hallel.security.services.JwtService;
import br.api.hallel.service.interfaces.MainInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Log4j2
public class MainService implements MainInterface {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private MembroGoogleRepository googleRepository;

    /*MÉTODO PARA REALIZAR O LOGIN BASEADO NO TOKEN DO USUARIO,
        ELE PODE LOGAR COMO MEMBRO OU COMO ADMISTRADOR
    * */

    @Override
    public AuthenticationResponse logar(@Valid LoginRequerimento loginRequerimento) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequerimento.getEmail(), loginRequerimento.getSenha()
                )
        );

        if(associadoService.findByEmail(loginRequerimento.getEmail()) != null){
            Associado associado = associadoService.findByEmail(loginRequerimento.getEmail());
            if(associado.getMensalidadePaga()) {
                var jwtToken = jwtService.generateToken(associado);
                AssociadoResponse associadoResponse = new AssociadoResponse();
                associadoResponse.setId(associado.getId());
                associadoResponse.setRoles(associado.getRoles());

                log.info(associado.getNome()+ " logando");
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .objeto(associadoResponse)
                        .build();
            }
        }

        if (membroRepository.findByEmail(loginRequerimento.getEmail()).isPresent()) {

            //VERIFICAÇÃO PARA VER SE EXISTE UM MEMBRO COM ESSE EMAIL

            var membro = membroRepository.findByEmail(loginRequerimento.getEmail()).get();

            if (membro.getStatus().equals(StatusMembro.ATIVO)) {

                //SE EXISTE, O MEMBRO VEM COMO ATIVO, POIS ESTÁ ACESSANDO AO SITE
                var jwtToken = jwtService.generateToken(membro);
                MembroResponse membroResponse = new MembroResponse();
                membroResponse.setId(membro.getId());
                membroResponse.setNome(membro.getNome());
                membroResponse.setEmail(membro.getEmail());
                membroResponse.setRoles(membro.getRoles());

                if (membro.getNome() != null && membro.getEmail() != null
                        && membro.getSenha() != null) {
                    log.info(membro.getNome()+ " logando");
                    return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .objeto(membroResponse)
                            .build();
                } else {
                    throw new SolicitarLoginException("Por favor, informe as credenciais os campos corretamente!");
                }
            }
        }

        //VERIFICA SE É UM ADM
        if (administradorRepository.findByEmail(loginRequerimento.getEmail()).isPresent()) {
            //SE EXISTE, ELE FAZ LOGIN COMO MEMBRO
            var administrador = administradorRepository.findByEmail(loginRequerimento.getEmail()).get();
            System.out.println("Adm");
            var jwtToken = jwtService.generateToken(administrador);
            AdministradorResponse administradorResponse = new AdministradorResponse();
            administradorResponse.setEmail(administrador.getEmail());
            administradorResponse.setNome(administrador.getNome());
            administradorResponse.setRoles(administrador.getRoles());

            if (administrador.getNome() != null && administrador.getEmail() != null) {
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .objeto(administradorResponse)
                        .build();
            } else {
                throw new SolicitarLoginException("Por favor, informe as credenciais corretamente! ");
            }
        }
        return null;
    }

    /*MÉTODO PARA REALIZAR O LOGIN COM GOOGLE (OAUTH2)
        (MESMA COISA DO MÉTODO ANTERIOR)
     */
    @Override
    public AuthenticationResponse logarGoogle(@Valid LoginRequerimentoGoogle loginRequerimentoGoogle) {
        System.out.println("COMEÇO");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequerimentoGoogle.getEmail(), loginRequerimentoGoogle.getNome()
                )

        );

        System.out.println("ANTES FAZENDO O IF ELSE ");

        if (googleRepository.findByEmail(loginRequerimentoGoogle.getEmail()).isPresent()) {

            System.out.println("FAZENDO O IF ELSE ");

            var membroGoogle = googleRepository.findByEmail(loginRequerimentoGoogle.getEmail()).get();
            System.out.println("Membro Google" );

            if (membroGoogle.getStatus().equals(StatusMembro.ATIVO)) {
                var jwtToken = jwtService.generateToken(membroGoogle);

                System.out.println("CERTO");

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .objeto(membroGoogle)
                        .build();
            }
        }

        if (administradorRepository.findByEmail(loginRequerimentoGoogle.getEmail()).isPresent()) {
            var administrador = administradorRepository.findByEmail(loginRequerimentoGoogle.getEmail()).get();
            System.out.println("Adm Google");
            var jwtToken = jwtService.generateToken(administrador);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .objeto(administrador)
                    .build();
        }

        System.out.println("FUCK RETORNOU NULL");

        return null;
    }

    /*
        MÉTODO PARA REALIZAR A SOLITAÇÃO DO CADASTRO
        (O ADMIN DEVE ACEITAR O CADASTRO DO MEMBRO)
     */
    @Override
    public AuthenticationResponse solicitarCadastro(SolicitarCadastroRequerimento solicitarCadastroRequerimento) {
        //ADICIONA A ROLE (FUNÇÃO) QUE O USUÁRIO É, NO CASO, DE MEMBRO.

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(ERole.ROLE_USER).get() : null);

        var membro = new Membro();
        membro.setNome(solicitarCadastroRequerimento.getNome());
        membro.setEmail(solicitarCadastroRequerimento.getEmail());
        membro.setSenha(encoder.encode(solicitarCadastroRequerimento.getSenha()));
        membro.setRoles(roles);
        membro.setStatus(StatusMembro.PENDENTE);

        //SALVA NO BD E GERA O TOKEN PARA O USUARIO
        if (membro.getEmail() != null &&
                membro.getNome() != null &&
                membro.getSenha() != null) {
            membroRepository.save(membro);
            var jwtToken = jwtService.generateToken(membro);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new SolicitarCadastroException("Por favor, preenchar os campos corretamente.");
        }
    }


    /*
      MÉTODO PARA REALIZAR A SOLITAÇÃO DO CADASTRO COM GOOGLE (OAUTH2)
      (O ADMIN DEVE ACEITAR O CADASTRO DO MEMBRO)
      (MESMA LÓGICA DO MÉTODO ANTERIOR)
   */
    @Override
    public AuthenticationResponse solicitarCadastroGoogle(SolicitarCadastroGoogle solicitarCadastroGoogle) {

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(ERole.ROLE_USER).get() : null);
        var membro = new MembroGoogle();

        membro.setNome(solicitarCadastroGoogle.getNome());
        membro.setEmail(solicitarCadastroGoogle.getEmail());
        membro.setSenha(encoder.encode(solicitarCadastroGoogle.getSenha()));
        membro.setRoles(roles);
        membro.setStatus(StatusMembro.PENDENTE);

        if (solicitarCadastroGoogle.getEmail() != null &&
                solicitarCadastroGoogle.getNome() != null &&
                solicitarCadastroGoogle.getSenha() != null) {

            googleRepository.save(membro);

            var jwtToken = jwtService.generateToken(membro);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new SolicitarCadastroException("Por favor, preenchar os campos corretamente.");
        }


    }

}