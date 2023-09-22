package com.itau.pagamento.service;

import com.itau.pagamento.model.Cliente;
import com.itau.pagamento.model.CobrancaDTO;
import com.itau.pagamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PagamentoService {

    private RestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String CHARGE_MICROSERVICE_URL = "http://localhost:8080";

    @Autowired
    public void PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PagamentoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cliente callChargeMicroservice(String clienteCpf, Long cobrancaId) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(clienteCpf);
        String url = CHARGE_MICROSERVICE_URL + "/clientes/" + clienteCpf + "/cobrancas/" + cobrancaId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<CobrancaDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CobrancaDTO.class);
        CobrancaDTO cobrancaDTO = responseEntity.getBody();
        // realizar o pagamento subtraindo o valor do saldo da conta do cliente
        Double saldoDebitado = clienteOptional.get().getSaldo() - cobrancaDTO.getValor();
        Cliente cliente = clienteOptional.get();
        cliente.setSaldo(saldoDebitado);  // Set the value for your attribute
        clienteRepository.save(cliente);
        return cliente;
    }
}