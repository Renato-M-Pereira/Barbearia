package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma barbearia que gerencia clientes, serviços e agendamentos.
 * A classe {@code Barbearia} fornece a estrutura para armazenar e manipular informações sobre clientes,
 * serviços disponíveis e agendamentos realizados na barbearia. Implementa a interface "Serializable"
 * para permitir a persistência em arquivos.
 */
public class Barbearia implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();
    private List<Agendamento> agendamentos = new ArrayList<>();

    /**
     * Getters e Setters para obter e retornar dados relativos a Barbearia.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    public List<Servico> getServicos() {
        return servicos;
    }
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}