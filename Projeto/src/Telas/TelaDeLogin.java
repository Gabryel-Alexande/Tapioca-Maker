package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Classes.Administrador;
import Classes.Central;
import Classes.Persistencia;

public class TelaDeLogin extends JFrame {
	public TelaDeLogin() {
		inicio();
		adicionarLabel();
		adicionarBotoes();
		adicionarText();
		setVisible(true);
	}

	

private JButton JBCancelar,
						JBSalvar,
							JBEsqueciASenha;
	
private JTextField JTEmail,
						JTSenha;

	private void adicionarText() {
		JTEmail = new JTextField();
		JTEmail.setBounds(110,50,200,20);
		add(JTEmail);
		
		JTSenha = new JPasswordField();
		JTSenha.setBounds(110,90,100,20);
		add(JTSenha);
		
	}
	private void adicionarBotoes() {
		JBCancelar  = new JButton("Cancelar");
		JBCancelar.setToolTipText("Voltar ao Início ?");
		JBCancelar.setBounds(130,200,100,30);
		add(JBCancelar);
		
		JBSalvar = new JButton("Salvar");
		JBSalvar.setToolTipText("Deseja Prosseguir ?");
		JBSalvar.setBounds(250, 200,100,30);
		add(JBSalvar);
		
		OuvinteBtOutros ouvinteBtOutros = new OuvinteBtOutros();
		JBCancelar.addActionListener(ouvinteBtOutros);
		JBSalvar.addActionListener(ouvinteBtOutros);
		
		JBEsqueciASenha = new JButton("Esqueci a senha");
		JBEsqueciASenha.setToolTipText("Clique se esqueceu sua senha");
		JBEsqueciASenha.setBounds(50, 130,135,30);
		add(JBEsqueciASenha);
		
		OuvinteBtEsqueciASenha ouvinteBtEsqueciASenha = new OuvinteBtEsqueciASenha();
		JBEsqueciASenha.addActionListener(ouvinteBtEsqueciASenha);
		
	}

	private class OuvinteBtOutros implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			/*
			JButton botaoClicado = (JButton) e.getSource();
			
			if (botaoClicado == JBSalvar)
				JOptionPane.showMessageDialog(null, "Cliquei no salvar");
			else
				JOptionPane.showMessageDialog(null, "Cliquei no cancelar");
			
			botaoClicado.setText("Fui clicado");
			botaoClicado.repaint();
			*/
			
			String rotulo = e.getActionCommand();
			Persistencia p = new Persistencia();
			Central c = p.recuperarCentral();
			switch (rotulo) {
				case "Cancelar":
					dispose();
					new TelaInicial();
					break;
				case "Salvar":
					String email = JTEmail.getText();
					String senha = JTSenha.getText();
					if(email.equals(c.getAdministrador().getEmail())&&senha.equals(c.getAdministrador().getSenha())) {
						dispose();
						new TelaHomeADM();
					}
					else {
						JOptionPane.showMessageDialog(null,"Email ou Senha Inválidos");
					}
					break;
			}
			
		}
		
	}
	
	private class OuvinteBtEsqueciASenha implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String email = JOptionPane.showInputDialog("Digite seu e-mail aqui");
			
			Persistencia persistencia = new Persistencia();
			
			Central central = persistencia.recuperarCentral();
			
			Administrador adm = central.getAdministrador();
			
			boolean achou = false;
			if (adm != null) {
				if (adm.getEmail().equals(email)) {
					JOptionPane.showMessageDialog(null, "Emai enviado");
					achou = true;
				}
			}
			
			if (!achou)
				JOptionPane.showMessageDialog(null, "Não há usuário com esse e-mail");

		}
	}
	

	private void adicionarLabel() {
		JLabel texto = new JLabel("Tapioca Maker-Login ADM",JLabel.CENTER);
		texto.setOpaque(isOpaque());
		texto.setBackground(new Color(251,51,0));
		texto.setFont(new Font("Times",Font.BOLD,15));
		texto.setBounds(0, 0,500,30);
		add(texto);
		
		texto = new JLabel("Email");
		texto.setBounds(50,50,50,30);
		add(texto);
		
		texto = new JLabel("Senha");
		texto.setBounds(50,90,50,30);
		add(texto);
		
	}

	private void inicio() {
		setResizable(false);
		setSize(500,300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login-ADM");
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new TelaDeLogin();
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

	public JButton getJBEsqueciASenha() {
		return JBEsqueciASenha;
	}

	public void setJBEsqueciASenha(JButton jBEsqueciASenha) {
		JBEsqueciASenha = jBEsqueciASenha;
	}
}
