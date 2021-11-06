package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Classes.Central;
import Classes.Ingrediente;
import Classes.Persistencia;

public class TelaEdicaoIngredientes extends JFrame {
private JTextField JTNome,
						  JTCalorias,
						  			JTPreco,
						  				JTId;

	
private JButton JBCancelar,
						JBSalvar;
private JComboBox<String>JCDisponibilidade;
private Ingrediente ingrediente;
	
	public TelaEdicaoIngredientes(Ingrediente i) {
		inicio();
		this.ingrediente = i;
		adicionarLabel();
		adicionarComboBox();
		adicionarBotoes();
		adicionarText();
		setVisible(true);
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
		OuvinteDosTextos ouvinte = new OuvinteDosTextos();
		
		JTNome = new JTextField(ingrediente.getNome());
		JTNome.setBounds(55,20,100,20);
		add(JTNome);
		
		JTPreco =new JTextField(""+ingrediente.getPreco());
		JTPreco.setBounds(55,70,50,20);
		add(JTPreco);
		JTPreco.addKeyListener(ouvinte);
		
		JTCalorias = new JTextField(""+ingrediente.getValorCaloricoDoIngrediente());
		JTCalorias.setBounds(270,20 ,70,20);
		add(JTCalorias);
		JTCalorias.addKeyListener(ouvinte);
		
		JTId = new JTextField(""+ingrediente.getId());
		JTId.setEditable(false);
		JTId.setBounds(35,100,100,20);
		add(JTId);
	}
	 private class OuvinteDosBotoes implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String rotulo = e.getActionCommand();
			switch(rotulo) {
			case"Cancelar":
				dispose();
				new TelaDosIngredientes();
				break;
			case"Salvar":
				
				String nome = JTNome.getText();
				if(!nome.equals("")) {
				try {
					
					float preco = Float.parseFloat(JTPreco.getText());
					float calorias = Float.parseFloat(JTCalorias.getText());
					boolean disp = false;
					if(JCDisponibilidade.getSelectedItem().equals("Sim")) {
						disp = true;
					}
					ingrediente.setNome(nome);
					ingrediente.setValorCaloricoDoIngrediente(calorias);
					ingrediente.setDisponibilidade(disp);

//o problema está aqui
					Persistencia p = new Persistencia();
//quando você recupera a central de novo, o objeto ingrediente que você está editando não é o mesmo objeto que está salvo na central (é como se fosse uma cópia)
					Central c = p.recuperarCentral();
					p.salvarCentral(c);
					
					dispose();
					new TelaDosIngredientes();
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Todos Os campos devem ser preenchidos");
				}
				
				}else {
					JOptionPane.showMessageDialog(null,"Digite um nome");
				}
				
			}
		}
	}

	private void adicionarBotoes() {
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();
		JBCancelar = new JButton("Cancelar");
		JBCancelar.setBounds(60,180,90,20);
		add(JBCancelar);
		JBCancelar.addActionListener(ouvinte);
		JBSalvar = new JButton("Salvar");
		JBSalvar.setBounds(200,180,90,20);
		add(JBSalvar);
		JBSalvar.addActionListener(ouvinte);
		
	}

	private void adicionarComboBox() {
		String[]opcoes = {"Sim","N�o"};
		JCDisponibilidade = new JComboBox<String>(opcoes);
		JCDisponibilidade.setBounds(290,70,60,20);
		add(JCDisponibilidade);
		
	}

	private void adicionarLabel() {
		JLabel texto = new JLabel("Nome");
		texto.setBounds(10,20,40,20);
		add(texto);
		
		texto = new JLabel("Pre�o");
		texto.setBounds(10,70 ,40,20);
		add(texto);
		
		texto = new JLabel("Calorias");
		texto.setBounds(220, 20,70,20);
		add(texto);
		
		texto = new JLabel("Disponivel ?");
		texto.setBounds(220,70,100,20);
		add(texto);
		
		texto = new JLabel("ID");
		texto.setBounds(10,100,20,20);
		add(texto);
		
	}

	private void inicio() {
		setResizable(false);
		setSize(400,250);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Editar Ingrediente");
		setLocationRelativeTo(null);
		
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

}
