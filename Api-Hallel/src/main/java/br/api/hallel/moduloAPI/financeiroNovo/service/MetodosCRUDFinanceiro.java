package br.api.hallel.moduloAPI.financeiroNovo.service;

import java.util.List;

/**
 *
 * @param <T> Representa a classe que você vai trabalhar
 * @param <R> Representa a classe request a qual vai vim de um requisição
 */
public interface MetodosCRUDFinanceiro<T, R> {
    public void cadastrar(R request);
    public void editar(String id, R request);
    public void deletar(String id);
    public List<T> listarAll();
    public T listarPorId(String id);
}
