package br.com.toolschallenge.repository;

import br.com.toolschallenge.domain.model.Transacao;

import java.util.List;

public interface TransacaoRepository {

    Transacao registrarPagamento(Transacao transacao);

    Transacao estornarPagamento(String id);

    Transacao buscarPagamentoPorId(String id);

    List<Transacao> listarTodosPagamentos();
}
