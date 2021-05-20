package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Central;
import Classes.Ingrediente;
import Classes.Pedido;
import Classes.Persistencia;

public class TelaHomeADM extends JFrame {
	
	private JMenuItem JMListaDeIngredientes,
										JMListaDeTapiocas,
													JMExcluirTapioca,
																JMGerarRelatorio,
																		JMEnviarMala,
																			JMSair;				
									
	
	
	public TelaHomeADM() {
		inico();
		adicionarLabel();
		adicionarBotoes();
		adicionarMenu();
		adicionarTabelaNoMeio();
		setVisible(true);

		
	}
	
	private void adicionarTabelaNoMeio() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable();
		tabela.setModel(modelo);
		
		modelo.addColumn("Id");
		modelo.addColumn("Cliente");
		modelo.addColumn("QTD");
		modelo.addColumn("Preço");
		modelo.addColumn("Estado");
		
		Persistencia persistencia = new Persistencia();
		Central central = persistencia.recuperarCentral();
		
		ArrayList<Pedido> todosOsPedidos = central.getPedidosCadastrados();
		
		for(Pedido p: todosOsPedidos) {
			String[] linha = new String[5];
			
			linha[0] = ""+p.getNumeroDoPedido();
			linha[1] = p.getEmail();
			linha[2] = ""+p.getQuantidade();
			linha[3] = ""+p.getPreco();
			if(p.getEstadoDoPedido()) {
				linha[4]="Aberto";
			}else {
				linha[4]="Fechado";
			}
			
			
			modelo.addRow(linha);
		}
		
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(0, 130, 550, 270);
		add(painel);
	}

	private class OuvinteDosMenus implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			String rotulo = e.getActionCommand();
			switch(rotulo) {
			case "Ingredientes":
				dispose();
				new TelaDosIngredientes();
				break;
			case"Tapiocas":
				dispose();
				new TelaDasTapiocas();
				break;
			case"Sair":
				dispose();
				break;
			}
			
		}
		
	}

	private void adicionarBotoes() {
		
		
	}

	private void adicionarMenu() {
		JMenuBar barraDeMenu = new JMenuBar();
		this.setJMenuBar(barraDeMenu);
		
		JMenu menu = new JMenu("Opções do Sistema");
		barraDeMenu.add(menu);
		OuvinteDosMenus ouvinte = new OuvinteDosMenus();
		
		JMListaDeIngredientes = new JMenuItem("Ingredientes");
		JMListaDeIngredientes.setToolTipText("Clique para ver todos os Ingredientes(adicionar e excluir)");
		JMListaDeIngredientes.addActionListener(ouvinte);
		menu.add(JMListaDeIngredientes);
		
		JMListaDeTapiocas = new JMenuItem("Tapiocas");
		JMListaDeTapiocas.setToolTipText("Clique para ver todas as Tapiocas(adicionar e excluir)");
		menu.add(JMListaDeTapiocas);
		JMListaDeTapiocas.addActionListener(ouvinte);
		
		JMEnviarMala = new JMenuItem("Enviar Mala Direta");
		JMEnviarMala.setToolTipText("Envia Um email para todos que ja compraram");
		menu.add(JMEnviarMala);
		
		JMGerarRelatorio = new JMenuItem("Gerar Relatório");
		JMGerarRelatorio.setToolTipText("Clique para gerar um relatório das Tapiocas mais pedidas");
		menu.add(JMGerarRelatorio);
		
		JMSair = new JMenuItem("Sair");
		JMSair.setToolTipText("Deseja sair do Programa ?");
		JMSair.addActionListener(ouvinte);
		menu.add(JMSair);
		
	}

	private void adicionarLabel() {
		JLabel texto = new JLabel("BEM VINDO ao Tapioca Maker-ADM HOME",JLabel.CENTER);
		texto.setFont(new Font("Times",Font.BOLD,20));
		texto.setBounds(0,0,550,40);
		texto.setOpaque(isOpaque());
		texto.setBackground(new Color(50,50,200));
		texto.setForeground(Color.WHITE);
		
		add(texto);
				
		
	}

	private void inico() {
		setResizable(false);
		setSize(550,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cadastro");
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new TelaHomeADM();
	}

}
