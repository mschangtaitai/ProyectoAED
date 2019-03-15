

import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Prompt {
	private Scanner entrada;
	private String ultimaExpresion;
	private static int numeroDeLinea=1;
	

	public Prompt(){
		this.entrada = new Scanner(System.in);
	}

	public void Escuchar(Hashtable<String, Atomo> variablesDelUsuario) {

		System.out.print(numeroDeLinea+">>> ");
		
		String nuevaExpresion = "";
		boolean haTerminadoDeLeer = false;
		int numeroDeParentesisAbiertos = 0;
		int numeroDeParentesisCerrados = 0;
		
		do{
			nuevaExpresion += " "+entrada.nextLine().replace('\n', ' ');
			
			if (variablesDelUsuario.containsKey(nuevaExpresion.trim())){
				haTerminadoDeLeer = true;
			}
				
			numeroDeParentesisAbiertos = contarCaracterEn('(',nuevaExpresion);
			numeroDeParentesisCerrados = contarCaracterEn(')',nuevaExpresion);
			
			if ((numeroDeParentesisAbiertos == numeroDeParentesisCerrados) && (numeroDeParentesisAbiertos !=0))
				haTerminadoDeLeer = true;
			
		} while (!haTerminadoDeLeer);
		
		this.ultimaExpresion = nuevaExpresion;
		this.numeroDeLinea++;
	}

	private int contarCaracterEn(char caracterBuscando, String cadena) {
		int cuantasVeces = 0;

		for (int i = 0 ; i < cadena.length() ; i++){
			if (cadena.charAt(i)==caracterBuscando)
				cuantasVeces++;
		}
		
		return cuantasVeces;
	}


	public String getUltimaExpresion() {
		return this.ultimaExpresion;
	}
}
