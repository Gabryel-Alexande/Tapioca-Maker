package Classes;

import java.util.ArrayList;

import Exceções.IngredienteDuplicadoException;
import Exceções.PedidoNaoEncontradoException;

public class Central {
	
	private ArrayList<Ingrediente> ingredientesCadastrados = new ArrayList<>();
	private ArrayList<Tapioca> tapiocasCadastradas = new ArrayList<>();
	private Administrador administrador;
	private ArrayList<Pedido> pedidosCadastrados = new ArrayList<>();
	
	
	public Central() {
		/*
		Ingrediente ingrediente1 = new Ingrediente("queijo", 2f, 200f, true);
		Ingrediente ingrediente2 = new Ingrediente("goiaba", 2f, 200f, true);
		
		ArrayList<Ingrediente> receita = new ArrayList<Ingrediente>();
		receita.add(ingrediente1);
		receita.add(ingrediente2);
		
		Tapioca t = new Tapioca("romeu e julieta", receita);
		
		ArrayList<Tapioca> carrinho = new ArrayList<Tapioca>();
		carrinho.add(t);
		
		Pedido p = new Pedido("c@c.com", carrinho, 1);
		
		ingredientesCadastrados.add(ingrediente1);
		ingredientesCadastrados.add(ingrediente2);
		tapiocasCadastradas.add(t);
		pedidosCadastrados.add(p);
		*/
	}
	
public Ingrediente procuradorDeIngrediente(String n) {
	for(Ingrediente i:ingredientesCadastrados) {
		if(i.getNome().equals(n)) {
			return i;
		}
	}
	return null;
}
	
public void excluirIngrediente(String n) {
	Ingrediente ingre = null;
	for(Ingrediente i :ingredientesCadastrados) {
		if(i.getNome().equals(n)) {
			ingre = i;
			break;
		}
	}
	ingredientesCadastrados.remove(ingre);
}
public Ingrediente procuradorDeIngrediente(Ingrediente i ) {
		
		for(Tapioca t:tapiocasCadastradas) {
			for(Ingrediente ingrediente:t.getIngredientesDaTapioca()) {
				if (ingrediente==i) {
					return i;
				}
			}
		
		}
		return null;
		
	}
	
	
	public Pedido procurarPedido(String ema)throws PedidoNaoEncontradoException {
		for(Pedido p:pedidosCadastrados) {
			if(p.getEmail().equals(ema))
				return p;
		}
		
		throw new PedidoNaoEncontradoException();
		
	}
	
	public Pedido procurarPedido(int numero)throws PedidoNaoEncontradoException {
		for(Pedido p:pedidosCadastrados) {
			if(p.getNumeroDoPedido()==numero)
				return p;
		}
		
		throw new PedidoNaoEncontradoException();
		
	}
	
	public ArrayList<Pedido> getPedidosCadastrados() {
		return pedidosCadastrados;
	}
	public void setPedidosCadastrados(ArrayList<Pedido> pedidosCadastrados) {
		this.pedidosCadastrados = pedidosCadastrados;
	}
	public void  adicionarIngrediente(Ingrediente i)throws IngredienteDuplicadoException{
		for(Ingrediente ingrediente:ingredientesCadastrados) {
			if (i.getNome().equalsIgnoreCase(ingrediente.getNome())) {
				throw new IngredienteDuplicadoException();
			}
		
		}
		ingredientesCadastrados.add(i);
		
	}
	public ArrayList<Ingrediente>getIngredientesCadastrados(){
		return ingredientesCadastrados;
	}
	
	public Ingrediente recuperarIngredinte(Long id) {
		for(Ingrediente i:ingredientesCadastrados) {
			if (i.getId()==id) {
				return i;
			}
		}
		return null;
	}
	public Ingrediente recuperarIngredinte(String n) {
		for(Ingrediente i:ingredientesCadastrados) {
			if (i.getNome().equals(n)) {
				return i;
			}
		}
		return null;
	}
	public boolean adicionarTapioca(Tapioca t) {
		for(Tapioca tapiocas:tapiocasCadastradas) {
			if(tapiocas.getNome().equals(t.getNome())) {
				return false;
			}
		}
		tapiocasCadastradas.add(t);
		return true;
	}
	
	public Tapioca recuperarTapioca(String n ) {
		for(Tapioca t:tapiocasCadastradas) {
			if(t.getNome().equals(n))
				return t;
		}
		return null;
		
	}
	public ArrayList<Tapioca> getTapiocasCadastradas() {
		return tapiocasCadastradas;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public void setIngredientesCadastrados(ArrayList<Ingrediente> ingredientesCadastrados) {
		this.ingredientesCadastrados = ingredientesCadastrados;
	}
	public void setTapiocasCadastradas(ArrayList<Tapioca> tapiocasCadastradas) {
		this.tapiocasCadastradas = tapiocasCadastradas;
	}
	

}
