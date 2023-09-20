package com.itau.pagamento.repository;

import com.itau.pagamento.model.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
    // Métodos de acesso aos dados das cobranças
}
