# Delivery V1

**Disciplina:** Projeto de Sistema  
**Autor:** João Pedro de Oliveira Bandeira  
**Matrícula:** 2016204225  

---

## 📋 Descrição

Este repositório contém a versão 1 do **sistema de Delivery** desenvolvido para a matéria **Projeto de Sistema**.  
O projeto implementa um modelo de domínio (delivery) e um modelo de projeto em Java, aplicando o padrão **Chain of Responsibility** para cálculo de descontos na taxa de entrega baseada em regras de negócio (bairro do cliente, tipo de cliente, tipo de item e valor do pedido).

---

## 🚀 Funcionalidades Principais

1. **Cadastro de Cliente**  
   - Armazena nome, telefone, bairro (para cálculo de desconto) e categoria (“Ouro”, “Prata” ou “Bronze”).

2. **Cadastro de Item**  
   - Cada item possui nome, tipo (“Alimentação”, “Educação” ou “Lazer”), preço unitário e quantidade.

3. **Criação de Pedido**  
   - Associa um Pedido a um Cliente.
   - Adiciona múltiplos itens ao pedido.
   - Calcula o valor total dos itens.

4. **Regras de Desconto na Taxa de Entrega** (Chain of Responsibility)  
   - **FormaDescontoPorBairro**: aplica desconto fixo conforme bairro do cliente:
     - Centro → R$ 2,00  
     - Bela Vista → R$ 3,00  
     - Cidade Maravilhosa → R$ 1,50  
   - **FormaDescontoPorTipoCliente**: aplica desconto fixo conforme categoria do cliente:
     - Ouro → R$ 3,00  
     - Prata → R$ 2,00  
     - Bronze → R$ 1,00  
   - **FormaDescontoPorTipoItem**: aplica desconto fixo para cada item no pedido, de acordo com seu tipo:
     - Alimentação → R$ 5,00  
     - Educação → R$ 2,00  
     - Lazer → R$ 1,50  
   - **FormaDescontoPorValorPedido**: aplica desconto de R$ 5,00 caso o valor total do pedido exceda R$ 200,00.  
   - **FormaLimiteMaximoDesconto**: ajusta o último cupom se a soma de todos os descontos ultrapassar R$ 10,00, garantindo que o desconto total na taxa de entrega não passe de R$ 10,00.

5. **Cálculo da Taxa de Entrega**  
   - Dado um valor de taxa base (ex.: R$ 15,00), subtrai a soma dos cupons aplicados (limitada a R$ 10,00) e nunca deixa o valor final negativo.

---
