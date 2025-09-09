package br.com.toolschallenge.service.impl;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.factory.TransacaoFactory;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.service.EstornoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EstornoServiceImpl implements EstornoService {

    private final TransacaoRepository repository;
    private final TransacaoFactory transacaoFactory;

    public TransacaoResponseDto estornarPagamento(String id) {

        Transacao transacaoResponseEntity = repository.estornarPagamento(id);

        if (transacaoResponseEntity == null) {
            throw new ApiException("Transação não encontrada para o ID: " + id);
        }

        return transacaoFactory.makeTransacaoResponseDto(transacaoResponseEntity);
    }
}
