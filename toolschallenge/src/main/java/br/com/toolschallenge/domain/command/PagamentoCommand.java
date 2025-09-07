package br.com.toolschallenge.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoCommand {
    @NotNull(message = "A Transação deve estar preenchida")
    @Valid
    TransacaoCommand transacao;
}
