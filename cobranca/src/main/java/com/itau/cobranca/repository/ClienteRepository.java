package com.itau.cobranca.repository;

import com.itau.cobranca.model.ClienteCobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteCobranca, Long> {
    // Você pode adicionar métodos personalizados de consulta, se necessário
    Optional<ClienteCobranca> findByCpf(String cpf);
}

