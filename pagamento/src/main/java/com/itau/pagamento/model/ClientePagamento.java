package com.itau.pagamento.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos relevantes para o pagamento do Cliente
    private String nome;
    private String cpf;

    private Double saldo;

    @OneToMany(mappedBy = "cliente")
    private List<Cobranca> cobrancas;



}
