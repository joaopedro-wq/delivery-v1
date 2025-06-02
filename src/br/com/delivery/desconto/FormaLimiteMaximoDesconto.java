package br.com.delivery.desconto;

import br.com.delivery.model.CupomDescontoEntrega;
import br.com.delivery.model.Pedido;

import java.util.List;

/**
 * Garante que a soma total dos descontos aplicados não ultrapasse R$ 10,00.
 * Se o acumulado for maior que 10,00, ajusta O ÚLTIMO cupom adicionado:
 *  - reduz o valor do último cupom em "excesso", modificando seu valor
 *  - acrescenta "(Parcial)" ao nome do cupom
 * Retorna sempre 10,00 (o limite máximo).
 *
 * Deve ser colocado POR ÚLTIMO na cadeia de responsabilidades.
 */
public class FormaLimiteMaximoDesconto implements IFormaDescontoTaxaEntrega {

    private static final double LIMITE_DESCONTO = 10.0;

    @Override
    public double aplicarDesconto(Pedido pedido, double descontoAcumulado) {
        
        if (descontoAcumulado <= LIMITE_DESCONTO) {
            return descontoAcumulado;
        }

        // Se ultrapassou o limite, ajusta o ÚLTIMO cupom de desconto
        List<CupomDescontoEntrega> cupons = pedido.getCuponsAplicados();
        if (!cupons.isEmpty()) {
            // Soma de todos os cupons
            double soma = cupons.stream().mapToDouble(CupomDescontoEntrega::getValor).sum();
            double excesso = soma - LIMITE_DESCONTO;

            // Último cupom da lista
            CupomDescontoEntrega ultimo = cupons.get(cupons.size() - 1);
            double valorOriginal = ultimo.getValor();
            double novoValor = valorOriginal - excesso;
            if (novoValor < 0) {
                novoValor = 0.0;
            }
            // Atualiza o valor e marca como parcial
            ultimo.setValor(novoValor);
            ultimo.setDescricao(ultimo.getDescricao() + " (Parcial)");
        }

        // Retorna exatamente o limite
        return LIMITE_DESCONTO;
    }
}
