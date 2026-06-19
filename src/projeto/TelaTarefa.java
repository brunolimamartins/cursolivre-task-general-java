package projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class TelaTarefa extends JFrame {
	private ArrayList <Tarefa> tarefas = new ArrayList<>();
	
	private JTextField campoTitulo;
	
	private JTextArea campoDescricao;
	
	private DefaultListModel<Tarefa> modelolista;
	
	private JList <Tarefa> ListaTarefas;
	
	public TelaTarefa() {
		
		setTitle("Gerenciador de Tarefas");
		
		setSize(850, 550);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		criarInterface();
		
		setVisible(true);
		
		
		
	}
	
	private void criarInterface() {
		
		JPanel painelPrincipal = new JPanel (new BorderLayout(20, 20));
		
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		
		painelPrincipal.setBackground(new Color (236,240,243));
		
		JLabel titulo = new JLabel("Gerenciador de tarefas");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
		titulo.setForeground(new Color(44,62,80));
		
		JLabel subtitulo = new JLabel("Organize suas tarefas de forma simples e pratica");
		
		subtitulo.setFont(new Font ("Segoe UI", Font.PLAIN, 15));
		
		JPanel painelTopo = new JPanel(new GridLayout(2,1));
		painelTopo.setBackground(new Color(236,240,243));
		painelTopo.add(titulo);
		painelTopo.add(subtitulo);
		
		painelPrincipal.add(painelTopo, BorderLayout.NORTH);
		
		JPanel cardFormulario = criarCard();
		cardFormulario.setLayout(new BorderLayout(10,10));
		
		//Paramos aqui
	}
private JPanel criarCard() {
	return;
	
}
}
