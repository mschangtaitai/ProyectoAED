/*Autores:
 *Michael Chan 18562
 *Diego Estrada 18540
 *Isabel Ortiz 18176
 *
 * Proyecto 1: Interprete Lisp en Java
 */

public class Node <E> {

    private Object Element;
    private Node<E> NextE;

    // Se crea un nuevo nodo

    public Node(Object element, Node<E> next) {
        Element = element;
        NextE = next;
    }

    // Retorna el elemento siguiente de la lista

    public Node<E> next() {
        return NextE;
    }

    // Determina el siguiente elemento

    public void setNext(Node<E> next){
        NextE = next;
    }

    // Retorna el valor asociado con el elemento

    public Object value() {
        return Element;
    }


}