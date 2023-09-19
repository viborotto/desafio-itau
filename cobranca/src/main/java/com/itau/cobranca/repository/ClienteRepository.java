package com.itau.cobranca.repository;

import com.itau.cobranca.model.ClienteCobranca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteCobranca, Long> {
    // Você pode adicionar métodos personalizados de consulta, se necessário
}

