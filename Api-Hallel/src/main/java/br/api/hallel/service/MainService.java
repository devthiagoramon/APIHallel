package br.api.hallel.service;

import br.api.hallel.model.*;
import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AdministradorResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class MainService implements MainInterface {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private MembroGoogleRepository googleRepository;

    @Override
    public AuthenticationResponse logar(@Valid LoginRequerimento loginRequerimento) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequerimento.getEmail(), loginRequerimento.getSenha()
                )
        );
        if (membroRepository.findByEmail(loginRequerimento.getEmail()).isPresent()) {
            var membro = membroRepository.findByEmail(loginRequerimento.getEmail()).get();
            System.out.println("Membro");
            if(membro.getStatus().equals(StatusMembro.ATIVO)) {
                var jwtToken = jwtService.generateToken(membro);
                MembroResponse membroResponse = new MembroResponse();
                membroResponse.setNome(membro.getNome());
                membroResponse.setEmail(membro.getEmail());
                membroResponse.setRoles(membro.getRoles());
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .objeto(membroResponse)
                        .build();
            }
        }
        if (administradorRepository.findByEmail(loginRequerimento.getEmail()).isPresent()) {
            var administrador = administradorRepository.findByEmail(loginRequerimento.getEmail()).get();
            System.out.println("Adm");
            var jwtToken = jwtService.generateToken(administrador);
            AdministradorResponse administradorResponse = new AdministradorResponse();
            administradorResponse.setEmail(administrador.getEmail());
            administradorResponse.setNome(administrador.getNome());
            administradorResponse.setRoles(administrador.getRoles());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .objeto(administradorResponse)
                    .build();
        }
        return null;
    }

    @Override
    public AuthenticationResponse solicitarCadastro(SolicitarCadastroRequerimento solicitarCadastroRequerimento) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(ERole.ROLE_USER).get() : null);
        var membro = new Membro();
        membro.setNome(solicitarCadastroRequerimento.getNome());
        membro.setEmail(solicitarCadastroRequerimento.getEmail());
        membro.setSenha(encoder.encode(solicitarCadastroRequerimento.getSenha()));
        membro.setRoles(roles);
        membro.setStatus(StatusMembro.PENDENTE);

        membroRepository.save(membro);
        var jwtToken = jwtService.generateToken(membro);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

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

            var membro = googleRepository.findByEmail(loginRequerimentoGoogle.getEmail()).get();
            System.out.println("Membro Google" );

            if (membro.getStatus().equals(StatusMembro.ATIVO)) {
                var jwtToken = jwtService.generateToken(membro);

                System.out.println("CERTO");

                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .objeto(membro)
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

        return  null;
    }

    @Override
    public AuthenticationResponse solicitarCadastroGoogle(SolicitarCadastroGoogle solicitarCadastroGoogle) {

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ROLE_USER).isPresent() ?
                roleRepository.findByName(ERole.ROLE_USER).get() : null);
        var membro = new MembroGoogle();

        membro.setNome(solicitarCadastroGoogle.getNome());
        membro.setEmail(solicitarCadastroGoogle.getEmail());
        membro.setRoles(roles);
        membro.setStatus(StatusMembro.PENDENTE);

        googleRepository.save(membro);
        var jwtToken = jwtService.generateToken(membro);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}