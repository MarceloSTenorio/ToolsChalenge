package br.com.toolschallenge.controller;

import br.com.toolschallenge.domain.command.PagamentoCommand;
import br.com.toolschallenge.domain.dto.TransacaoResponseDto;
import br.com.toolschallenge.service.EstornoService;
import br.com.toolschallenge.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransacaoController {

    private final PagamentoService pagamentoService;
    private final EstornoService estornoService;

    @PostMapping("/pagamento")
    public ResponseEntity<TransacaoResponseDto> efetuarPagamento(@Valid @RequestBody PagamentoCommand command) {
        TransacaoResponseDto transacaoResponseDto = pagamentoService.registrarPagamento(command);
        return ResponseEntity.ok(transacaoResponseDto);
    }

    @PutMapping("/estorno")
    public ResponseEntity<TransacaoResponseDto> efetuarEstorno(@RequestParam String id) {
        TransacaoResponseDto transacaoResponseDto = estornoService.estornarPagamento(id);
        return ResponseEntity.ok(transacaoResponseDto);
    }
}
