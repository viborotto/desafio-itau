package com.itau.cobranca.controller;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.ClienteCobranca;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.service.ClienteService;
import com.itau.cobranca.service.CobrancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteCobranca> listarCobrancas() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteCobranca obterCobranca(@PathVariable Long id) {
        return clienteService.obterCliente(id);
    }

    @PostMapping
    public ClienteCobranca criarCobranca(@RequestBody ClienteCobranca clienteCobranca) throws ClienteNotFoundException {
        return clienteService.criarCliente(clienteCobranca);
    }
}
