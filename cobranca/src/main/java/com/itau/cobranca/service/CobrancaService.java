package com.itau.cobranca.service;

import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.Cobranca;

import java.util.List;

public interface CobrancaService {

    List<Cobranca> listarCobrancas();

    Cobranca obterCobranca(Long id);

    Cobranca criarCobranca(Cobranca cobranca, Long clienteId) throws ClienteNotFoundException;
}

