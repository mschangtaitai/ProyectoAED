public class Nodo <E> {

	private Object Elemento; 
	private Nodo<E> SiguienteElemento; 
	
	//Nuevo nodo
	public Nodo(Object elemento, Nodo<E> next) {
		Elemento = elemento; 
		SiguienteElemento = next; 
	} 
		
	//Devuelve el siguiente elemento en la lista
	public Nodo<E> next() { 
		return SiguienteElemento; 
	} 
	
	//Establece el siguiente elemento. 
	public void setNext(Nodo<E> next){
		SiguienteElemento = next; 
	} 
	
	//Devuelve el valor asociado con este elemento.
	public Object value() { 
		return Elemento; 
	} 
	
	
}

