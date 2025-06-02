package br.com.delivery.desconto;

import br.com.delivery.model.CupomDescontoEntrega;
import br.com.delivery.model.Item;
import br.com.delivery.model.Pedido;

import java.util.HashMap;
import java.util.Map;

/**
 * Aplica desconto na taxa de entrega com base no(s) tipo(s) de item(s) do pedido.
 * Para cada item:
 *  - "Alimentação" -> R$ 5,00
 *  - "Educação"    -> R$ 2,00
 *  - "Lazer"       -> R$ 1,50
 *
 * O desconto total desta etapa é a soma dos descontos de cada item (independente da quantidade).
 */
public class FormaDescontoPorTipoItem implements IFormaDescontoTaxaEntrega {

    private IFormaDescontoTaxaEntrega proximo;
    private static final Map<String, Double> descontosPorTipoItem = new HashMap<>();

    static {
        descontosPorTipoItem.put("Alimentação", 5.0);
        descontosPorTipoItem.put("Educação", 2.0);
        descontosPorTipoItem.put("Lazer", 1.5);
    }

    public FormaDescontoPorTipoItem(IFormaDescontoTaxaEntrega proximo) {
        this.proximo = proximo;
    }

    @Override
    public double aplicarDesconto(Pedido pedido, double descontoAcumulado) {
        double totalDescontoItens = 0.0;

        // Itera sobre cada Item do pedido e acumula o desconto conforme o tipo
        for (Item item : pedido.getItens()) {
            double descontoItem = descontosPorTipoItem.getOrDefault(item.getTipo(), 0.0);
            if (descontoItem > 0.0) {
                totalDescontoItens += descontoItem;
            }
        }

        if (totalDescontoItens > 0.0) {
            pedido.adicionarCupom(new CupomDescontoEntrega("Desconto Tipo Item", totalDescontoItens));
        }

        double novoAcumulado = descontoAcumulado + totalDescontoItens;
        if (proximo != null) {
            return proximo.aplicarDesconto(pedido, novoAcumulado);
        }
        return novoAcumulado;
    }
}
