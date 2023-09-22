package com.itau.pagamento.service;

import com.itau.pagamento.model.Cliente;
import com.itau.pagamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente criarCliente(Cliente clienteCobranca) {
        clienteCobranca.setCobrancas(new ArrayList<>());
        return clienteRepository.save(clienteCobranca);
    }
}
