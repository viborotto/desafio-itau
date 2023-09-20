package com.itau.pagamento.controller;

import com.itau.pagamento.exceptions.CobrancaNotFoundException;
import com.itau.pagamento.model.Cobranca;
import com.itau.pagamento.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boletos")
public class BoletoController {
    @Autowired
    private CobrancaService cobrancaService;

    @PostMapping("/{cobrancaId}/pagar")
    public ResponseEntity<Cobranca> pagarBoleto(@PathVariable Long cobrancaId, @RequestParam double valorPago) throws CobrancaNotFoundException {
        Cobranca cobrancaAtualizada = cobrancaService.realizarPagamento(cobrancaId, valorPago);
        return ResponseEntity.ok(cobrancaAtualizada);
    }
}
