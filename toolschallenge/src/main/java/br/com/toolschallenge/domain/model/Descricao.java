package br.com.toolschallenge.domain.model;

import br.com.toolschallenge.domain.enums.StatusTransacaoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
public class Descricao {
    private BigDecimal valor;
    private String dataHora;
    private String estabelecimento;
    private UUID nsu = UUID.randomUUID();
    private UUID codigoAutorizacao = UUID.randomUUID();
    private StatusTransacaoEnum status;

    public void atribuirDataHoraAtual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataHora = LocalDateTime.now().format(formatter);
    }
}
