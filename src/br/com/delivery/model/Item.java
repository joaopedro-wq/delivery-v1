package br.com.delivery.model;

/**
 * Representa um item que pode ser adicionado a um pedido.
 */
public class Item {
    private String nome;
    private String tipo;     
    private double preco;
    private int quantidade;

    public Item(String nome, String tipo, double preco, int quantidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    /**
     * Retorna o preço total deste item (preço unitário * quantidade)
     */
    public double calcularSubtotal() {
        return preco * quantidade;
    }

   
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
