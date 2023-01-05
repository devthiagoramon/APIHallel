package br.api.hallel.service.interfaces;

import br.api.hallel.payload.requerimento.LoginRequerimento;
import br.api.hallel.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.payload.resposta.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface MainInterface {

    public AuthenticationResponse logar(@Valid LoginRequerimento loginRequerimento);

    public AuthenticationResponse solicitarCadastro(@Valid SolicitarCadastroRequerimento solicitarCadastroRequerimento);
}
