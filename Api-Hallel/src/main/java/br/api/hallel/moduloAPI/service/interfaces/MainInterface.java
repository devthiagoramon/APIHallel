package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.payload.requerimento.LoginRequerimento;
import br.api.hallel.moduloAPI.payload.requerimento.LoginRequerimentoGoogle;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroGoogle;
import br.api.hallel.moduloAPI.payload.requerimento.SolicitarCadastroRequerimento;
import br.api.hallel.moduloAPI.payload.resposta.AuthenticationResponse;
import jakarta.validation.Valid;

public interface MainInterface {

    public AuthenticationResponse logar(@Valid LoginRequerimento loginRequerimento);
    public AuthenticationResponse solicitarCadastro(@Valid SolicitarCadastroRequerimento solicitarCadastroRequerimento);
    public AuthenticationResponse logarGoogle(@Valid LoginRequerimentoGoogle loginRequerimento);
    public AuthenticationResponse solicitarCadastroGoogle(@Valid SolicitarCadastroGoogle solicitarCadastroGoogle);

}
