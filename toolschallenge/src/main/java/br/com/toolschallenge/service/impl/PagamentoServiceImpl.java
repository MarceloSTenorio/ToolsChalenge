package br.com.toolschallenge.service.impl;

import br.com.toolschallenge.domain.command.PagamentoCommand;
import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.factory.TransacaoFactory;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PagamentoServiceImpl implements PagamentoService {

    private final TransacaoRepository repository;
    private final TransacaoFactory transacaoFactory;

    public TransacaoResponseDto registrarPagamento(PagamentoCommand command) {

        if (command.getTransacao().getCartao() == null) {
            throw new ApiException("Número do cartão inválido");
        }

        BigDecimal valor = command.getTransacao().getDescricao().getValor();
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException("Valor da transação inválido");
        }

        Transacao transacao = transacaoFactory.makeTransacao(command);
        Transacao transacaoSalva = repository.registrarPagamento(transacao);

        return transacaoFactory.makeTransacaoResponseDto(transacaoSalva);
    }
}
