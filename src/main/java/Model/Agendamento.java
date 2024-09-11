package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um agendamento em uma barbearia.
 * A classe {@code Agendamento} é responsável por armazenar informações sobre o horário de um agendamento,
 * a lista de clientes associados e os serviços solicitados. Implementa a interface "Serializable" para permitir
 * a persistência em arquivos.
 */
public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String horario;
    private List<Cliente> clientes;
    private List<Servico> servicos;

    /**
     * Construtor da classe "Agendamento".
     * Inicializa um novo agendamento com o horário especificado e cria listas vazias para clientes e serviços.
     */
    public Agendamento(String horario) {
        this.horario = horario;
        this.clientes = new ArrayList<>();
        this.servicos = new ArrayList<>();
    }

    /**
     * Getters e Setters para obter e retornar dados do agendamento.
     */
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    public List<Servico> getServicos() {
        return servicos;
    }
    public void addServico(Servico servico) {
        servicos.add(servico);
    }
}
