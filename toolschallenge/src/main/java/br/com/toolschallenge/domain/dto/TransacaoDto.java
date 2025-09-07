package br.com.toolschallenge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDto {
    private String cartao;
    private String id;
    private DescricaoDto descricao;
    private FormaPagamentoDto formaPagamento;
}
