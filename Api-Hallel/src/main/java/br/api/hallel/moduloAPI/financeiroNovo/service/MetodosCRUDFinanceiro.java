package br.api.hallel.moduloAPI.financeiroNovo.service;

import java.util.List;

/**
 *
 * @param <T> Representa a classe que você vai trabalhar
 * @param <RQ> Representa a classe request a qual vai vim de um requisição
 * @param <RE> Representa a classe response a qual vaidar uma resposta
 */
public interface MetodosCRUDFinanceiro<T, RQ, RE> {
    public T cadastrar(RQ request);
    public Boolean editar(String id, RQ request);
    public Boolean deletar(String id);
    public List<RE> listarAll();
    public List<RE> listByPage(int pagina);
    public RE listarPorId(String id);
}
