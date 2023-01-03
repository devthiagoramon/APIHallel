package br.api.hallel.service.interfaces;

import java.util.List;

import br.api.hallel.model.Administrador;

public interface AdministradorInterface {
    
    public String inserirAdministrador(Administrador administrador);
    public List<Administrador> listarTodosAdministradores();
    public Administrador findAdministrador(String id);
    public Administrador findAdministradorEmail(String email);
    public Administrador acessarAdministrador(String id, String senhaAcesso);
    public String alterarAdministrador(String id, Administrador administradorNovo);
    public String deletarAdministrador(String id);

}
