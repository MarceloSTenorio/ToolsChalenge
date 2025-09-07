package br.com.toolschallenge.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTransacaoEnum {
    AUTORIZADO("AUTORIZADO"),
    CANCELADO("CANCELADO"),
    NEGADO("NEGADO");

    private String status;
}
