package br.com.toolschallenge.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoPagamentoEnum {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");

    private final String tipo;

    @JsonCreator
    public static TipoPagamentoEnum fromString(String tipo) {
        for (TipoPagamentoEnum value : TipoPagamentoEnum.values()) {
            if (value.getTipo().equalsIgnoreCase(tipo)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipo);
    }

    @JsonValue
    public String getTipo() {
        return tipo;
    }
}
