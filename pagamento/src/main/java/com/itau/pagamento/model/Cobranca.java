package com.itau.pagamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cobranca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private Date dataVencimento;

    private double valorPago; // Valor que foi efetivamente pago
    private Date dataPagamento; // Data em que o pagamento foi efetuado

    @OneToOne(mappedBy = "cobranca")
    private Boleto boleto;

    @ManyToOne // Muitas cobranças podem pertencer a um cliente
    @JoinColumn(name = "cliente_id") // Nome da coluna que representa o relacionamento
    @JsonIgnore
    private ClientePagamento cliente;

    public ClientePagamento getCliente() {
        return cliente;
    }

    public void setCliente(ClientePagamento cliente) {
        this.cliente = cliente;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
