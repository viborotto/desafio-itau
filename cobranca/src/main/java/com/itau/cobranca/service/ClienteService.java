package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.exception.CobrancaNotFoundException;
import com.itau.cobranca.model.ClienteCobranca;
import com.itau.cobranca.model.Cobranca;

import java.util.List;

public interface ClienteService {
    List<ClienteCobranca> listarClientes();

    ClienteCobranca obterCliente(Long id);

    Cobranca obterCobrancaClienteCpf(String cpf, Long cobranca_id) throws ClienteNotFoundException, CobrancaNotFoundException;

    ClienteCobranca criarCliente(ClienteCobranca cliente);
}
