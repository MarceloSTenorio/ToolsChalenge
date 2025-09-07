package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;

import java.util.List;

public interface ConsultaService {
    TransacaoResponseDto buscarPagamentoPorId(String id);

    List<TransacaoResponseDto> listarTodosPagamentos();
}
