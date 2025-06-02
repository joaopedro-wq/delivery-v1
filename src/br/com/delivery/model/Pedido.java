package br.com.delivery.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um Pedido no sistema de delivery.
 * Um Pedido possui:
 *  - Cliente que fez o pedido
 *  - Lista de Itens
 *  - Data e hora
 *  - Lista de cupons de desconto de entrega aplicados
 *
 * Também calcula valor total dos itens e taxa de entrega final (considerando cupons).
 */
public class Pedido {
    private Cliente cliente;
    private List<Item> itens;
    private LocalDateTime dataHora;
    private List<CupomDescontoEntrega> cuponsAplicados;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
        this.cuponsAplicados = new ArrayList<>();
    }

    
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    /**
     * Calcula o valor total dos itens do pedido (somatório dos subtotais).
     */
    public double calcularValorTotal() {
        return itens.stream()
                    .mapToDouble(Item::calcularSubtotal)
                    .sum();
    }

    /**
     * Adiciona um cupom de desconto ao pedido (para a taxa de entrega).
     */
    public void adicionarCupom(CupomDescontoEntrega cupom) {
        cuponsAplicados.add(cupom);
    }

    /**
     * Retorna a lista de cupons que foram aplicados (em ordem de aplicação).
     */
    public List<CupomDescontoEntrega> getCuponsAplicados() {
        return cuponsAplicados;
    }

    /**
     * Retorna o cliente associado a este pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Calcula a taxa de entrega final, dado um valor base de taxa.
     * Subtrai do valor base a soma de todos os cupons.
     */
    public double calcularTaxaEntrega(double taxaBase) {
        double somaDescontos = cuponsAplicados.stream()
                .mapToDouble(CupomDescontoEntrega::getValor)
                .sum();
        double resultado = taxaBase - somaDescontos;
        return (resultado < 0) ? 0.0 : resultado;
    }

        /**
     * Retorna a lista de itens que compõem o pedido.
     */
    public List<Item> getItens() {
        return itens;
    }

}
