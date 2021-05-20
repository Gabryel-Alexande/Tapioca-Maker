package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import Classes.Central;
import Classes.Persistencia;

public class TelaInicial extends JFrame {
	public TelaInicial() {
		inicio();
		adicionarBotoes();
		adicionarLabel();
		setVisible(true);
	}
private JButton JBAdm,
					JBUsuario;

private class OuvinteDosBotoes implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		
		String rotulo =e.getActionCommand();
		switch(rotulo) {
		case"Usuário Administrador":
			dispose();
			new TelaDeLogin();
			break;
		case"Usúario Padrão":
			dispose();
			new TelaHomePadrao();
			break;
		}
		
	}
	
	
}

	private void adicionarBotoes() {
		OuvinteDosBotoes ouvinte = new OuvinteDosBotoes();
		JBAdm = new JButton ("Usuário Administrador");
		JBAdm.setFont(new Font("Times",Font.BOLD,15));
		JBAdm.setBounds(100,100,200,50);
		add(JBAdm);
		JBAdm.addActionListener(ouvinte);
		
		JBUsuario = new JButton("Usúario Padrão");
		JBUsuario.setFont(new Font("Times",Font.BOLD,15));
		JBUsuario.setBounds(100,200,200,50);
		add(JBUsuario);
		JBUsuario.addActionListener(ouvinte);
	}

	private void inicio() {
		setResizable(false);
		setSize(400,400);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Início");
		setLocationRelativeTo(null);
		
	}
	
	private void adicionarLabel() {
		JLabel texto = new JLabel("Bem Vindo ao Tapioca Maker",JLabel.CENTER);
		texto.setFont(new Font("Times",Font.BOLD,20));
		texto.setOpaque(isOpaque());
		texto.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
		texto.setBackground(Color.white);
		texto.setBounds(0, 0,400,50);
		add(texto);
		
		texto = new JLabel();
		texto.setOpaque(isOpaque());
		texto.setBackground(Color.blue);
		texto.setBounds(0,51,400,400);
		add(texto);
	}
	
	public static void main(String[]args) {
		new TelaInicial();
	}

}
