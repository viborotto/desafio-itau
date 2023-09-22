package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.Cliente;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.repository.ClienteRepository;
import com.itau.cobranca.repository.CobrancaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado para o ID: " + clienteId));

        cobranca.setCliente(cliente);
//        canaisCobranca.cobrancaEmail(cobranca);
//        canaisCobranca.cobrancaSNS(cobranca);
        return cobrancaRepository.save(cobranca);
    }

    @Override
    @Transactional
    public void deleteCobrancaByClienteId(Long clienteId) {
        cobrancaRepository.deleteByClienteId(clienteId);
    }


}
