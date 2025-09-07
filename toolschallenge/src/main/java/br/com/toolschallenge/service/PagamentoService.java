package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.command.PagamentoCommand;
import br.com.toolschallenge.domain.dto.TransacaoResponseDto;

public interface PagamentoService {
    TransacaoResponseDto registrarPagamento(PagamentoCommand command);
}
