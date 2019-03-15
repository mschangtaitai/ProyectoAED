/*Autores:
 *Michael Chan 18562
 *Diego Estrada 18540
 *Isabel Ortiz 18176
 *
 * Proyecto 1: Interprete Lisp en Java
 */

public interface Stack<E> {

    public boolean empty ();

    public boolean full ();

    public E getByIndex(int pos);

    public void push (E element);

    public E pop ();

    public E top ();


}
