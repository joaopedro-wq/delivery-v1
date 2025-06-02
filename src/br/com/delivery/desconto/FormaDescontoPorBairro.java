package br.com.delivery.desconto;

import br.com.delivery.model.CupomDescontoEntrega;
import br.com.delivery.model.Pedido;

import java.util.HashMap;
import java.util.Map;

/**
 * Aplica desconto na taxa de entrega com base no bairro do cliente.
 * Valores (fixos):
 *  - "Centro"                -> R$ 2,00
 *  - "Bela Vista"            -> R$ 3,00
 *  - "Cidade Maravilhosa"    -> R$ 1,50
 */
public class FormaDescontoPorBairro implements IFormaDescontoTaxaEntrega {

    private IFormaDescontoTaxaEntrega proximo;
    private static final Map<String, Double> descontosPorBairro = new HashMap<>();

    static {
        descontosPorBairro.put("Centro", 2.0);
        descontosPorBairro.put("Bela Vista", 3.0);
        descontosPorBairro.put("Cidade Maravilhosa", 1.5);
    }

    public FormaDescontoPorBairro(IFormaDescontoTaxaEntrega proximo) {
        this.proximo = proximo;
    }

    @Override
    public double aplicarDesconto(Pedido pedido, double descontoAcumulado) {
        String bairro = pedido.getCliente().getBairro();
        double valorDesconto = descontosPorBairro.getOrDefault(bairro, 0.0);

        if (valorDesconto > 0.0) {
            // Adiciona um cupom chamado "Desconto Bairro"
            pedido.adicionarCupom(new CupomDescontoEntrega("Desconto Bairro", valorDesconto));
        }

        double novoAcumulado = descontoAcumulado + valorDesconto;
        if (proximo != null) {
            return proximo.aplicarDesconto(pedido, novoAcumulado);
        }
        return novoAcumulado;
    }
}
