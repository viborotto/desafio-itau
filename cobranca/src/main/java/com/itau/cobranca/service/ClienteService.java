package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.ClienteCobranca;
import com.itau.cobranca.model.Cobranca;

import java.util.List;

public interface ClienteService {
    List<ClienteCobranca> listarClientes();

    ClienteCobranca obterCliente(Long id);

    ClienteCobranca criarCliente(ClienteCobranca cliente);
}
