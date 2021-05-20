package Classes;

public class Ingrediente {
	private String nome;
	private float preco;
	private Long id;
	private boolean disponibilidadeDoIngrediente;
	private float valorCaloricoDoIngrediente;

	
	
	public Ingrediente(String n , float p,float valorC,boolean disp) {
		preco = p;
		nome = n;
		id = System.currentTimeMillis();
		disponibilidadeDoIngrediente  = disp;
		valorCaloricoDoIngrediente = valorC;
	}
	public boolean getDisponibilidade() {
		return disponibilidadeDoIngrediente;
	}
	public void setDisponibilidade(boolean x) {
		disponibilidadeDoIngrediente = x;
	}

	public float getValorCaloricoDoIngrediente() {
		return valorCaloricoDoIngrediente;
	}
	public void setValorCaloricoDoIngrediente(float valorCaloricoDoIngrediente) {
		this.valorCaloricoDoIngrediente = valorCaloricoDoIngrediente;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String toString() {
		return "\n Nome do Ingrediente: "+nome+"\n Preço do Ingrediente: "+preco+"\n ID: "+id; 
	}
	
	

}
