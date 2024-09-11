package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Representa a tela inicial da aplicação da barbearia.
 * A classe {@code TelaInicialView} é a janela principal que exibe a imagem de fundo, uma mensagem de boas-vindas e botões para
 * agendar um horário, visualizar a agenda e cancelar a aplicação.
 */
public class TelaInicialView extends JFrame {
    private JButton btnAdicionar;
    private JButton btnVerAgenda;
    private JButton btnCancelar;

    public TelaInicialView() {
        setTitle("Barbearia");
        setSize(424, 612);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Cria um JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(424, 612));

        // Adiciona o JLayeredPane ao JFrame
        getContentPane().add(layeredPane, BorderLayout.CENTER);

        // Adiciona a imagem de fundo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/barbershop-poster.jpg"));
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, 424, 612);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        // Cria o JTextField e personaliza
        JTextField textField = new JTextField("Bem-vindo a Barbearia.");
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setForeground(Color.BLACK);
        textField.setBounds(25, 325, 350, 30);
        textField.setEditable(false);

        // Adicionando o JTextField ao JLayeredPane
        layeredPane.add(textField, JLayeredPane.PALETTE_LAYER);

        // Cria os botões e define suas fontes e posições
        btnAdicionar = new JButton("Agendar Horario");
        btnAdicionar.setBounds(137, 400, 150, 30);
        layeredPane.add(btnAdicionar, JLayeredPane.PALETTE_LAYER);

        btnVerAgenda = new JButton("Ver Agenda");
        btnVerAgenda.setBounds(137, 450, 150, 30);
        layeredPane.add(btnVerAgenda, JLayeredPane.PALETTE_LAYER);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(137, 500, 150, 30);
        layeredPane.add(btnCancelar, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * Adiciona um {@code ActionListener} aos botões
     * *btnAdicionar - Adicionar agendamento de horário
     * *btnVerAgenda - Abrir tela para ver horários agendados
     * *btnCancelar - Cancela a visualização da tela inicial.
     */
    public void addAdicionarButtonListener(ActionListener listener) {
        btnAdicionar.addActionListener(listener);
    }

    public void addVerAgendaButtonListener(ActionListener listener) {
        btnVerAgenda.addActionListener(listener);
    }

    public void addCancelarButtonListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }
}
