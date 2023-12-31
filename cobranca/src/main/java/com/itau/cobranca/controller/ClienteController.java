package com.itau.cobranca.controller;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.exception.CobrancaNotFoundException;
import com.itau.cobranca.model.Cliente;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarCobrancas() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente obterCobranca(@PathVariable Long id) {
        return clienteService.obterCliente(id);
    }

    //obter o cliente com base no cpf e a respectiva cobranca
    @GetMapping("/{cliente_cpf}/cobrancas/{cobranca_id}")
    public Cobranca obterCobrancaClienteCPF(@PathVariable String cliente_cpf, @PathVariable Long cobranca_id) throws ClienteNotFoundException, CobrancaNotFoundException {
        return clienteService.obterCobrancaClienteCpf(cliente_cpf, cobranca_id);
    }

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) throws ClienteNotFoundException {
        return clienteService.criarCliente(cliente);
    }
}
