package com.itau.pagamento.model;

import java.time.LocalDateTime;

public class PagamentoResponse {
    private Long cobrancaId;
    private Double valorPago;
    private LocalDateTime dataPagamento;

    public PagamentoResponse() {
    }

    public PagamentoResponse(Long cobrancaId, Double valorPago, LocalDateTime dataPagamento) {
        this.cobrancaId = cobrancaId;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    public Long getCobrancaId() {
        return cobrancaId;
    }

    public void setCobrancaId(Long cobrancaId) {
        this.cobrancaId = cobrancaId;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

