package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.exception.CobrancaNotFoundException;
import com.itau.cobranca.model.Cliente;
import com.itau.cobranca.model.Cobranca;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();

    Cliente obterCliente(Long id);

    Cobranca obterCobrancaClienteCpf(String cpf, Long cobranca_id) throws ClienteNotFoundException, CobrancaNotFoundException;

    Cliente criarCliente(Cliente cliente);
}
