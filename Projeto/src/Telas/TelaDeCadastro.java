package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Classes.Administrador;
import Classes.Central;
import Classes.Ingrediente;
import Classes.Persistencia;

public class TelaDeCadastro extends JFrame {
	private JTextField JTNome,
							JTIdade,
									JTDataDeNascimento,
													JTEmail,
														JTSenha,
															JTConfirmacao;
															
	
	private JButton JBCancelar,
							JBSalvar;


	public TelaDeCadastro() {
		inicio();
		adicionarLabels();
		adicionarText();
		adicionarBotoes();
		adicionarComboBox();
		setVisible(true);
		

		
		
	}

	private void inicio() {
		setResizable(false);
		setSize(550,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cadastro");
		setLocationRelativeTo(null);
	}
	private JComboBox<String> JEstadoCivil;
	private JComboBox<String> JSexo;
	private void adicionarComboBox() {
		String[]opcoes = {"Casado(a)","Solteiro(a)","Viúvo(a)"};
		JEstadoCivil = new JComboBox<String>(opcoes);
		JEstadoCivil.setBounds(340, 130,100,20);
		add(JEstadoCivil);
		
		String[] opcoes2 = {"M","F","I"};
		JSexo = new JComboBox<String>(opcoes2);
		JSexo.setBounds(300, 90,50,20);
		add(JSexo);
		
	}
	
	public JButton getJBCancelar() {
		return JBCancelar;
	}

	public void setJBCancelar(JButton jBCancelar) {
		JBCancelar = jBCancelar;
	}

	public JButton getJBSalvar() {
		return JBSalvar;
	}

	public void setJBSalvar(JButton jBSalvar) {
		JBSalvar = jBSalvar;
	}

	public JComboBox<String> getJEstadoCivil() {
		return JEstadoCivil;
	}

	public void setJEstadoCivil(JComboBox<String> jEstadoCivil) {
		JEstadoCivil = jEstadoCivil;
	}

	public JComboBox<String> getJSexo() {
		return JSexo;
	}

	public void setJSexo(JComboBox<String> jSexo) {
		JSexo = jSexo;
	}

	private void adicionarBotoes() {
		
		JBSalvar = new JButton("Salvar");
		JBSalvar.setToolTipText("Clique para Criar um Novo ADM");
		JBSalvar.setBounds(200, 300,90, 40);
		add(JBSalvar);
		
		JBSalvar.addActionListener(new OuvinteBTSalvar());
		
	}
	
	private class OuvinteBTSalvar implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//pegar as informações da interface gráfica
			String nome =null;
			String entradaData =null;
			Date dataDeNascimento = null;
			String entradaIdade =null;
			int idade = 0;
			String estadoCivil =null;
			String email = null;
			String senha =null;
			String confimacaoSenha =null;
			boolean x = true;
			try{
				
			 nome = JTNome.getText();
			
			 entradaData = JTDataDeNascimento.getText();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				dataDeNascimento = sdf.parse(entradaData);
			} catch (ParseException e1) {}
			
			 entradaIdade = JTIdade.getText();
			
			 idade = Integer.parseInt(entradaIdade);
			
			 estadoCivil = (String) JEstadoCivil.getSelectedItem();
			 email = JTEmail.getText();
			 senha = JTSenha.getText();
			 confimacaoSenha = JTConfirmacao.getText();
			}catch(Exception exce) {
				x = false;
			}
			if(x!=false) {
			 	x =false;
			
			 	if(senha.equals(confimacaoSenha)) {
					 x =true;
					if(nome!=null&&dataDeNascimento!=null&&idade!=0&&email!=null&&senha!=null) {
					Persistencia persistencia = new Persistencia();
					Central central = persistencia.recuperarCentral();
					
					Administrador administrador = new Administrador();
					administrador.setNome(nome);
					administrador.setDataDeNascimento(dataDeNascimento);
					administrador.setSenha(senha);
					administrador.setEmail(email);
					administrador.setEstadoCivil(estadoCivil);
					administrador.setIdade(idade);
					
					central.setAdministrador(administrador);
					persistencia.salvarCentral(central);
					
					
					
					JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!!");
					dispose();
					new TelaHomeADM();
					
					}
					
					
					//fazer desaparecer a janela atual e aparecer a tela inicial
				}
			if(x==false) {
				JOptionPane.showMessageDialog(null,"A Senha e Sua Confirmação devem ser Iguais!");
			}
			
	}
		else {
				JOptionPane.showMessageDialog(null,"Todos os Campos precisam ser Preenchidos!");
			}
		}
	}
	
	private void adicionarText() {
		JTNome = new JTextField();
		JTNome.setToolTipText("Digite seu Nome");
		JTNome.setBounds(60, 50,250,20);
		add(JTNome);
		MaskFormatter mascara=null;
		try{
			 mascara = new MaskFormatter("##/##/####");
		}catch(Exception e ) {}
		JTDataDeNascimento = new JFormattedTextField(mascara);
		JTDataDeNascimento.setToolTipText("Digite a data do seu nascimento");
		JTDataDeNascimento.setBounds(150, 90,70,20);
		add(JTDataDeNascimento);
		
		JTIdade = new JTextField();
		JTIdade.setToolTipText("Digite sua Idade");
		JTIdade.setBounds(70, 130,20,20);
		add(JTIdade);
		
		JTIdade.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				if (!Character.isDigit(arg0.getKeyChar()))
					arg0.consume();
			}
			
			public void keyReleased(KeyEvent arg0) {
				
			}
			
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		
		JTEmail = new JTextField();
		JTEmail.setToolTipText("Digite seu Email");
		JTEmail.setBounds(70, 170, 200,20);
		add(JTEmail);
		
		JTSenha = new JPasswordField();
		JTSenha.setToolTipText("Digite sua Senha");
		JTSenha.setBounds(70,210,100,20);
		add(JTSenha);
		
		JTConfirmacao = new JPasswordField();
		JTConfirmacao.setToolTipText("Digite a confirmação da Senha");
		JTConfirmacao.setBounds(340, 210,100,20);
		add(JTConfirmacao);
		
		
		
	}
	private void adicionarLabels() {
		JLabel texto = new JLabel("Tapioca Maker-Cadastro de Administrador",JLabel.CENTER);
		texto.setBounds(0,0,600,40);
		texto.setOpaque(isOpaque());
		texto.setBackground(new Color(200,51,0));
		texto.setFont(new Font("Times",Font.BOLD,17));
		add(texto);
		texto = new JLabel("Nome");
		texto.setBounds(20,50,35, 20);
		add(texto);
		
		texto= new JLabel("Data de Nascimento");
		texto.setBounds(20,90,120,20);
		add(texto);
		
		texto = new JLabel("Idade");
		texto.setBounds(20, 130,40, 20);
		add(texto);
		
		texto = new JLabel("Email");
		texto.setBounds(20, 170, 40,20);
		add(texto);
		
		texto = new JLabel("Senha");
		texto.setBounds(20,210,40,20);
		add(texto);
		
		texto = new JLabel("Estado Civil");
		texto.setBounds(250,130,80,20);
		add(texto);
		
		texto = new JLabel("Confirmação");
		texto.setBounds(250, 210,80,20);
		add(texto);
		
		texto = new JLabel("Sexo");
		texto.setBounds(250,90, 40,20);
		add(texto);
	
	}
	public JTextField getJTSenha() {
		return JTSenha;
	}

	public void setJTSenha(JTextField jTSenha) {
		JTSenha = jTSenha;
	}

	public JTextField getJTConfirmacao() {
		return JTConfirmacao;
	}

	public void setJTConfirmacao(JTextField jTConfirmacao) {
		JTConfirmacao = jTConfirmacao;
	}
	public JTextField getJTNome() {
		return JTNome;
	}

	public void setJTNome(JTextField jTNome) {
		JTNome = jTNome;
	}

	public JTextField getJTIdade() {
		return JTIdade;
	}

	public void setJTIdade(JTextField jTIdade) {
		JTIdade = jTIdade;
	}

	public JTextField getJTDataDeNascimento() {
		return JTDataDeNascimento;
	}

	public void setJTDataDeNascimento(JTextField jTDataDeNascimento) {
		JTDataDeNascimento = jTDataDeNascimento;
	}

	public JTextField getJTEmail() {
		return JTEmail;
	}

	public void setJTEmail(JTextField jTEmail) {
		JTEmail = jTEmail;
	}
	
	public static void main(String[] args) {
		new TelaDeCadastro();
	}
	
}
