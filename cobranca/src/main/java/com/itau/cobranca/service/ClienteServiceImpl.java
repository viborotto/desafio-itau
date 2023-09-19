package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.ClienteCobranca;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.repository.ClienteRepository;
import com.itau.cobranca.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteCobranca> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteCobranca obterCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public ClienteCobranca criarCliente(ClienteCobranca clienteCobranca) {
        clienteCobranca.setCobrancas(new ArrayList<>());
        return clienteRepository.save(clienteCobranca);
    }
}