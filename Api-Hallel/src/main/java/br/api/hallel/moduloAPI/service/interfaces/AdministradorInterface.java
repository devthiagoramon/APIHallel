package br.api.hallel.moduloAPI.service.interfaces;

import java.util.List;

import br.api.hallel.moduloAPI.model.Administrador;
import br.api.hallel.moduloAPI.payload.requerimento.CadAdministradorRequerimento;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AdministradorInterface {
    
    public ResponseEntity<?> inserirAdministrador(@Valid CadAdministradorRequerimento administradorReq);
    public List<Administrador> listarTodosAdministradores();
    public Administrador findAdministrador(String id);
    public Administrador findAdministradorEmail(String email);
    public Administrador acessarAdministrador(String id, String senhaAcesso);
    public String alterarAdministrador(String id, Administrador administradorNovo);
    public String deletarAdministrador(String id);

}
