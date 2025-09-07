package br.com.toolschallenge.domain.model;

import br.com.toolschallenge.domain.enums.TipoPagamentoEnum;
import lombok.Data;

@Data
public class FormaPagamento {
    private TipoPagamentoEnum tipo;
    private Integer parcelas;
}
