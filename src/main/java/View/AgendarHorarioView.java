package View;

import Model.Servico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Set;

/**
 * Representa a interface gráfica para agendar um horário na barbearia.
 * A classe {@code AgendarHorarioView} é uma janela que permite ao usuário inserir informações sobre
 * um novo agendamento, incluindo nome, sobrenome, telefone, horário e serviço desejado.
 */

public class AgendarHorarioView extends JFrame {
    private JTextField textNome;
    private JTextField textSobrenome;
    private JTextField textTelefone;
    private JComboBox<String> jComboHorario;
    private JComboBox<Servico> jComboServico;
    private JButton btnOk;
    private JButton btnCancelar;

    /**
     * Construtor da classe {@code AgendarHorarioView}.
     * Inicializa a interface gráfica com os campos para inserir informações do cliente, selecionar horário e serviço.
     * Também configura o layout e os botões da janela.
     */
    public AgendarHorarioView(String[] horarios, Servico[] servicos, Set<String> horariosAgendados) {
        setTitle("Agendar Horario");
        setSize(520, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        textNome = new JTextField();
        textSobrenome = new JTextField();
        textTelefone = new JTextField();
        jComboHorario = new JComboBox<>(horarios);
        jComboServico = new JComboBox<>(servicos);
        btnOk = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        c.ipadx = 100; c.ipady = 10;
        c.gridx = 0; c.gridy = 0; add(new JLabel("Nome"), c);
        c.gridx = 1; add(textNome, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Sobrenome"), c);
        c.gridx = 1; add(textSobrenome, c);

        c.gridx = 0; c.gridy = 2; add(new JLabel("Telefone"), c);
        c.gridx = 1; add(textTelefone, c);

        c.gridx = 0; c.gridy = 3; add(new JLabel("Horario"), c);
        c.gridx = 1; add(jComboHorario, c);

        c.gridx = 0; c.gridy = 4; add(new JLabel("Servico"), c);
        c.gridx = 1; add(jComboServico, c);

        // Painel para os botões btnOk e btnCancelar
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(btnOk);
        panelBotoes.add(btnCancelar);

        // Adiciona o painel ao layout principal
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        add(panelBotoes, c);

        // Define o tamanho dos botões
        Dimension buttonSize = new Dimension(100, 30);
        btnOk.setPreferredSize(buttonSize);
        btnCancelar.setPreferredSize(buttonSize);

        // Adiciona o Listener para validar o telefone quando o campo perde o foco
        textTelefone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validarTelefone();
            }
        });
        atualizarHorarios(horariosAgendados);
    }

    /**
     * Atualiza a lista de horários disponíveis, removendo os horários que já foram agendados.
     */
    private void atualizarHorarios(Set<String> horariosAgendados) {
        // Remove os horários já agendados
        for (String horario : horariosAgendados) {
            if (horario != null) {
                jComboHorario.removeItem(horario);
            }
        }
    }

    /**
     * Limpa todos os campos da interface gráfica.
     * Define os campos de texto e combo boxes para seus valores padrão.
     */
    public void limparCampos() {
        textNome.setText("");
        textSobrenome.setText("");
        textTelefone.setText("");
        jComboHorario.setSelectedIndex(0);
        jComboServico.setSelectedIndex(0);
    }

    /**
     * Valida o telefone inserido, garantindo que contenha apenas números.
     * Caso o telefone contenha caracteres não numéricos, exibe uma mensagem de erro e limpa o campo.
     */
    private void validarTelefone() {
        String telefone = textTelefone.getText();
        if (!telefone.matches("\\d*")) {
            JOptionPane.showMessageDialog(this, "O telefone deve conter apenas numeros.",
                    "Erro ao Validar", JOptionPane.ERROR_MESSAGE);
            textTelefone.setText(""); // Limpa o campo
        }
    }

    private void mostrarMensagemAgendamento() {
        JOptionPane.showMessageDialog(this, "Horario Agendado com Sucesso!",
                "Confirmar Agendamento", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getNome() {
        return textNome.getText();
    }
    public String getSobrenome() {
        return textSobrenome.getText();
    }
    public String getTelefone() {
        String telefone = textTelefone.getText();
        return telefone;
    }
    public String getHorario() {
        return (String) jComboHorario.getSelectedItem();
    }
    public Servico getServico() {
        return (Servico) jComboServico.getSelectedItem();
    }

    /**
     * Adiciona um {@code ActionListener} ao botão de confirmação.
     * Verifica se todos os campos foram preenchidos antes de executar o listener.
     * Mostra uma mensagem de sucesso após a execução do listener.
     */
    public void addOkButtonListener(ActionListener listener) {
        btnOk.addActionListener(e -> {
            // Verifiqa a validade dos campos antes de executar o listener
            if (getNome().isEmpty() || getSobrenome().isEmpty() || getTelefone().isEmpty() || getHorario() == null || getServico() == null) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            listener.actionPerformed(e);
            // Após a execução do listener, mostra a mensagem de sucesso
            mostrarMensagemAgendamento();
        });
    }

    public void addCancelarButtonListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }
}
