package Classes;

import java.util.ArrayList;

public class Tapioca {
	private String nome;
	private float precoTapioca;
	private ArrayList<Ingrediente>ingredientesDaTapioca = new ArrayList<>();
	private float valorCalorico;
	private boolean disponibilidadeDaTapioca;
	
	public Tapioca(String n, ArrayList<Ingrediente> i) {
		nome = n;
		ingredientesDaTapioca = i;
		
		for(Ingrediente ingre:i) {
			precoTapioca+=ingre.getPreco();
			valorCalorico+=ingre.getValorCaloricoDoIngrediente();
			if(ingre.getDisponibilidade()==false) {
				disponibilidadeDaTapioca =false;
			}
		}
	}
	
	public Ingrediente procuradorDeIngrediente(Ingrediente i ) {
		
		for(Ingrediente ingrediente:ingredientesDaTapioca) {
			if (i==ingrediente) {
				return i;
			}
		}
		return null;
		
	}
	public boolean verificarDisponibilidade() {
		disponibilidadeDaTapioca =true;
		
		for(Ingrediente ingre:ingredientesDaTapioca) {
			if(ingre.getDisponibilidade()==false) {
				disponibilidadeDaTapioca =false;
			}
			
			
		}
		return disponibilidadeDaTapioca;
	}

	public float getValorCalorico() {
		return valorCalorico;
	}

	public void setValorCalorico(float valorCalorico) {
		this.valorCalorico = valorCalorico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Ingrediente> getIngredientesDaTapioca() {
		return ingredientesDaTapioca;
	}

	public void setIngredientesDaTapioca(ArrayList<Ingrediente> ingredientesDaTapioca) {
		this.ingredientesDaTapioca = ingredientesDaTapioca;
	}
	
	public String toString() {
		String frase = "Nome da Tapioca: "+nome+"\n Preço da Tapioca: "+precoTapioca+"\n Ingredientes:";
		for(Ingrediente i:ingredientesDaTapioca) {
			frase+="\n"+i.getNome();
		}
		return frase;
	}
	public float getPrecoTapioca() {
		return precoTapioca;
	}
	public void setPrecoTapioca(float precoTapioca) {
		this.precoTapioca = precoTapioca;
	}
	public boolean isDisponibilidadeDaTapioca() {
		return disponibilidadeDaTapioca;
	}
	public void setDisponibilidadeDaTapioca(boolean disponibilidadeDaTapioca) {
		this.disponibilidadeDaTapioca = disponibilidadeDaTapioca;
	}
	
}
