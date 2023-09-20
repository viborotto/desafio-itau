package com.itau.pagamento.service;

import com.itau.pagamento.model.Cobranca;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BoletoService {

    public String gerarCodigoDeBarras() {
        UUID uuid=UUID.randomUUID();
        // Retornando o código de barras como String
        return uuid.toString();
    }
}
