package com.itau.pagamento.service;

import com.itau.pagamento.exceptions.ClienteNotFoundException;
import com.itau.pagamento.exceptions.CobrancaNotFoundException;
import com.itau.pagamento.model.Cliente;
import com.itau.pagamento.model.Cobranca;

import java.util.List;

public interface ClienteService {

    Cliente criarCliente(Cliente cliente);
}
