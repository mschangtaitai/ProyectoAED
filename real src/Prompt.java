/**
 * GtLisp
 * 
 * Para el curso CC2003 - Algoritmos y Estructura de Datos en UVG (Universidad del Valle de Guatemala, http://uvg.edu.gt)
 * Una peque�a implementaci�n de Lisp en Java
 * 
 * Mas informacion en: http://sourceforge.net/projects/gtlisp
 * 
 * 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/> or write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Prompt {
	private Scanner entrada;
	private String ultimaExpresion;
	private static int numeroDeLinea=1;
	
	/*
	 * Constructor del prompt
	 */
	public Prompt(){
		this.entrada = new Scanner(System.in);
	}
	
	/*
	 * M�todo que "escucha" (espera) algo para ser le�do y entendido
	 * 
	 */
	public void Escuchar(Hashtable<String, Atomo> variablesDelUsuario) {
		/*
		 * Hacerle saber al usuario que GtLisp est� listo para procesar algo
		 * ingresando el n�mero de l�nea que corresponde
		 */
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
		
		//System.out.println(cadena.length());
		
		for (int i = 0 ; i < cadena.length() ; i++){
			if (cadena.charAt(i)==caracterBuscando)
				cuantasVeces++;
		}
		
		return cuantasVeces;
	}

	public void EscribirBienvenida() {

		System.out.println("*******************************************************************************" +
				   	"\n-------------------------------------------------------------------------------\n" +
				   	"\n     MMMMMMMMMMMM      .MM     .MMMMM        ..MMM.                         	  " +     
				   	"\n  .MMMMMMMMMMMMMM     .MMM  .  .MMMMM        . MMM     .  .          .. .      " +  
				   	"\n .MMMMMMM.     MM . .MMMMMMMM  .MMMMM         MMMMM  .. MMMMM..  MMMMMMMMM$.   " +   
				   	"\n .MMMMM..  .       MMMMMMMMMM  .MMMMM         MMMMM  .MMMMMMMM   MMMMMMMMMMMM  " +  
				   	"\n .MMMMM   .MMMMMM.   MMMMM     .MMMMM         MMMMM  7MMMM ....  MMMMM..MMMMM$   " +
				   	"\n  MMMMM . .MMMMMM.   MMMMM     .MMMMM         MMMMM  .MMMMMMMI   MMMMM.  MMMMM   " +
				   	"\n .MMMMMMN  . MMMM.  .MMMMM...  .MMMMM      .  MMMMM   .  MMMMMM  MMMMM...MMMMM.  " +
				   	"\n   MMMMMMMMMMMMMM.   MMMMMMMMM..MMMMMMMMMMMM  MMMMM  .MMMMMMMMM  MMMMMMMMMMMM    " +
				   	"\n    .MMMMMMMMMMMM    .MMMMMMMM. MMMMMMMMMMMM  MMMMM  .MMMMMMMM   MMMMMMMMMMM     " +
				   	"\n       .  .. ..            .    .            .     .   ...       MMMMM   .       " +
				   	"\n                                                                 MMMMM.          " +
				   	"\n                                                                 MMMMM.      " +
		
				   	"\n\nEn fase de desarollo por: Carlos Lopez, Byron Morales y Josue Rendon (UVG, 2009)" +
				   	"\n Para m�s informaci�n, visite http://sourceforge.net/projects/gtlisp/"			+
				   	"\n-------------------------------------------------------------------------------" +
					"\n*******************************************************************************\n");
	}

	public String getUltimaExpresion() {
		return this.ultimaExpresion;
	}
}
