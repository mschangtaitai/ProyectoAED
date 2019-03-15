/*Autores:
 *Michael Chan 18562
 *Diego Estrada 18540
 *Isabel Ortiz 18176
 *
 * Proyecto 1: Interprete Lisp en Java
 */

public class Atom {
    private boolean isList;
    private boolean isInt;
    public boolean isNull;

    public Lista list;
    public String description;
    private String atom;

    private float integer;
    public boolean booleanVal;
    private boolean isBoolean;

    public Atom() {
        this.isNull = true;
        this.isList = false;
        this.isInt = false;
    }

    public Atom(Lista lista) {
        this.isList = true;
        this.isNull = false;
        this.isInt = false;

        this.list = lista;
    }

    public void copyAtom(Atom selectedAtom) {
        this.isList = selectedAtom.isList;
        this.isInt = selectedAtom.isInt;
        this.list = selectedAtom.list;
        this.atom = selectedAtom.atom;
        this.description = selectedAtom.description;
        this.integer = selectedAtom.integer;
        this.isNull = selectedAtom.isNull;
    }

    public Atom(String selectedAtom) {
        try {
            Atom intAtom = new Atom(Integer.parseInt(selectedAtom));
            this.copyAtom(intAtom);
            this.isInt = true;
        } catch (NumberFormatException notIntegerAtom) {
            try {
                Atom intAtom = new Atom(Float.parseFloat(selectedAtom));
                this.copyAtom(intAtom);
                this.isInt = true;
            } catch (NumberFormatException notFloatingAtom) {
                this.atom = selectedAtom;
                this.isInt = false;
                this.isNull = false;
                this.isList = false;
            }

        }
    }

    public Atom(String selectedAtom, String description) {
        this.atom = selectedAtom;
        this.description = description;
    }

    public Atom(int numero) {
        this.integer = numero;
        this.isInt = true;
        this.isNull = false;
        this.isList = false;
        this.atom = Integer.toString(numero);
    }

    public Atom(float number) {
        this.integer = number;
        this.isInt = true;
        this.isNull = false;
        this.isList = false;

        this.atom = Float.toString(this.integer);
    }

    public Atom(boolean isTrue) {
        if (isTrue) {
            this.isBoolean = true;
            this.booleanVal = true;
            this.isInt = false;
            this.isNull = false;
            this.isList = false;
            this.atom = "T";
        } else {
            this.isNull = true;
            this.isList = false;
            this.isInt = false;
        }
    }
}
