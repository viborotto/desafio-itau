package com.itau.pagamento.model;

public class PagamentoRequest {
    private Double valor;

    public PagamentoRequest() {
    }

    public PagamentoRequest(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
