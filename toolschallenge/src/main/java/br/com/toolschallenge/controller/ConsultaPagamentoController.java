package br.com.toolschallenge.controller;

import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consulta")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultaPagamentoController {
    private final ConsultaService consultaService;

    @GetMapping("/transacao")
    public ResponseEntity<TransacaoResponseDto> buscarPagamentoPorId(@Valid @RequestParam String id) {
        TransacaoResponseDto pagamento = consultaService.buscarPagamentoPorId(id);
        return ResponseEntity.ok(pagamento);
    }

    @GetMapping("/transacoes")
    public ResponseEntity<List<TransacaoResponseDto>> listarTodosPagamentos() {
        List<TransacaoResponseDto> listaPagamento = consultaService.listarTodosPagamentos();
        return ResponseEntity.ok(listaPagamento);
    }
}
