
public class Main {
	static Interpreter interpreter;
	public static void main(String[] args) throws Exception {
		interpreter = new Interpreter();

		Prompt prompt = new Prompt();
		boolean haSalido = false;
		while (!haSalido){
			prompt.Escuchar(interpreter.variablesDelUsuario);

			if (prompt.getUltimaExpresion().trim().compareTo("(exit)")==0){
				haSalido = true;
				return;
			}
			
			String ultimaExpresion = prompt.getUltimaExpresion().trim();
					
			try {
			} finally{

				try {
					Atomo atomoAEvaluar = interpreter.parsearExpresion(ultimaExpresion, false, false);
					System.out.println(interpreter.evaluar(atomoAEvaluar).toString());
				} finally {

                }
			}
		}
	}

}
