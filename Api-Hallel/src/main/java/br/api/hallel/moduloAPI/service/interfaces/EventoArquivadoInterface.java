package br.api.hallel.moduloAPI.service.interfaces;

public interface EventoArquivadoInterface {

    public void addEventoArquivado(String idEvento);
    public void retirarEventoArquivado(String idEvento);
    public void excluirEventoArquivado(String idEvento);
}
