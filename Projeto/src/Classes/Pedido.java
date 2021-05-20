package Classes;

import java.util.ArrayList;

public class Pedido {
	
	private int quantidade ;
	private int numeroDoPedido;
	private String email;
	private ArrayList<Tapioca> carrinho = new ArrayList<>();
	private boolean estadoDoPedido;
	
	
	public Pedido(String email,ArrayList<Tapioca> carrinho,int numeroDoPedido) {
		this.email = email;
		this.carrinho = carrinho;
		this.numeroDoPedido=numeroDoPedido;
		estadoDoPedido = true;
	}
	public boolean getEstadoDoPedido() {
		return estadoDoPedido;
	}
	public void setEstadoDoPedido(boolean estadoDoPedido) {
		this.estadoDoPedido = estadoDoPedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getNumeroDoPedido() {
		return numeroDoPedido;
	}
	public void setNumeroDoPedido(int numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Tapioca> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(ArrayList<Tapioca> carrinho) {
		this.carrinho = carrinho;
	}
	
	public float getPreco() {
		float preco = 0;
		for(Tapioca t: carrinho) {
			preco += t.getPrecoTapioca();
		}
		return preco;
	}
	
}
