package br.com.toolschallenge.service;

import br.com.toolschallenge.domain.command.DescricaoCommand;
import br.com.toolschallenge.domain.command.FormaPagamentoCommand;
import br.com.toolschallenge.domain.command.PagamentoCommand;
import br.com.toolschallenge.domain.command.TransacaoCommand;
import br.com.toolschallenge.domain.dto.TransacaoDto;
import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.factory.PagamentoFactory;
import br.com.toolschallenge.domain.enums.TipoPagamentoEnum;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.exception.ApiException;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.service.impl.PagamentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class PagamentoServiceImplTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private PagamentoFactory pagamentoFactory;

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRegistrarPagamentoComDadosValidos() {
        PagamentoCommand command = PagamentoCommand.builder()
                .transacao(TransacaoCommand.builder()
                        .cartao("5304610409111869")
                        .descricao(DescricaoCommand.builder()
                                .valor(BigDecimal.valueOf(100.00))
                                .estabelecimento("Loja Teste")
                                .build())
                        .formaPagamento(FormaPagamentoCommand.builder()
                                .tipo(TipoPagamentoEnum.AVISTA)
                                .parcelas(1)
                                .build())
                        .build())
                .build();

        Transacao transacaoMock = new Transacao();
        when(pagamentoFactory.makeTransacao(command)).thenReturn(transacaoMock);

        Transacao transacaoSalvaMock = new Transacao();
        when(transacaoRepository.registrarPagamento(transacaoMock)).thenReturn(transacaoSalvaMock);

        TransacaoResponseDto responseMock = TransacaoResponseDto.builder()
                .transacao(new TransacaoDto())
                .build();
        when(pagamentoFactory.makeTransacaoResponseDto(transacaoSalvaMock)).thenReturn(responseMock);

        TransacaoResponseDto result = pagamentoService.registrarPagamento(command);

        assertNotNull(result);
        verify(pagamentoFactory, times(1)).makeTransacao(command);
        verify(transacaoRepository, times(1)).registrarPagamento(transacaoMock);
        verify(pagamentoFactory, times(1)).makeTransacaoResponseDto(transacaoSalvaMock);
    }

    @Test
    void deveLancarExcecaoQuandoCartaoForNulo() {
        PagamentoCommand command = PagamentoCommand.builder()
                .transacao(TransacaoCommand.builder()
                        .cartao(null)
                        .descricao(DescricaoCommand.builder()
                                .valor(BigDecimal.valueOf(100.00))
                                .estabelecimento("Loja Teste")
                                .build())
                        .formaPagamento(FormaPagamentoCommand.builder()
                                .tipo(TipoPagamentoEnum.AVISTA)
                                .parcelas(1)
                                .build())
                        .build())
                .build();

        assertThrows(ApiException.class, () -> pagamentoService.registrarPagamento(command));
    }

    @Test
    void deveLancarExcecaoQuandoValorNegativo() {
        PagamentoCommand command = PagamentoCommand.builder()
                .transacao(TransacaoCommand.builder()
                        .cartao("5304610409111869")
                        .descricao(DescricaoCommand.builder()
                                .valor(BigDecimal.valueOf(-10.00))
                                .estabelecimento("Loja Teste")
                                .build())
                        .formaPagamento(FormaPagamentoCommand.builder()
                                .tipo(TipoPagamentoEnum.AVISTA)
                                .parcelas(1)
                                .build())
                        .build())
                .build();

        assertThrows(ApiException.class, () -> pagamentoService.registrarPagamento(command));
    }
}

