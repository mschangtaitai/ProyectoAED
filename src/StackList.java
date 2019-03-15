/*Autores:
 *Michael Chan 18562
 *Diego Estrada 18540
 *Isabel Ortiz 18176
 *
 * Proyecto 1: Interprete Lisp en Java
 */

public class StackList<E> implements Stack<E> {

    //Atributos
    public Node<E> head;
    public int counter;
    public static final int MAX_CAPACITY = 100;

    //Constructor
    public StackList(){
        counter = 0;
        head = null;
    }

    public boolean empty() {//Funciòn que verifica si està vacìo, devuelve booleano
        if (counter==0)
            return true;
        else
            return false;
    }

    public boolean full() {//Funcion que verifica si se encuentra lleno, devuelve booleano
        if (counter==MAX_CAPACITY)
            return true;
        else{
            return false;
        }
    }

    public void push(Object object){//Funcion push, introduce un elemento al stack, Last In First Out (LIFO).
        Node<E> myNode = new Node<E> (object, null);
        counter = counter + 1;
        if (head != null){//Si no esta vacio, lo introduce
            Node<E> nodeStack = head;
            while (nodeStack.next() != null){
                nodeStack = nodeStack.next();
            }
            nodeStack.setNext(myNode);
        }
        //Coloca en el incio
        else
            head = myNode;
    }

    public E pop (){//Elimina el elemento en el stack
        Node<E> nodeStack = head;
        Node<E> previous = null;
        while (nodeStack.next() != null){
            previous = nodeStack;
            nodeStack = nodeStack.next();
        }
        if (previous == null) {
            head = null;
            //solo cuenta con un elemento
        }
        else {
            previous.setNext(null);
            //ultimo elemento de stack
        }
        counter = counter - 1;
        return (E)nodeStack.value();
    }



    public E top() {//Funcion que muestra el nodo de arriba en el Stack
        Node<E> nodeStack = head;
        while (nodeStack.next() != null){
            nodeStack = nodeStack.next();
        }
        return (E)nodeStack.value();
    }



    public E getByIndex(int index) {//DEVOLVER el elemento por posicion
        if (index>=counter)
            return (E) ("Dispo"+Integer.toString(index+1));
        else{
            int count=0;
            Node<E> nodeStack = head;
            while (count<index){
                nodeStack = nodeStack.next();
                count = count +1;
            }
            return (E)nodeStack.value();
        }
    }

}