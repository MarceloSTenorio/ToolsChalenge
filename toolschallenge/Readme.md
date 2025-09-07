# ToolsChallenge - API de Pagamentos

## Descrição
Este projeto é uma API REST desenvolvida em Java 8, utilizando Spring Boot e MongoDB, para gerenciamento de transações de pagamento. O sistema foi estruturado com foco em boas práticas de arquitetura e padrões de projeto.

## Padrões de Projeto Utilizados
- **Command:** Encapsula os dados das requisições em objetos específicos, facilitando a validação e o transporte das informações.
- **Factory:** Responsável pela criação dos DTOs de resposta, centralizando a lógica de montagem dos objetos.
- **Builder:** Utilizado para facilitar a construção dos objetos de domínio e DTOs, tornando o código mais legível e flexível.
- **Repository:** Utilizado para abstrair a comunicação com o banco de dados.

## Pré-requisitos
- Java 8+
- Docker e Docker Compose
- Maven

## Como executar
1. **Suba o MongoDB com Docker antes de iniciar a aplicação:**
   ```sh
   docker-compose up -d
   ```
2. **Execute a aplicação:**
   ```sh
   ./mvnw spring-boot:run
   ```

## Endpoints Principais

### 1. Registrar Pagamento
- **POST** `/transacao/pagamento`
- **Body para transação autorizada:**

```json
{
  "transacao": {
    "cartao": "5304610409111869",
    "descricao": {
      "valor": 500.50,
      "estabelecimento": "PetShop Mundo cão"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": 1
    }
  }
}
```

> **Observação:** O número do cartão é validado pelo algoritmo de Luhn. Para obter uma transação **autorizada**, utilize um cartão válido como acima.

- **Body para transação negada:**

```json
{
  "transacao": {
    "cartao": "4444333322221112",
    "descricao": {
      "valor": 500.50,
      "estabelecimento": "Tem que ser Negado"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": 1
    }
  }
}
```

> **Observação:** Cartões inválidos pelo Luhn terão o status **Negado**.

### 2. Estornar Pagamento
- **PUT** `/transacao/estorno?id={idTransacao}`

  Basta informar o `id` da transação que deseja cancelar/estornar.

## Validações
- O número do cartão é validado pelo algoritmo de Luhn.
- O valor da transação não pode ser negativo.
- O tipo de pagamento deve ser um dos valores aceitos pelo sistema.
- Campos obrigatórios são validados automaticamente.

## Testes
Os testes unitários estão localizados em `src/test/java/br/com/toolschallenge/service/` e cobrem os principais fluxos de negócio e validações.

---
