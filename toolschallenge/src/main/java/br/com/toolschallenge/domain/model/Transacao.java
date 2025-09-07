package br.com.toolschallenge.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "transacoes")
public class Transacao {

    @Id
    private String id = UUID.randomUUID().toString();
    private String cartao;
    private Descricao descricao;
    private FormaPagamento formaPagamento;
}
