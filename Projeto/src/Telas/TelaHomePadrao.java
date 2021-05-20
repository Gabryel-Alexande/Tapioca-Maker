package Telas;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Central;
import Classes.Pedido;
import Classes.Persistencia;
import Classes.Tapioca;

public class TelaHomePadrao extends JFrame {
	
	public TelaHomePadrao(){
		inicio();
		adicionarLabel();
		adicionarBotoes();
		adicionarMenu();
		adicionarTabela();
		setVisible(true);
	}

	private void adicionarMenu() {
		
		
	}

	private void adicionarTabela() {
		DefaultTableModel modelo = new DefaultTableModel();
		
		JTable tabela = new JTable();
		tabela.setModel(modelo);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Preço");
		modelo.addColumn("Disponibilidade");
		
		Persistencia persistencia = new Persistencia();
		Central central = persistencia.recuperarCentral();
		
		ArrayList<Tapioca> cardapio = central.getTapiocasCadastradas();
		
		for(Tapioca t: cardapio) {
			String[] linha = new String[3];
			
			 
			linha[0] = ""+t.getNome();
			linha[1] = ""+t.getPrecoTapioca();
			if(t.verificarDisponibilidade()){
				linha[2]="Disponível";
			}else{
				linha[2]="Indisponivel";
			}
			
			modelo.addRow(linha);
		}
		
		JScrollPane painel = new JScrollPane(tabela);
		painel.setBounds(0, 130, 550, 270);
		
		add(painel);
		
	}

	private void adicionarBotoes() {
		
		
	}

	private void adicionarLabel() {
		
		
	}

	private void inicio() {
		setResizable(false);
		setSize(550,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cadastro");
		setLocationRelativeTo(null);
		
	}
	public static void main(String[] args) {
		new TelaHomePadrao();
	}

}
