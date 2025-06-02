package br.com.delivery.model;

/**
 * Representa um cliente no sistema de delivery.
 */
public class Cliente {
    private String nome;
    private String telefone;
    private String bairro;
    private String tipoCliente; 

    public Cliente(String nome, String telefone, String bairro, String tipoCliente) {
        this.nome = nome;
        this.telefone = telefone;
        this.bairro = bairro;
        this.tipoCliente = tipoCliente;
    }

    /**
     * Retorna uma representação em String do cliente.
     *
     * @return String formatada com os dados do cliente.
     */
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }
}
