package Exce��es;

public class IngredienteDuplicadoException extends Exception {
	
	public String getMessage() {
		return "O Ingrediente j� foi Cadastrado";
	}

}
