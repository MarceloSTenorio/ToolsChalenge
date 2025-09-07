package br.com.toolschallenge.domain.dto.factory;

import br.com.toolschallenge.domain.command.PagamentoCommand;
import br.com.toolschallenge.domain.command.validation.LuhnValidator;
import br.com.toolschallenge.domain.dto.DescricaoDto;
import br.com.toolschallenge.domain.dto.FormaPagamentoDto;
import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.domain.dto.TransacaoDto;
import br.com.toolschallenge.domain.enums.StatusTransacaoEnum;
import br.com.toolschallenge.domain.model.Descricao;
import br.com.toolschallenge.domain.model.FormaPagamento;
import br.com.toolschallenge.domain.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.toolschallenge.utils.mascaraCartao.MascaraCartao.mascararCartao;

@Component
public class PagamentoFactory {

    public Transacao makeTransacao(PagamentoCommand command) {
        LuhnValidator luhnValidator = new LuhnValidator();
        boolean cartaoValido = luhnValidator.isValid(command.getTransacao().getCartao(), null);

        Transacao transacao = new Transacao();
        transacao.setCartao(command.getTransacao().getCartao());

        Descricao descricao = new Descricao();
        descricao.setValor(command.getTransacao().getDescricao().getValor());
        descricao.setEstabelecimento(command.getTransacao().getDescricao().getEstabelecimento());
        descricao.setStatus(cartaoValido ? StatusTransacaoEnum.AUTORIZADO : StatusTransacaoEnum.NEGADO);
        descricao.atribuirDataHoraAtual();
        transacao.setDescricao(descricao);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(command.getTransacao().getFormaPagamento().getTipo());
        formaPagamento.setParcelas(command.getTransacao().getFormaPagamento().getParcelas());
        transacao.setFormaPagamento(formaPagamento);

        return transacao;
    }

    public TransacaoResponseDto makeTransacaoResponseDto(Transacao transacao) {
        return TransacaoResponseDto.builder()
                .transacao(makeTransacaoDto(transacao))
                .build();
    }

    public List<TransacaoResponseDto> makeListaTransacoesResponseDto(List<Transacao> transacoes) {
        return transacoes.stream()
                .map(this::makeTransacaoResponseDto)
                .collect(Collectors.toList());
    }

    private TransacaoDto makeTransacaoDto(Transacao transacao) {
        return TransacaoDto.builder()
                .id(transacao.getId().toString())
                .cartao(mascararCartao(transacao.getCartao()))
                .descricao(makeDescricaoDto(transacao.getDescricao()))
                .formaPagamento(makeFormaPagamentoDto(transacao.getFormaPagamento()))
                .build();
    }

    private DescricaoDto makeDescricaoDto(Descricao descricao) {
        return DescricaoDto.builder()
                .valor(descricao.getValor() != null ? descricao.getValor().toString() : null)
                .dataHora(descricao.getDataHora())
                .estabelecimento(descricao.getEstabelecimento())
                .nsu(descricao.getNsu() != null ? descricao.getNsu().toString() : null)
                .codigoAutorizacao(descricao.getCodigoAutorizacao() != null ? descricao.getCodigoAutorizacao().toString() : null)
                .status(descricao.getStatus())
                .build();
    }

    private FormaPagamentoDto makeFormaPagamentoDto(FormaPagamento formaPagamento) {
        return FormaPagamentoDto.builder()
                .tipo(formaPagamento.getTipo())
                .parcelas(formaPagamento.getParcelas())
                .build();
    }
}
