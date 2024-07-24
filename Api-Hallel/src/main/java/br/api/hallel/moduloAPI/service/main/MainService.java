package br.api.hallel.moduloAPI.service.main;

import br.api.hallel.moduloAPI.exceptions.SolicitarCadastroException;
import br.api.hallel.moduloAPI.exceptions.SolicitarLoginException;
import br.api.hallel.moduloAPI.exceptions.associado.AssociadoNotFoundException;
import br.api.hallel.moduloAPI.exceptions.handler.EmailJaCadastradoException;
import br.api.hallel.moduloAPI.model.*;
import br.api.hallel.moduloAPI.payload.requerimento.LoginRequerimento;
import br.api.hallel.moduloAPI.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.moduloAPI.payload.resposta.AssociadoResponse;
import br.api.hallel.moduloAPI.payload.resposta.AuthenticationResponse;
import br.api.hallel.moduloAPI.payload.resposta.MembroResponse;
import br.api.hallel.moduloAPI.repository.AdministradorRepository;
import br.api.hallel.moduloAPI.repository.MembroGoogleRepository;
import br.api.hallel.moduloAPI.repository.MembroRepository;
import br.api.hallel.moduloAPI.repository.RoleRepository;
import br.api.hallel.moduloAPI.security.services.JwtService;
import br.api.hallel.moduloAPI.service.financeiro.AssociadoService;
import br.api.hallel.moduloAPI.service.interfaces.MainInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    public void atualizarTokenMembro(Membro membro, String token){
        membro.setToken(token);
        membroRepository.save(membro);
    }


    @Override
    public AuthenticationResponse logar(
            @Valid LoginRequerimento loginRequerimento) throws
            AssociadoNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequerimento.getEmail(),
                        loginRequerimento.getSenha()
                )
                                          );

        if (associadoService.findByEmail(
                loginRequerimento.getEmail()) != null) {
            Associado associado = associadoService.findByEmail(
                    loginRequerimento.getEmail());
            if (associado.getMensalidadePaga()) {
                var jwtToken = jwtService.generateToken(associado);

                associadoService.atualizarTokenAssociado(associado.id, jwtToken);

                AssociadoResponse associadoResponse = new AssociadoResponse();
                associadoResponse.setId(associado.getId());
                associadoResponse.setRoles(associado.getRoles());
                associadoResponse.setEmail(associado.getEmail());
                associadoResponse.setNome(associado.getNome());
                associadoResponse.setImagem(associado.getImage());
                log.info(associado.getNome() + " logando");
                return AuthenticationResponse.builder()
                                             .token(jwtToken)
                                             .objeto(associadoResponse)
                                             .build();
            }
        }

        if (membroRepository.findByEmail(
                loginRequerimento.getEmail()).isPresent()) {

            //VERIFICAÇÃO PARA VER SE EXISTE UM MEMBRO COM ESSE EMAIL

            var membro = membroRepository.findByEmail(
                    loginRequerimento.getEmail()).get();

            if (membro.getStatusMembro().equals(StatusMembro.ATIVO)) {

                //SE EXISTE, O MEMBRO VEM COMO ATIVO, POIS ESTÁ ACESSANDO AO SITE
                var jwtToken = jwtService.generateToken(membro);
                atualizarTokenMembro(membro, jwtToken);
                MembroResponse membroResponse = new MembroResponse();
                membroResponse.setId(membro.getId());
                membroResponse.setNome(membro.getNome());
                membroResponse.setEmail(membro.getEmail());
                membroResponse.setRoles(membro.getRoles());
                membroResponse.setImagem(membro.getImage());
                if (membro.getNome() != null && membro.getEmail() != null
                        && membro.getSenha() != null) {
                    log.info(membro.getNome() + " logando");
                    return AuthenticationResponse.builder()
                                                 .token(jwtToken)
                                                 .objeto(membroResponse)
                                                 .build();
                } else {
                    throw new SolicitarLoginException(
                            "Por favor, informe as credenciais os campos corretamente!");
                }
            }
        }
        throw new SolicitarLoginException(
                "Usuário não encontrado, tente novamente!");
    }


    /*MÉTODO PARA REALIZAR O LOGIN COM GOOGLE (OAUTH2)
        (MESMA COISA DO MÉTODO ANTERIOR)
     */
    @Override
    public AuthenticationResponse logarGoogle(
            @Valid LoginRequerimentoGoogle loginRequerimentoGoogle) {
        System.out.println("COMEÇO");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequerimentoGoogle.getEmail(),
                        loginRequerimentoGoogle.getNome()
                )

                                          );

        System.out.println("ANTES FAZENDO O IF ELSE ");

        if (googleRepository.findByEmail(
                loginRequerimentoGoogle.getEmail()).isPresent()) {

            System.out.println("FAZENDO O IF ELSE ");

            var membroGoogle = googleRepository.findByEmail(
                    loginRequerimentoGoogle.getEmail()).get();
            System.out.println("Membro Google");

            if (membroGoogle.getStatusMembro().equals(
                    StatusMembro.ATIVO)) {
                var jwtToken = jwtService.generateToken(membroGoogle);

                System.out.println("CERTO");

                return AuthenticationResponse.builder()
                                             .token(jwtToken)
                                             .objeto(membroGoogle)
                                             .build();
            }
        }

        if (administradorRepository.findByEmail(
                loginRequerimentoGoogle.getEmail()).isPresent()) {
            var administrador = administradorRepository.findByEmail(
                    loginRequerimentoGoogle.getEmail()).get();
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
    public AuthenticationResponse solicitarCadastro
    (SolicitarCadastroRequerimento solicitarCadastroRequerimento)
            throws SolicitarCadastroException,
            EmailJaCadastradoException {
        //ADICIONA A ROLE (FUNÇÃO) QUE O USUÁRIO É, NO CASO, DE MEMBRO.
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(
                ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(
                        ERole.ROLE_USER).get() : null);

        var membro = new Membro();
        membro.setNome(solicitarCadastroRequerimento.getNome());
        membro.setEmail(solicitarCadastroRequerimento.getEmail());
        membro.setSenha(encoder.encode(
                solicitarCadastroRequerimento.getSenha()));
        membro.setRoles(roles);
        membro.setStatusMembro(StatusMembro.ATIVO);

        // Verifica se o email já está cadastrado
        if (membroRepository.findByEmail(
                                    solicitarCadastroRequerimento.getEmail())
                            .isPresent()) {
            throw new EmailJaCadastradoException(
                    "Este email já está cadastrado.");
        }

        //SALVA NO BD E GERA O TOKEN PARA O USUARIO
        if (membro.getEmail() != null &&
                membro.getNome() != null &&
                membro.getSenha() != null) {
            var jwtToken = jwtService.generateToken(membro);
            membro.setToken(jwtToken);
            membroRepository.save(membro);
            return AuthenticationResponse.builder()
                                         .token(jwtToken)
                                         .build();
        } else {
            throw new SolicitarCadastroException(
                    "Por favor, preencha os campos corretamente.");
        }
    }


    /*
      MÉTODO PARA REALIZAR A SOLITAÇÃO DO CADASTRO COM GOOGLE (OAUTH2)
      (O ADMIN DEVE ACEITAR O CADASTRO DO MEMBRO)
      (MESMA LÓGICA DO MÉTODO ANTERIOR)
   */
    @Override
    public AuthenticationResponse solicitarCadastroGoogle(
            SolicitarCadastroGoogle solicitarCadastroGoogle) {

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(
                ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(
                        ERole.ROLE_USER).get() : null);
        var membro = new MembroGoogle();

        membro.setNome(solicitarCadastroGoogle.getNome());
        membro.setEmail(solicitarCadastroGoogle.getEmail());
        membro.setSenha(
                encoder.encode(solicitarCadastroGoogle.getSenha()));
        membro.setRoles(roles);
        membro.setStatusMembro(StatusMembro.PENDENTE);

        if (solicitarCadastroGoogle.getEmail() != null &&
                solicitarCadastroGoogle.getNome() != null &&
                solicitarCadastroGoogle.getSenha() != null) {

            googleRepository.save(membro);

            var jwtToken = jwtService.generateToken(membro);
            return AuthenticationResponse.builder()
                                         .token(jwtToken)
                                         .build();
        } else {
            throw new SolicitarCadastroException(
                    "Por favor, preenchar os campos corretamente.");
        }


    }

    public static Date getDataAtual() {
        return new Date();
    }

    public static Boolean comparateDatas(Date date1, Date date2) {
        LocalDate data1 = date1.toInstant().atZone(
                ZoneId.of("America/Puerto_Rico")).toLocalDate();
        LocalDate data2 = date2.toInstant().atZone(
                ZoneId.of("America/Puerto_Rico")).toLocalDate();

        log.info(data1);
        log.info(data2);

        if (data1.compareTo(data2) >= 0) {
            log.info(
                    data1 + " é antes, ou igual a dataAtual: " + data2);
            return true;
        }
        log.info(data1 + " é depois da dataAtual: " + data2);

        return false;
    }

}