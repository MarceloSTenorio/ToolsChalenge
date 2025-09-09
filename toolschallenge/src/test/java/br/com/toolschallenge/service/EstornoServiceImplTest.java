package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.TransacaoDto;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.domain.dto.factory.TransacaoFactory;
import br.com.toolschallenge.service.impl.EstornoServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstornoServiceImplTest {

    @Mock
    private TransacaoRepository repository;

    @Mock
    private TransacaoFactory transacaoFactory;

    @InjectMocks
    private EstornoServiceImpl estornoService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void deveEstornarPagamentoComSucesso() {
        String id = "12345";

        Transacao transacaoMock = new Transacao();
        TransacaoDto transacaoDtoMock = TransacaoDto.builder().build();
        TransacaoResponseDto responseMock = TransacaoResponseDto.builder()
                .transacao(transacaoDtoMock)
                .build();

        when(repository.estornarPagamento(id)).thenReturn(transacaoMock);
        when(transacaoFactory.makeTransacaoResponseDto(transacaoMock)).thenReturn(responseMock);

        TransacaoResponseDto result = estornoService.estornarPagamento(id);

        assertNotNull(result);
        assertEquals(responseMock, result);

        verify(repository, times(1)).estornarPagamento(id);
        verify(transacaoFactory, times(1)).makeTransacaoResponseDto(transacaoMock);
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoEncontrada() {
        String id = "99999";

        when(repository.estornarPagamento(id)).thenReturn(null);

        ApiException thrown = assertThrows(ApiException.class,
                () -> estornoService.estornarPagamento(id));

        assertTrue(thrown.getMessage().contains("Transação não encontrada"));
        verify(repository, times(1)).estornarPagamento(id);
        verifyNoInteractions(transacaoFactory);
    }
}