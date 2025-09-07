package br.com.toolschallenge.service.impl;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.factory.PagamentoFactory;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultaServiceImpl implements ConsultaService {

    private final TransacaoRepository repository;
    private final PagamentoFactory pagamentoFactory;

    public TransacaoResponseDto buscarPagamentoPorId(String id) {
        Transacao transacaoResponseEntity = repository.buscarPagamentoPorId(id);

        if (transacaoResponseEntity == null) {
            throw new ApiException("Transação não encontrada para o ID: " + id);
        }
        return pagamentoFactory.makeTransacaoResponseDto(transacaoResponseEntity);
    }

    public List<TransacaoResponseDto> listarTodosPagamentos() {
        List<Transacao> transacoes = repository.listarTodosPagamentos();

        if (transacoes == null) {
            throw new ApiException("Não existem transações feitas");
        }

        return pagamentoFactory.makeListaTransacoesResponseDto(transacoes);
    }
}
