package br.com.toolschallenge.domain.command;

import br.com.toolschallenge.domain.enums.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoCommand {

    @NotNull(message = "O tipo da forma de pagamento é obrigatório")
    private TipoPagamentoEnum tipo;

    @NotNull(message = "O número de parcelas é obrigatório")
    @DecimalMin(value = "1", message = "A quantidade mínima de parcelas é 1")
    private Integer parcelas;

}
