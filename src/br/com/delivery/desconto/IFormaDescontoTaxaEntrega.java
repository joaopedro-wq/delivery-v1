package br.com.delivery.desconto;

import br.com.delivery.model.Pedido;

/**
 * Interface para cada forma de cálculo de desconto na taxa de entrega.
 * Cada implementação deverá analisar o Pedido e o descontoAcumulado até aquele ponto,
 * possivelmente criar um CupomDescontoEntrega e retornar o novo valor acumulado.
 */
public interface IFormaDescontoTaxaEntrega {
    /**
     * @param pedido            - pedido em que os cupons serão adicionados
     * @param descontoAcumulado - soma dos descontos já aplicados antes desta regra
     * @return novo total de desconto após esta regra (pode ou não adicionar novo cupom)
     */
    double aplicarDesconto(Pedido pedido, double descontoAcumulado);
}
