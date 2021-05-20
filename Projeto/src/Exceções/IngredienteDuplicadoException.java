package Exceções;

public class IngredienteDuplicadoException extends Exception {
	
	public String getMessage() {
		return "O Ingrediente já foi Cadastrado";
	}

}
