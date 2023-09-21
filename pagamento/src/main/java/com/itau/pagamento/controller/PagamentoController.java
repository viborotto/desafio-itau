package com.itau.pagamento.controller;

import com.itau.pagamento.exceptions.CobrancaNotFoundException;
import com.itau.pagamento.model.Cobranca;
import com.itau.pagamento.model.CobrancaDTO;
import com.itau.pagamento.service.CobrancaService;
import com.itau.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final CobrancaService cobrancaService;

//    private final CobrancaServiceImpl cobrancaServiceImpl;

//    @Autowired
//    private CobrancaClient cobrancaClient;

    @Autowired
    private PagamentoService pagamentoService;

    public PagamentoController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }


    @PostMapping("/boleto/{cobranca_id}/pagar")
    public ResponseEntity<Cobranca> pagarBoleto(@PathVariable Long cobranca_id, @RequestParam double valorPago) throws CobrancaNotFoundException {
        Cobranca cobrancaAtualizada = cobrancaService.realizarPagamento(cobranca_id, valorPago);
        return ResponseEntity.ok(cobrancaAtualizada);
    }

    @PostMapping("/{cobranca_id}/emitir-boleto")
    public ResponseEntity<Cobranca> emitirBoleto(@PathVariable Long cobranca_id, @RequestBody Cobranca cobranca) {
        Cobranca cobrancaEmitida = cobrancaService.emitirBoleto(cobranca);
        return ResponseEntity.ok(cobrancaEmitida);
    }

    @PostMapping("/clientes/{cliente_cpf}/cobrancas/{cobranca_id}/pagar-debito")
    public ResponseEntity<CobrancaDTO> pagarDebito(@PathVariable String cliente_cpf, @PathVariable Long cobranca_id) {
        CobrancaDTO callResponse = pagamentoService.callChargeMicroservice(cliente_cpf, cobranca_id);

        return ResponseEntity.ok(callResponse);
    }
}
