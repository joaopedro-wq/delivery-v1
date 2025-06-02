package br.com.delivery.desconto;

import br.com.delivery.model.CupomDescontoEntrega;
import br.com.delivery.model.Pedido;

import java.util.HashMap;
import java.util.Map;

/**
 * Aplica desconto na taxa de entrega com base no tipo de cliente.
 * Valores (fixos):
 *  - "Ouro"   -> R$ 3,00
 *  - "Prata"  -> R$ 2,00
 *  - "Bronze" -> R$ 1,00
 */
public class FormaDescontoPorTipoCliente implements IFormaDescontoTaxaEntrega {

    private IFormaDescontoTaxaEntrega proximo;
    private static final Map<String, Double> descontosPorTipo = new HashMap<>();

    static {
        descontosPorTipo.put("Ouro", 3.0);
        descontosPorTipo.put("Prata", 2.0);
        descontosPorTipo.put("Bronze", 1.0);
    }

    public FormaDescontoPorTipoCliente(IFormaDescontoTaxaEntrega proximo) {
        this.proximo = proximo;
    }

    @Override
    public double aplicarDesconto(Pedido pedido, double descontoAcumulado) {
        String tipoCliente = pedido.getCliente().getTipoCliente();
        double valorDesconto = descontosPorTipo.getOrDefault(tipoCliente, 0.0);

        if (valorDesconto > 0.0) {
            pedido.adicionarCupom(new CupomDescontoEntrega("Desconto Tipo Cliente", valorDesconto));
        }

        double novoAcumulado = descontoAcumulado + valorDesconto;
        if (proximo != null) {
            return proximo.aplicarDesconto(pedido, novoAcumulado);
        }
        return novoAcumulado;
    }
}
