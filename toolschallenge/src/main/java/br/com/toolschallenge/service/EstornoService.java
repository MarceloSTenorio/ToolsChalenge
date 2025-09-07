package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;

public interface EstornoService {
    TransacaoResponseDto estornarPagamento(String idTransacao);
}
