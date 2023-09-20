package com.itau.pagamento.repository;

import com.itau.pagamento.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {
    // Métodos de acesso aos dados dos boletos
}
