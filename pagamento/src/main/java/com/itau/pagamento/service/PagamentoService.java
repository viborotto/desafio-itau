package com.itau.pagamento.service;

import com.itau.pagamento.model.CobrancaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PagamentoService {

    private RestTemplate restTemplate;

    private static final String CHARGE_MICROSERVICE_URL = "http://localhost:8080";

    @Autowired
    public void PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PagamentoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CobrancaDTO callChargeMicroservice(String clienteCpf, Long cobrancaId) {
        String url = CHARGE_MICROSERVICE_URL + "/clientes/" + clienteCpf + "/cobrancas/" + cobrancaId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<CobrancaDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CobrancaDTO.class);
        CobrancaDTO cobrancaDTO = responseEntity.getBody();

        // Process the CobrancaDTO object

        return cobrancaDTO;
    }
}