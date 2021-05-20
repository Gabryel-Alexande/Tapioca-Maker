package Exceções;

public class PedidoNaoEncontradoException extends Exception {
	
	public String getMessage() {
		return "Pedido não Encontrado";
	}

}
