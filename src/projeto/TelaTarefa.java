package projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class TelaTarefa extends JFrame {
	private ArrayList <Tarefa> tarefas = new ArrayList<>();
	
	private JTextField campoTitulo;
	
	private JTextArea campoDescricao;
	
	private DefaultListModel<Tarefa> modeloLista;
	
	private JList <Tarefa> listaTarefas;
	
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
		
		JLabel tituloFormulario = new JLabel ("Nova tarefa");
		tituloFormulario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tituloFormulario.setForeground(new Color(44,62,60));
		
		//Painel qie agrupa os campos do formulario
		
		JPanel campos = new JPanel(new GridLayout(4, 1, 8, 8));
		campos.setBackground(Color.WHITE);
		
		//Label do campo de titulo
		JLabel labelTitulo = new JLabel("Titulo");
		labelTitulo.setFont((new Font("Segoe UI", Font.BOLD,14)));
		
		campoTitulo = new JTextField();
		campoTitulo.setFont(new Font ("Segoe UI", Font.PLAIN, 14));
		
		campoTitulo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color (203,213,225)),BorderFactory.createEmptyBorder(8, 10, 8, 10)));
		
		//Label do campo de descrição
		JLabel labelDescricao = new JLabel("Descrição");
		labelDescricao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		campoDescricao = new JTextArea();
		campoDescricao.setFont((new Font("Segoe UI", Font.PLAIN, 14)));
		campoDescricao.setLineWrap(true);
		campoDescricao.setWrapStyleWord(true);
		campoDescricao.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
		
		JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
		scrollDescricao.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
		
		//Adiciona os labels
		campos.add(labelTitulo);
		campos.add(campoTitulo);
		campos.add(labelDescricao);
		campos.add(campoDescricao);
		campos.add(scrollDescricao);
		
		//Botão para adicionar uma nova tarefa
		JButton botaoAdicionar = criarBotao("Adicionar tarefa", new Color(37, 99, 235));
		
		//Adicionar titulo, campos e botão ao card de formulario
		cardFormulario.add(tituloFormulario, BorderLayout.NORTH);
		cardFormulario.add(campos,BorderLayout.CENTER);
		cardFormulario.add(botaoAdicionar,BorderLayout.SOUTH);
		
		//Adiciona o card do formulario a esquerda da tela
		
		painelPrincipal.add(cardFormulario,BorderLayout.WEST);
		
		//Criar o card na lista de tarefas
		JPanel cardLista = criarCard();
		cardLista.setLayout(new BorderLayout(10, 10));
		
		//Titulo da área de tarefas cadastradas 
		JLabel tituloLista = new JLabel("Tarefas cadastradas");
		tituloLista.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		tituloLista.setForeground(new Color(44,62,80));
		
		//Modelo que guarda os dados que sera exibido na JList
		
		modeloLista = new DefaultListModel<>();
		
		listaTarefas = new JList<>(modeloLista);
		
		listaTarefas.setCellRenderer(new TarefaCardRenderer());
		
		listaTarefas.setFixedCellHeight(75);
		
		listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listaTarefas.setBackground(Color.WHITE);
		
		JScrollPane scrollLista = new JScrollPane(listaTarefas);
		
		scrollLista.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
		
		//Painel inferior, onde ficam os botoes de ação na lista de tarefas
		JPanel painelBotoes = new JPanel (new FlowLayout(FlowLayout.RIGHT));
		painelBotoes.setBackground(Color.WHITE);
		
		
		//Botoes para concluir e remover uma tarefa cadastrada
		JButton botaoConcluir = criarBotao("Concluir", new Color(22,163,74));
		JButton botaoRemover = criarBotao("Remover", new Color(220,38,38));
		
		//Adicionar os botoes ao painel inferior
		painelBotoes.add(botaoConcluir);
		painelBotoes.add(botaoRemover);
		
		//Adicionar titulo, lista e botões ao da lista
		cardLista.add(tituloLista,BorderLayout.NORTH);
		cardLista.add(scrollLista, BorderLayout.CENTER);
		cardLista.add(painelBotoes,BorderLayout.SOUTH);
		
		//Adicionar o card da lista ao centro da tela
		painelPrincipal.add(cardLista,BorderLayout.CENTER);
		
		//Adicionar o painel principal à nossa janela
		add(painelPrincipal);
		
		//Eventos para os botoes de adicionar, concluir e remover uma tarefa
		botaoAdicionar.addActionListener(e -> adicionarTarefa());
		botaoConcluir.addActionListener(e -> concluirTarefa());
		botaoRemover.addActionListener(e -> removerTarefa());
		
		
		
	}
	
	private JPanel criarCard() {
		JPanel painel = new JPanel();
		painel.setBackground(Color.WHITE);
		painel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(226,232,240)),BorderFactory.createEmptyBorder(20,20,20,20)));
		
		painel.setPreferredSize(new Dimension(330,400));
		return painel;
		
	}
	//Metodo responsavel por criar os botoes padronizados
	private JButton criarBotao(String texto, Color cor) {
		//Criar o botão com base no texto recebido na criação da nossa interface
		JButton botao = new JButton(texto);
		botao.setFont(new Font("Segoe UI", Font.BOLD,14));
		botao.setBackground(cor);//Cor de fundo
		botao.setForeground(Color.WHITE);//Cor do texto
		botao.setFocusPainted(false);//Remove a borda de foco
		//Altera o cursor ao passar o mouse sobre o botão
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//Define espaçamento interno do botão(tambem chamamos de padding)
		botao.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
		return botao;
	}
	//Metodo responsavel por adicionar uma nova tarefa
	private void adicionarTarefa() {
		//Captura o titulo e descrição digitado pelo usuário
		String titulo = campoTitulo.getText().trim();
		String descricao = campoDescricao.getText().trim();
		//Verifica se os campos estão vazios
		if (titulo.isEmpty() || descricao.isEmpty()) {
			JOptionPane.showMessageDialog(this,"Preencha todos os campos.");
			return;
			
		}
		//Criar um novo objeto tarefa
		Tarefa tarefa = new Tarefa(titulo, descricao);
		tarefas.add(tarefa); //adiciona a tarefa a lista interna
		modeloLista.addElement(tarefa);// adiciona a tarefa
		//Limpa os campos de titulo e descrição
		campoTitulo.setText("");
		campoDescricao.setText("");
		
		
	}
	private void concluirTarefa() {
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		
		if(tarefaSelecionada == null) {
			JOptionPane.showMessageDialog(this,"Selecione uma tarefa");
			return;
		}
		tarefaSelecionada.concluir();
		listaTarefas.repaint();//Atualiza vizualmente a lista
		
	}
	private void removerTarefa() {
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		if(tarefaSelecionada == null) {
			JOptionPane.showMessageDialog(this, "Selecione uma tarefa");
			return;
		}
		tarefas.remove(tarefaSelecionada); //Remove da lista interna
		modeloLista.removeElement(tarefaSelecionada);//Remove da lista visual
	}
	//Classe interna para fazer a renderização do card da tarefa
	private class TarefaCardRenderer extends JPanel implements ListCellRenderer<Tarefa>{
		private JLabel labelTitulo;// exibe o titulo
		private JLabel labelDescricao;// exibe a descrição
		private JLabel labelStatus;//exibe o status
		//Construtor do renderer
		public TarefaCardRenderer() {
			setLayout(new BorderLayout(8,5));//Define o Layout do card
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,8,0,Color.WHITE),BorderFactory.createEmptyBorder(10,12,10,12)));
			//Define a borda e o espaçamento
			//Cria e estiliza a label do titulo
			labelTitulo = new JLabel();
			labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
			//Cria e estiliza a label de descrição
			labelDescricao = new JLabel();
			labelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			labelDescricao.setForeground(new Color(100,116,139));
			
			labelStatus = new JLabel(); //Estiliza o status
			labelStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
			
			JPanel painelTexto = new JPanel(new GridLayout(2,1));
			
			painelTexto.setOpaque(false);//deixa o painel transparente
			painelTexto.add(labelTitulo);//adiona titulo
			painelTexto.add(labelDescricao);//adiciona descrição
			// Adiciona o painel no centro do card
			add(painelTexto, BorderLayout.CENTER);
			add(labelStatus,BorderLayout.EAST);//Coloca o status a direita do card
			 // Método para recriar a listagem
		}

	        @Override
	        public Component getListCellRendererComponent(
	                JList<? extends Tarefa> list,
	                Tarefa tarefa,
	                int index,
	                boolean isSelected,
	                boolean cellHasFocus
	        ) {

	            // Define o título que será exibido no card
	            labelTitulo.setText(tarefa.getTitulo());

	            // Define a descrição que será exibida no card
	            labelDescricao.setText(tarefa.getDescricao());

	            // Define o status que será exibido no card
	            labelStatus.setText(tarefa.getStatus());

	            // Se a tarefa estiver concluída, o status fica verde
	            if (tarefa.getStatus().equals("Concluída")) {
	                labelStatus.setForeground(new Color(22, 163, 74));
	            } else {
	                // Caso contrário, o status fica laranja
	                labelStatus.setForeground(new Color(234, 88, 12));
	            }

	            // Se o card estiver selecionado, muda a cor do fundo
	            if (isSelected) {
	                setBackground(new Color(219, 234, 254));
	            } else {
	                // Caso contrário, usa um fundo claro
	                setBackground(new Color(248, 250, 252));
	            }

	            // Retorna o próprio painel personalizado para ser exibido na lista
	            return this;
	        }
	    }
	}

