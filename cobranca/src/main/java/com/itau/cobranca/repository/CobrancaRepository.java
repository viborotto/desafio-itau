package com.itau.cobranca.repository;

import com.itau.cobranca.model.Cobranca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
    Optional<Cobranca> findByIdAndClienteCpf(Long id, String cpf);

    void deleteByClienteId(Long clienteId);
}
