import java.util.ArrayList;
import java.util.List;

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
public class Lista extends ArrayList implements List{
	public boolean esOperacion;
	
	/**
	 * Constructor de la lista
	 * Usa el contructor de ArrayList()
	 */
	public Lista() {
		super();
	}
	
	/**
	 * Constructor a partir de una lista
	 * @param lista lista a copiar
	 */
	public Lista(List lista){
		super();
		
		if (lista == null){
			lista = new Lista();
		}
		
		int i = 0;
		
		while (i < lista.size()){
			this.add(lista.get(i));
			i++;
		}
	}
	
	public Lista(Atomo atomoIngresado) {
		super();
		
		this.add(atomoIngresado);
	}

	public boolean estaVacia(){
		return this.size()==0;
	}
	
	public void AgregarAlFinal(Atomo atomo){
			this.add(atomo);
	}
	
	public void AgregarEn(int indice, Atomo atomo){
		this.add(indice, atomo);
	}
	
	public boolean existe(Atomo atomo){
		int i = 0;
		
		while (i <= this.size() -1){
			if (this.get(i).equals(atomo))
				return true;
			i++;
		}
		
		return false;
	}
	

	public Atomo getOperacion(){
		if (!this.esOperacion)
			return new Atomo();
		
		return (Atomo)this.get(0);
	}

	public Atomo getAtomoEn(int i) {
		return (Atomo) this.get(i);
	}
	
	private Atomo removeAtomoEn(int i) {
		return (Atomo) this.remove(i);
	}
	
	/**
	 * Metodo que verifica si dos listas son iguales
	 * @param objetoLista Lista, instanciada como Objeto
	 * @return true, si las dos listas tienen los mismos elementos
	 */
	public boolean equals(Object objetoLista){
		Lista otraLista = (Lista)objetoLista;
		
		if (this.size()!=otraLista.size())
			return false;
		
		int indice = 0;
		while (indice < this.size()){
			if (!this.get(indice).equals(otraLista.get(indice)))
				return false;
		}
		
		return true;
	}

	public Atomo remplazarEn_Por(int i, Atomo atomoRemplazante) {
		Atomo atomoRemplazado = this.removeAtomoEn(i);
		this.AgregarEn(i, atomoRemplazante);
		
		return atomoRemplazado;
	}
}
