package br.com.toolschallenge.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DescricaoCommand {

    @NotNull(message = "O valor da transação é obrigatório")
    @DecimalMin(value = "0.00", inclusive = false, message = "Valor da transação não pode ser negativo")
    private BigDecimal valor;

    @NotBlank(message = "O estabelecimento é obrigatório")
    private String estabelecimento;
}
