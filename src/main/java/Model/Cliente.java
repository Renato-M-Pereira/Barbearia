package Model;

import java.io.Serializable;

/**
 * Representa um cliente da barbearia.
 * A classe {@code Cliente} armazena informações sobre um cliente, incluindo nome, sobrenome e telefone.
 * Implementa a interface "Serializable" para permitir a persistência em arquivos.
 */
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String sobrenome;
    private String telefone;

    /**
     * Construtor da classe "Cliente".
     * Inicializa um novo cliente com o nome, sobrenome e telefone fornecidos.
     */
    public Cliente(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }

    /**
     * Getters e Setters para obter e retornar dados do Cliente.
     */
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
