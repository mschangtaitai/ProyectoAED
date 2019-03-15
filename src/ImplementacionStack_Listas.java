
public class ImplementacionStack_Listas<E> implements InterfazStack<E> {
	public int contador;
	public Nodo<E> head;
	
	//inicializar nueva lista
	public ImplementacionStack_Listas(){
		contador = 0;
		head = null;
	}
	
	//Indica si hay elementos en la lista 
	public boolean empty() {
		if (contador==0)
			return true;
		else
			return false;
	}
	
	//Indica si el parqueo esta lleno --> 10 elementos en la lista 
	public boolean full() {
		if (contador==100)
			return true;
		else{
			return false;
		}
	}


//AGREGAR UN NUEVO ELEMENTO A LA LISTA
	public void push(Object element){
		
		Nodo<E> nuevoNodo = new Nodo<E> (element, null);
		contador++;
		//Si hay elementos en la lista
		if (head != null){
			//colocara el nuevo nodo al final de la list
			Nodo<E> finger = head;
			while (finger.next() != null){
				finger = finger.next();
			}
			finger.setNext(nuevoNodo);
		}
		else
			//d lo contrario, colocara el nuevo nodo al principio.
			head = nuevoNodo;
				
		}

//Devuelve el utlimo elemento en la lista y NO lo borra
	public E top() {
		Nodo<E> finger = head; 
		
		while (finger.next() != null){ // Encontrar el final de lista 
			finger = finger.next(); 
		} 

		return (E)finger.value();  
	}
	
//Devuelve el utlimo elemento en la lista y lo borra
	public E pop (){

		Nodo<E> finger = head; 
		Nodo<E> previous = null; 
		
		while (finger.next() != null){ // Encontrar el final de lista 
			previous = finger; 
			finger = finger.next(); 
		} 
		// finger is null, or points to end of list 
		if (previous == null) { 
		// has exactly one element 
			head = null; 
		} 
		else { 
		// pointer to last element is reset 
			previous.setNext(null); 
		} 
		contador--; 
		return (E)finger.value();  

	}

	//DEVUELVE elemento en la posicion dicha. 
	public E devolver(int pos) {
		if (pos>=contador)
			return (E) ("Dispo"+Integer.toString(pos+1));
		else{
			
			int recorrido=0;
			Nodo<E> finger = head; 
			while (recorrido<pos){ // Encontrar el final de lista 
				finger = finger.next(); 
				recorrido++;
			} 
			return (E)finger.value();  	
		}
	}
	
}