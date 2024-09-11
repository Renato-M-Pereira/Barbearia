package Model;

import java.io.Serializable;

/**
 * Representa um serviço oferecido pela barbearia.
 * A classe {@code Servico} armazena informações sobre um serviço, incluindo seu nome e preço.
 * Implementa a interface "Serializable" para permitir a persistência em arquivos.
 */
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private double preco;

    /**
     * Construtor da classe "Servico".
     * Inicializa um novo serviço com o nome e preço fornecidos.
     */
    public Servico(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Getters e Setters para obter e retornar dados do Serviço.
     */
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + preco + ")";
    }
}
