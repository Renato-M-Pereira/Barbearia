import Controller.BarbeariaController;
import javax.swing.*;

/**
 * Classe principal para iniciar a aplicação de agendamento de barbearia.
 * A classe {@code Main} contém o método "main", que é o ponto de entrada da aplicação. Ela configura a aplicação para ser executada na
 * thread de eventos do Swing, garantindo que a interface gráfica do usuário seja manipulada de forma segura e eficiente.
 */
public class Main {
    /**
     * Método principal que inicia a aplicação.
     * Este método é o ponto de entrada da aplicação. Ele utiliza {@code SwingUtilities.invokeLater} para garantir que a criação e manipulação da
     * interface gráfica sejam realizadas na thread de eventos do Swing. Dentro deste método, um {@code BarbeariaController} é criado e iniciado.
     */
    public static void main(String[] args) {
        // Executa a aplicação na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            BarbeariaController controller = new BarbeariaController();
            controller.iniciar();
        });
    }
}

