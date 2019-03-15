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

/**
 * @author kmels
 *
 */
public class Main {
	static GtLisp gtLisp;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		gtLisp = new GtLisp();
		
		/**
		 * Instanciar variables y dar la bienvenida
		 */
		Prompt prompt = new Prompt();
		prompt.EscribirBienvenida();
		
		boolean haSalido = false;
		while (!haSalido){
			prompt.Escuchar(gtLisp.variablesDelUsuario);
			
			/*
			 * Revisar si sali� de GtLisp
			 */
			if (prompt.getUltimaExpresion().trim().compareTo("(exit)")==0){
				haSalido = true;
				return;
			}
			
			String ultimaExpresion = prompt.getUltimaExpresion().trim();
					
			try {
				/**
				 * Tratar de interpretar la entrada del usuario como ayuda
				 */
				
			} finally{
				/**
				 * Si no obtuvo ayuda, tratar de parsear
				 */
				try {
					Atomo atomoAEvaluar = gtLisp.parsearExpresion(ultimaExpresion, false, false);
					System.out.println(gtLisp.evaluar(atomoAEvaluar).toString());
				} finally {

                }
			}
		}
	}

}
