package br.com.delivery.app;

import br.com.delivery.model.Cliente;
import br.com.delivery.model.Item;
import br.com.delivery.model.Pedido;
import br.com.delivery.service.CalculadoraDeDescontoService;

/**
 * Classe principal que executa o sistema de delivery.
 * 
 * 1) Cria um cliente
 * 2) Cria um pedido com itens
 * 3) Calcula os descontos na taxa de entrega
 * 4) Imprime o resumo do pedido e cupons aplicados
 */
public class Main {
    public static void main(String[] args) {
     
        Cliente cliente = new Cliente("Ana", "99999-9999", "Centro", "Ouro");

      
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Item("Pizza", "Alimentação", 50.0, 3));
        pedido.adicionarItem(new Item("Livro", "Educação", 100.0, 1));

        
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();
        calculadora.calcularDesconto(pedido);

       
        double taxaEntregaBase = 15.0;
        double taxaEntregaFinal = pedido.calcularTaxaEntrega(taxaEntregaBase);

       
        System.out.println("=== RESUMO DO PEDIDO ===");
        System.out.printf("Valor total dos itens: R$ %.2f%n", pedido.calcularValorTotal());
        System.out.printf("Taxa de entrega (base): R$ %.2f%n", taxaEntregaBase);
        System.out.printf("Taxa de entrega (final): R$ %.2f%n", taxaEntregaFinal);

        System.out.println("\n=== CUPONS APLICADOS ===");
        pedido.getCuponsAplicados().forEach(cupom -> {
            System.out.printf("%s - R$ %.2f%n", cupom.getDescricao(), cupom.getValor());
        });
    }
}
