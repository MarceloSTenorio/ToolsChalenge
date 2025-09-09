package br.com.toolschallenge.repository.impl;

import br.com.toolschallenge.domain.enums.StatusTransacaoEnum;
import br.com.toolschallenge.domain.model.Transacao;
import br.com.toolschallenge.repository.TransacaoMongoRepository;
import br.com.toolschallenge.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransacaoRepositoryImpl implements TransacaoRepository {

    @Autowired
    private TransacaoMongoRepository transacaoMongoRepository;

    @Override
    public Transacao registrarPagamento(Transacao transacao) {
        return transacaoMongoRepository.save(transacao);
    }

    @Override
    public Transacao estornarPagamento(String id) {
        Transacao transacao = transacaoMongoRepository.findById(id).orElse(null);

        assert transacao != null;
        transacao.getDescricao().setStatus(StatusTransacaoEnum.CANCELADO);

        transacaoMongoRepository.save(transacao);

        return transacao;
    }

    @Override
    public Transacao buscarPagamentoPorId(String id) {
        return transacaoMongoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transacao> listarTodosPagamentos() {
        return transacaoMongoRepository.findAll();
    }
}
