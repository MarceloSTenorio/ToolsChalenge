package br.com.toolschallenge.repository;

import br.com.toolschallenge.domain.model.Transacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransacaoMongoRepository extends MongoRepository<Transacao, String> {
}
