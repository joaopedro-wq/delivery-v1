package br.com.delivery.desconto;

import br.com.delivery.model.CupomDescontoEntrega;
import br.com.delivery.model.Pedido;

/**
 * Aplica um desconto fixo de R$ 5,00 quando o valor total do pedido excede R$ 200,00,
 * mas somente se a soma dos descontos anteriores ainda não ultrapassou o limite de R$ 10,00.
 *
 * OBS.: A lógica de “não ultrapassar R$ 10,00” será efetivamente garantida pela última etapa do chain,
 * que é a FormaLimiteMaximoDesconto, mas aqui respeitamos a condição de “se valor do pedido > 200”.
 */
public class FormaDescontoPorValorPedido implements IFormaDescontoTaxaEntrega {

    private IFormaDescontoTaxaEntrega proximo;
    private static final double LIMITE_VALOR_PEDIDO = 200.0;
    private static final double DESCONTO_VALOR_PEDIDO = 5.0;

    public FormaDescontoPorValorPedido(IFormaDescontoTaxaEntrega proximo) {
        this.proximo = proximo;
    }

    @Override
    public double aplicarDesconto(Pedido pedido, double descontoAcumulado) {
        double valorTotalPedido = pedido.calcularValorTotal();
        double descontoAplicado = 0.0;

        if (valorTotalPedido > LIMITE_VALOR_PEDIDO) {
            descontoAplicado = DESCONTO_VALOR_PEDIDO;
            pedido.adicionarCupom(new CupomDescontoEntrega("Desconto Valor Pedido (>200)", descontoAplicado));
        }

        double novoAcumulado = descontoAcumulado + descontoAplicado;
        if (proximo != null) {
            return proximo.aplicarDesconto(pedido, novoAcumulado);
        }
        return novoAcumulado;
    }
}
