package com.itau.pagamento.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Cobranca> cobrancas;

}
