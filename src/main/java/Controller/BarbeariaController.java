package Controller;

import Model.Agendamento;
import Model.Barbearia;
import Model.Cliente;
import Model.Servico;
import View.VerAgendaView;
import View.AgendarHorarioView;
import View.TelaInicialView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controlador da aplicação de barbearia.
 *
 * A classe "BarbeariaController" gerencia a interação entre as views (interface gráfica) e o modelo de dados da barbearia.
 * Ela é responsável por carregar e salvar os dados da barbearia, e por adicionar, remover e visualizar agendamentos.
 */
public class BarbeariaController {
    private Barbearia barbearia;
    private TelaInicialView telaInicialView;
    private AgendarHorarioView agendarHorarioView;
    private VerAgendaView verAgendaView;

    /**
     * Construtor da classe BarbeariaController.
     * Inicializa as views e configura os listeners para os botões das views.
     */
    public BarbeariaController() {
        barbearia = carregarBarbearia();
        telaInicialView = new TelaInicialView();

        Set<String> horariosAgendados = new HashSet<>();
        for (Agendamento agendamento : barbearia.getAgendamentos()) {
            horariosAgendados.add(agendamento.getHorario());
        }

        agendarHorarioView = new AgendarHorarioView(
                new String[]{" - - Escolha o horario  ", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"},
                new Servico[]{new Servico("Corte de cabelo", 40), new Servico("Barba", 20), new Servico("Corte de cabelo + Barba", 50)},
                horariosAgendados
        );
        verAgendaView = new VerAgendaView();
        configurarListeners();
    }

    private void configurarListeners() {
        telaInicialView.addAdicionarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agendarHorarioView.setVisible(true);
            }
        });

        telaInicialView.addVerAgendaButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAgendaView();
                verAgendaView.setVisible(true);
            }
        });

        telaInicialView.addCancelarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        agendarHorarioView.addOkButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAgendamento();
            }
        });

        agendarHorarioView.addCancelarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agendarHorarioView.setVisible(false);
            }
        });

        verAgendaView.addRemoverButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerAgendamento();
            }
        });

        verAgendaView.addCancelarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verAgendaView.setVisible(false);
            }
        });
    }

    /**
     * Método que adiciona um novo agendamento à barbearia.
     * Obtém os dados do cliente e do serviço a partir de "AgendarHorarioView" e salva o agendamento na lista de agendamentos da barbearia.
     */
    private void adicionarAgendamento() {
        String nome = agendarHorarioView.getNome();
        String sobrenome = agendarHorarioView.getSobrenome();
        String telefone = agendarHorarioView.getTelefone();
        String horario = agendarHorarioView.getHorario();
        Servico servico = agendarHorarioView.getServico();

        Cliente cliente = new Cliente(nome, sobrenome, telefone);
        Agendamento agendamento = new Agendamento(horario);
        agendamento.addCliente(cliente);
        agendamento.addServico(servico);
        barbearia.getAgendamentos().add(agendamento);

        agendarHorarioView.limparCampos();
        agendarHorarioView.setVisible(false);
        salvarBarbearia();
    }

    /**
     * Atualiza a visualização da agenda na view "VerAgendaView".
     * Atualiza a lista de agendamentos exibida na view com os agendamentos atuais da barbearia.
     */
    private void atualizarAgendaView() {
        verAgendaView.setAgendamentos(barbearia.getAgendamentos());
    }

    /**
     * Remove um agendamento selecionado da lista de agendamentos e salva as alterações.
     */
    private void removerAgendamento() {
        int rowIndex = verAgendaView.removeSelectedRow();
        if (rowIndex != -1) {
            List<Agendamento> agendamentos = barbearia.getAgendamentos();
            //Remove uma linha de agendamento
            barbearia.getAgendamentos().remove(rowIndex);
            //Salva a alteração
            salvarBarbearia();
        }
    }
    /**
     * Carrega a barbearia a partir de um arquivo.
     * Tenta ler a barbearia de um arquivo binário. Se o arquivo não existir ou ocorrer um erro, cria uma nova instância de "Barbearia".
     */

    private Barbearia carregarBarbearia() {
        Barbearia barbearia = null;
        File file = new File("dados.bin");

        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                barbearia = (Barbearia) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar a barbearia: " + e.getMessage());
            }
        }

        if (barbearia == null) {
            barbearia = new Barbearia();
            salvarBarbearia();
        }
        return barbearia;
    }
    /**
     * Salva o estado atual da barbearia em um arquivo.
     * Grava a instância atual de "Barbearia" em um arquivo binário.
     */
    private void salvarBarbearia() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("dados.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(barbearia);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a barbearia: " + e.getMessage());
        }
    }

    /**
     * Inicia a aplicação, exibindo a tela inicial.
     */
    public void iniciar() {
        telaInicialView.setVisible(true);
    }
}
