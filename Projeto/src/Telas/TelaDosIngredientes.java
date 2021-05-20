package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Classes.Central;
import Classes.Ingrediente;
import Classes.Persistencia;
import Exceções.IngredienteDuplicadoException;


public class TelaDosIngredientes extends JFrame {
	
	private JButton JBVoltar,
							JBCriarIngrediente,
											JBExcluirIngrediente,
													JBEditarIngrediente;
	
	

	private JTextField JTNome,
							JTCalorias,
									JTPreco;
									
private DefaultTableModel modelo = new DefaultTableModel();
private JTable tabela=new JTable(modelo);
	

	public TelaDosIngredientes() {
		inicio();
		adicionarLabel();
		adicionarTabela();
		adicionarBotao();
		adicionarText();
		adicionarComboBox();
		setVisible(true);
		
		
		
		
	}
	
private JComboBox<String> JCDisponibilidade;
	private void adicionarComboBox() {
		String[]opcoes = {"Sim","Não"};
		
		JCDisponibilidade = new JComboBox<String>(opcoes);
		JCDisponibilidade.setBounds(310,130, 50, 20);
		add(JCDisponibilidade);
		
	}
	private class OuvinteDosTextos implements KeyListener{

		
		public void keyPressed(KeyEvent e) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (!Character.isDigit(e.getKeyChar())) {
				String le = new String(""+e.getKeyChar());
				
				if(!le.equals(".")) {
					e.consume();
				}
			}
				
		}
		
	}

	private void adicionarText() {
		OuvinteDosTextos ouvinteT = new OuvinteDosTextos();
		
		JTNome = new JTextField();
		JTNome.setBounds(150, 70,100,20);
		add(JTNome);
		
		JTCalorias = new JTextField();
		JTCalorias.setBounds(70 , 130, 30,20);
		add(JTCalorias);
		JTCalorias.addKeyListener(ouvinteT);
		
		JTPreco = new JTextField();
		JTPreco.setBounds(70, 100,30,20);
		add(JTPreco);
		JTPreco.addKeyListener(ouvinteT);
		
		
		
		
	}

	private void adicionarBotao() {
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();
		
		JBVoltar = new JButton("Voltar");
		JBVoltar.setToolTipText("Voltar para Tela Home ?");
		JBVoltar.setBounds(30, 535,70,30);
		JBVoltar.addActionListener(ouvinte);
		add(JBVoltar);
		
		JBCriarIngrediente  = new JButton("Criar");
		JBCriarIngrediente.setBounds(170 ,170,100,20);
		add(JBCriarIngrediente);
		JBCriarIngrediente.addActionListener(ouvinte);
		
		JBExcluirIngrediente = new JButton("Excluir");
		JBExcluirIngrediente.setBounds(110, 535,80,30);
		JBExcluirIngrediente.setToolTipText("Exclui o Ingrediente da linha selecionada");
		add(JBExcluirIngrediente);
		JBExcluirIngrediente.addActionListener(ouvinte);
		
		JBEditarIngrediente = new JButton("Editar");
		JBEditarIngrediente.setBounds(200,535,80,30);
		JBEditarIngrediente.setToolTipText("Editar Ingrediente Selecionado");
		add(JBEditarIngrediente);
		JBEditarIngrediente.addActionListener(ouvinte);
		
		
	}
	
	private class OuvinteDosBotoes implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			String rotulo = e.getActionCommand();
			
			switch(rotulo) {
			case"Voltar":
				dispose();
				new TelaHomeADM();
				break;
			case"Criar":
				
				String Nome = JTNome.getText();
				if(!Nome.equals("")) {
					
					float Preco =0;
					try {
					Preco = Float.parseFloat(JTPreco.getText());
					float Calorias = Float.parseFloat(JTCalorias.getText());
					boolean disponi =false;
					if (JCDisponibilidade.getSelectedItem().equals("Sim")){
						disponi = true;
					}
					try {
						Persistencia persistencia = new Persistencia();
						Central central = persistencia.recuperarCentral();
					
						central.adicionarIngrediente(new Ingrediente(Nome,Preco,Calorias,disponi));
						persistencia.salvarCentral(central);
					
					
						String[] opcoes = {Nome,""+Preco,"Inativo",""+JCDisponibilidade.getSelectedItem()};
						modelo.addRow(opcoes);
						tabela.repaint();
					}catch(IngredienteDuplicadoException ex) {
						JOptionPane.showMessageDialog(null,"Ingrediente Já Cadastrado");
					}
					
					
					
					
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Todos Os Campos precisam ser Preenchidos !");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Coloque um Nome");
				}
				break;
			case "Excluir":
				int l = tabela.getSelectedRow();
				if (l==-1) {
					JOptionPane.showMessageDialog(null,"Selecione uma linha");
				}else {
					if(tabela.getValueAt(l,2).equals("Ativo")){
						JOptionPane.showMessageDialog(null,"Ingrediente sendo usado,Impossível ser deletado ");
					}else {
						modelo = (DefaultTableModel) tabela.getModel();
						Persistencia p = new Persistencia();
						Central c = p.recuperarCentral();
						c.excluirIngrediente((String)(modelo.getValueAt(l,0)));
						p.salvarCentral(c);
						try {
							modelo.removeRow(l);
							tabela.repaint();
						}catch(Exception ex) {}
						}
				}
				break;
			case"Editar":
				int linha = tabela.getSelectedRow();
				if (linha==-1) {
					JOptionPane.showMessageDialog(null,"Selecione uma linha");
				}else {
					Persistencia p = new Persistencia();
					Central c = p.recuperarCentral();
					Ingrediente i = c.procuradorDeIngrediente(""+tabela.getValueAt(linha,0));
					
					
					dispose();
					new TelaEdicaoIngredientes(i);
					
				}
				
					
				
			}
			
		}
		
	}



	private void adicionarTabela() {
		
		
		modelo.addColumn("Nome");
		modelo.addColumn("Preço");
		modelo.addColumn("Situação");
		modelo.addColumn("Disponivel");
		modelo.addColumn("Calorias");
		
		Persistencia p  = new Persistencia();
		Central central = p.recuperarCentral();
		
		ArrayList<Ingrediente>ingredientes = central.getIngredientesCadastrados();
		for(Ingrediente i :ingredientes) {
			String[] linha = new String[5];
			linha[0] = i.getNome();
			linha[1] = ""+i.getPreco();
			if(central.procuradorDeIngrediente(i)!=null) {
				linha[2]="Ativo";
			}
			else {
				linha[2]="Inativo";
			}
			if(i.getDisponibilidade()) {
				linha[3]="Sim";
			}
			else {
				linha[3]="Não";
			}
			linha[4]=""+i.getValorCaloricoDoIngrediente();
			modelo.addRow(linha);
		}
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(0, 260, 500, 270);
		add(painel);
	}

	private void inicio() {
		setResizable(false);
		setSize(500,600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Ingredientes");
		setLocationRelativeTo(null);
		
	}

	private void adicionarLabel() {
		JLabel texto = new JLabel("Tela de Ingredientes",JLabel.CENTER);
		texto.setFont(new Font("Times",Font.BOLD,20));
		texto.setForeground(Color.white);
		texto.setOpaque(isOpaque());
		texto.setBackground(new Color(50,50,200));
		texto.setBounds(0,20,500,40);
		add(texto);
		
		texto = new JLabel("Nome do Ingrediente");
		texto.setBounds(20, 70,120,20);
		add(texto);
		
		texto = new JLabel("Preço");
		texto.setBounds(20, 100,40,20);
		add(texto);
		
		texto = new JLabel("Calorias");
		texto.setBounds(20,130,50,20);
		add(texto);
		
		texto = new JLabel("Está Disponível ?");
		texto.setBounds(200,130,100,20);
		add(texto);
		
		
	}
	public JButton getJBExcluirIngrediente() {
		return JBExcluirIngrediente;
	}

	public void setJBExcluirIngrediente(JButton jBExcluirIngrediente) {
		JBExcluirIngrediente = jBExcluirIngrediente;
	}

	public JButton getJBEditarIngrediente() {
		return JBEditarIngrediente;
	}

	public void setJBEditarIngrediente(JButton jBEditarIngrediente) {
		JBEditarIngrediente = jBEditarIngrediente;
	}
	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	public JTextField getJTNome() {
		return JTNome;
	}

	public void setJTNome(JTextField jTNome) {
		JTNome = jTNome;
	}

	public JTextField getJTCalorias() {
		return JTCalorias;
	}

	public void setJTCalorias(JTextField jTCalorias) {
		JTCalorias = jTCalorias;
	}

	public JTextField getJTPreco() {
		return JTPreco;
	}

	public void setJTPreco(JTextField jTPreco) {
		JTPreco = jTPreco;
	}

	public JComboBox<String> getJCDisponibilidade() {
		return JCDisponibilidade;
	}

	public void setJCDisponibilidade(JComboBox<String> jCDisponibilidade) {
		JCDisponibilidade = jCDisponibilidade;
	}
	public JButton getJBVoltar() {
		return JBVoltar;
	}

	public void setJBVoltar(JButton jBVoltar) {
		JBVoltar = jBVoltar;
	}

	public JButton getJBCriarIngrediente() {
		return JBCriarIngrediente;
	}

	public void setJBCriarIngrediente(JButton jBCriarIngrediente) {
		JBCriarIngrediente = jBCriarIngrediente;
	}
	public static void main(String[] args) {
		new TelaDosIngredientes();
	}

}
