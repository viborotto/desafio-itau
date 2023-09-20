package com.itau.pagamento.controller;

import com.itau.pagamento.model.Cobranca;
import com.itau.pagamento.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {
    @Autowired
    private CobrancaService cobrancaService;

    @PostMapping("/emitir-boleto")
    public ResponseEntity<Cobranca> emitirBoleto(@RequestBody Cobranca cobranca) {
        Cobranca cobrancaEmitida = cobrancaService.emitirBoleto(cobranca);
        return ResponseEntity.ok(cobrancaEmitida);
    }
}
