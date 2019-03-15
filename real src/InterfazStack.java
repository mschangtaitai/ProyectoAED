/*Interfaz para el uso de los Stacks
 */

/**
   * @author chitiore
   *
   */
public interface InterfazStack<E> {
	
	public boolean empty ();
	
	public boolean full ();
	
	public E devolver(int pos);
		
	public void push (E element);
	
	public E pop ();

	public E top ();
	}
