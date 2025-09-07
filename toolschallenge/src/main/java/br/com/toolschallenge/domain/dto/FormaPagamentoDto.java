package br.com.toolschallenge.domain.dto;

import br.com.toolschallenge.domain.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoDto {
    private TipoPagamentoEnum tipo;
    private Integer parcelas;
}
