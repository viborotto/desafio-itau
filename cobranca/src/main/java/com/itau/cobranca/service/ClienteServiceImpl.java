package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.exception.CobrancaNotFoundException;
import com.itau.cobranca.model.Cliente;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.repository.ClienteRepository;
import com.itau.cobranca.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obterCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cobranca obterCobrancaClienteCpf(String cpf, Long cobranca_id) throws ClienteNotFoundException, CobrancaNotFoundException {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);

        if (clienteOptional.isPresent()) {
            Optional<Cobranca> cobrancaOptional = cobrancaRepository.findByIdAndClienteCpf(cobranca_id, cpf);

            if (cobrancaOptional.isPresent()) {
                return cobrancaOptional.get();
            } else {
                throw new CobrancaNotFoundException("Cobrança não encontrada para o ID: " + cobranca_id + " e CPF: " + cpf);
            }
        } else {
            throw new ClienteNotFoundException("Cliente não encontrado para o CPF: " + cpf);
        }
    }

    @Override
    public Cliente criarCliente(Cliente clienteCobranca) {
        clienteCobranca.setCobrancas(new ArrayList<>());
        return clienteRepository.save(clienteCobranca);
    }
}