import java.util.ArrayList;
import java.util.List;

/*Autores:
 *Michael Chan 18562
 *Diego Estrada 18540
 *Isabel Ortiz 18176
 *
 * Proyecto 1: Interprete Lisp en Java
 */

public class Lista extends ArrayList implements List {

    public Lista() {
        super();
    }

    public Lista(List list) {
        super();

        if (list == null) {
            list = new Lista();
        }

        int i = 0;

        while (i < list.size()) {
            this.add(list.get(i));
            i++;
        }
    }
}