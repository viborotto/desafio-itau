package com.itau.pagamento.service;

import com.itau.pagamento.exceptions.CobrancaNotFoundException;
import com.itau.pagamento.model.Boleto;
import com.itau.pagamento.model.Cobranca;
import com.itau.pagamento.model.CobrancaDTO;
import com.itau.pagamento.repository.BoletoRepository;
import com.itau.pagamento.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CobrancaService {
    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private BoletoService boletoService;

//    @Autowired
//    private CobrancaClient cobrancaClient;
//
//    public CobrancaDTO getCobranca(String cpf, Long cliente_id){
//        CobrancaDTO byCobrancaId = cobrancaClient.findByCobrancaId(cpf, cliente_id);
//        return byCobrancaId;
//    }

    public Cobranca emitirBoleto(Cobranca cobranca) {
        String codigoDeBarrasGerado = boletoService.gerarCodigoDeBarras();

        // Associe o boleto à cobrança
        Boleto boleto = new Boleto();
        boleto.setCodigoDeBarras(codigoDeBarrasGerado);
        boleto.setCobranca(cobranca);

        cobranca.setBoleto(boleto);

        return cobrancaRepository.save(cobranca);
    }

    public Cobranca realizarPagamento(Long cobrancaId, double valorPago) throws CobrancaNotFoundException {
        Optional<Cobranca> cobrancaOptional = cobrancaRepository.findById(cobrancaId);
        if (cobrancaOptional.isPresent()) {
            Cobranca cobranca = cobrancaOptional.get();

            // Atualize a cobrança
            cobranca.setValorPago(valorPago);
            cobranca.setDataPagamento(new Date());

            return cobrancaRepository.save(cobranca);
        } else {
            throw new CobrancaNotFoundException("Cobrança não encontrada para o ID: " + cobrancaId);
        }
    }
}
