package View;

import Model.Agendamento;
import Model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Representa a visualização da tela de agendamentos.
 * A classe {@code VerAgendaView} é uma janela que exibe os agendamentos em uma tabela. Permite ao usuário remover um agendamento
 * selecionado e cancelar a visualização da agenda.
 */
public class VerAgendaView extends JFrame {
    private JTable tabelaAgendamentos;
    private JButton btnRemover;
    private JButton btnCancelar;
    private DefaultTableModel modeloTabela;

    /**
     * Construtor da classe {@code VerAgendaView}.
     * Inicializa a interface gráfica com uma tabela para exibir os agendamentos e botões para remover e cancelar.
     */
    public VerAgendaView() {
        setTitle("Agenda do dia");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        modeloTabela = new DefaultTableModel(new Object[]{"Horario", "Nome", "Sobrenome", "Telefone", "Servico"}, 0);
        tabelaAgendamentos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaAgendamentos);
        add(scrollPane, BorderLayout.CENTER);

        // Criando o botão "Cancelar"
        btnRemover = new JButton("Remover");
        btnCancelar = new JButton("Cancelar");

        // Painel auxiliar para posicionar o botão na parte inferior direita
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnRemover);
        panel.add(btnCancelar);

        // Adicionando o painel ao final do layout
        add(panel, BorderLayout.SOUTH);
    }

    /**
     * Atualiza a tabela com os agendamentos fornecidos.
     * Os agendamentos são ordenados por horário antes de serem exibidos na tabela.
     */
    public void setAgendamentos(List<Agendamento> agendamentos) {
        // Ordenar agendamentos por horário
        Collections.sort(agendamentos, new Comparator<Agendamento>() {
            @Override
            public int compare(Agendamento a1, Agendamento a2) {
                return a1.getHorario().compareTo(a2.getHorario());
            }
        });

        modeloTabela.setRowCount(0);
        for (Agendamento agendamento : agendamentos) {
            for (Cliente cliente : agendamento.getClientes()) {
                modeloTabela.addRow(new Object[]{
                        agendamento.getHorario(),
                        cliente.getNome(),
                        cliente.getSobrenome(),
                        cliente.getTelefone(),
                        agendamento.getServicos().isEmpty() ? "N/A" : agendamento.getServicos().get(0)
                });
            }
        }
    }

    public void addRemoverButtonListener(ActionListener listener) {
        btnRemover.addActionListener(listener);
    }
    public void addCancelarButtonListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    /**
     * Remove a linha selecionada na tabela.
     * Se nenhuma linha estiver selecionada, exibe uma mensagem de erro e retorna -1.
     */
    public int removeSelectedRow() {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = tabelaAgendamentos.convertRowIndexToModel(selectedRow);
            modeloTabela.removeRow(modelRow);
            return modelRow;
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma linha selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
}
