package br.com.toolschallenge.domain.command;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import br.com.toolschallenge.domain.command.validation.Luhn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoCommand {

    //    @Luhn(message = "Número do cartão inválido") // Validação de Luhn comentada para haver a possibilidade do status NEGADO
    @NotNull(message = "O número do cartão é obrigatório")
    @Size(max = 19, message = "O número do cartão deve ter no máximo 19 caracteres")
    private String cartao;

    @NotNull(message = "A descrição é obrigatória")
    @Valid
    private DescricaoCommand descricao;

    @NotNull(message = "A forma de pagamento é obrigatória")
    @Valid
    private FormaPagamentoCommand formaPagamento;
}
