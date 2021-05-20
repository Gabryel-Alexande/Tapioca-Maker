package Classes;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Email {
	
	private String email = "gabryel.alexandre@academico.ifpb.edu.br";
	private String senha = "2348369JG@";
	private Administrador adm ;
	public Email() {
		Persistencia p = new Persistencia();
		Central c = p.recuperarCentral();
		adm = c.getAdministrador();
	}
	
	
	private void enviarEmail(String dest , String assunto,String texto ) {
		Properties props = new Properties();
		
		props.put("mail.smtp.user",email); 
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.port", "465");   
        props.setProperty("mail.smtp.socketFactory.port", "465");
        
        Session session = Session.getDefaultInstance(props,
        		new javax.mail.Authenticator() {
        	protected javax.mail.PasswordAuthentication getPasswordAuthentication()
        	{
        		return new javax.mail.PasswordAuthentication(email,senha);
        	}
        });
        session.setDebug(true);
        
       
        try {
        	Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			Address[] toUser = InternetAddress
					.parse(dest);
			message.setRecipients(Message.RecipientType.TO,toUser);
			message.setSubject(assunto);
			message.setText(texto);
			Transport.send(message);
		} catch(Exception e ) {
			JOptionPane.showMessageDialog(null,"Ocorreu um Erro!");
		}
	
	
	}
	
	public void esqueciASenha() {
		String m ="";
		String[]opcoes ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for()
	}
	public void enviarMalaDireta(String menssagem) {
		Persistencia p = new Persistencia();
		Central c  =p.recuperarCentral();
		for (Pedido pedido:c.getPedidosCadastrados()) {
			this.enviarEmail(pedido.getEmail(),"Novas Promoções",menssagem);
		}
	}
	
	public static void main(String[] args) {
		
}
}


