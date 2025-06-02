package br.com.delivery.service;

import br.com.delivery.desconto.FormaDescontoPorBairro;
import br.com.delivery.desconto.FormaDescontoPorTipoCliente;
import br.com.delivery.desconto.FormaDescontoPorTipoItem;
import br.com.delivery.desconto.FormaDescontoPorValorPedido;
import br.com.delivery.desconto.FormaLimiteMaximoDesconto;
import br.com.delivery.desconto.IFormaDescontoTaxaEntrega;
import br.com.delivery.model.Pedido;

/**
 * Serviço que cria a cadeia de responsabilidade para aplicar todos os descontos
 * na taxa de entrega de um Pedido, seguindo esta ordem:
 *   1) Desconto por Bairro
 *   2) Desconto por Tipo de Cliente
 *   3) Desconto por Tipo de Item
 *   4) Desconto por Valor do Pedido (> 200)
 *   5) Limitar ao máximo de R$ 10,00
 */
public class CalculadoraDeDescontoService {

  
    public void calcularDesconto(Pedido pedido) {
       
        IFormaDescontoTaxaEntrega etapaLimite = new FormaLimiteMaximoDesconto();
        IFormaDescontoTaxaEntrega etapaValorPedido = new FormaDescontoPorValorPedido(etapaLimite);
        IFormaDescontoTaxaEntrega etapaTipoItem = new FormaDescontoPorTipoItem(etapaValorPedido);
        IFormaDescontoTaxaEntrega etapaTipoCliente = new FormaDescontoPorTipoCliente(etapaTipoItem);
        IFormaDescontoTaxaEntrega etapaBairro = new FormaDescontoPorBairro(etapaTipoCliente);

        // Inicia a aplicação a partir de "Bairro", com descontoAcumulado = 0
        etapaBairro.aplicarDesconto(pedido, 0.0);
    }
}
