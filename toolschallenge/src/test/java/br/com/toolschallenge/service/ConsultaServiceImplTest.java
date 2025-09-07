package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.factory.PagamentoFactory;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.service.impl.ConsultaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ConsultaServiceImplTest {

    @Mock
    private TransacaoRepository repository;

    @Mock
    private PagamentoFactory pagamentoFactory;

    @InjectMocks
    private ConsultaServiceImpl consultaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTransacaoAoBuscarPorIdExistente() {
        Transacao transacaoMock = new Transacao();
        TransacaoResponseDto dtoMock = new TransacaoResponseDto();

        String id = "1";

        when(repository.buscarPagamentoPorId(id)).thenReturn(transacaoMock);
        when(pagamentoFactory.makeTransacaoResponseDto(transacaoMock)).thenReturn(dtoMock);

        TransacaoResponseDto result = consultaService.buscarPagamentoPorId(id);

        assertNotNull(result);
        verify(repository, times(1)).buscarPagamentoPorId(id);
        verify(pagamentoFactory, times(1)).makeTransacaoResponseDto(transacaoMock);
    }

    @Test
    void deveLancarExcecaoAoBuscarPorIdInexistente() {
        String id = "99";
        when(repository.buscarPagamentoPorId(id)).thenReturn(null);
        assertThrows(ApiException.class, () -> consultaService.buscarPagamentoPorId(id));
    }
}

