package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.ClienteCobranca;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.repository.ClienteRepository;
import com.itau.cobranca.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.util.List;

@Service
public class CobrancaServiceImpl implements CobrancaService {

    private CanaisCobranca canaisCobranca;

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cobranca> listarCobrancas() {
        return cobrancaRepository.findAll();
    }

    @Override
    public Cobranca obterCobranca(Long id) {
        return cobrancaRepository.findById(id).orElse(null);
    }

    @Override
    public Cobranca criarCobranca(Cobranca cobranca, Long clienteId) throws ClienteNotFoundException {
        // Verifica se o cliente existe
        ClienteCobranca cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o ID: " + clienteId));

        cobranca.setCliente(cliente); // Associa a cobrança ao cliente
//        canaisCobranca.cobrancaEmail(cobranca);
//        canaisCobranca.cobrancaSNS(cobranca);
        return cobrancaRepository.save(cobranca);
    }


}
