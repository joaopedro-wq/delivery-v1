package br.com.delivery.model;

/**
 * Representa um cupom de desconto aplicado à taxa de entrega de um pedido.
 * Cada cupom possui descrição (nome) e valor (quanto vai descontar).
 */
public class CupomDescontoEntrega {
    private String descricao;
    private double valor;

    public CupomDescontoEntrega(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
