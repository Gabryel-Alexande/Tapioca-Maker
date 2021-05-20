package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {
	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
	private File arquivo = new File ("Dados.xml");

	public void salvarCentral(Central c )  {
		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n";
		xml += xstream.toXML(c);

		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}

	public Central recuperarCentral() {
		try {
			if(arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				return (Central)xstream.fromXML(fis);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return new Central();
	}

}
