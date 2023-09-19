package com.itau.cobranca.controller;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {

    @Autowired
    private CobrancaService cobrancaService;

    @GetMapping
    public List<Cobranca> listarCobrancas() {
        return cobrancaService.listarCobrancas();
    }

    @GetMapping("/{id}")
    public Cobranca obterCobranca(@PathVariable Long id) {
        return cobrancaService.obterCobranca(id);
    }

    @PostMapping("/{clienteId}")
    public Cobranca criarCobranca(@RequestBody Cobranca cobranca, @PathVariable Long clienteId) throws ClienteNotFoundException {
        return cobrancaService.criarCobranca(cobranca, clienteId);
    }
}
