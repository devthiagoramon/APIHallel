package br.api.hallel.moduloAPI.service.interfaces;

import br.api.hallel.moduloAPI.model.EventoArquivado;

import java.util.List;

public interface EventoArquivadoInterface {

    public void addEventoArquivado(String idEvento);
    public void retirarEventoArquivado(String idEvento);
    public void excluirEventoArquivado(String idEvento);

    List<EventoArquivado> listarEventosArquivados();
}
