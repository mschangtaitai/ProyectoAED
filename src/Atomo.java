import java.util.List;

public class Atomo {
    private boolean esLista;
    private boolean esNumero;
    public boolean esNulo;

    public Lista lista;
    public String descripcion;
    private String atomo;

    private float numero;
    public boolean valorBooleano;
    private boolean esBooleano;


    public Atomo() {
        this.esNulo = true;
        this.esLista = false;
        this.esNumero = false;
    }

    public Atomo(Lista lista){
        this.esLista = true;
        this.esNulo = false;
        this.esNumero = false;

        this.lista = lista;
    }

    public void copiarAtomo(Atomo atomoAcopiar){
        this.esLista = atomoAcopiar.esLista;
        this.esNumero = atomoAcopiar.esNumero;
        this.lista = atomoAcopiar.lista;
        this.atomo = atomoAcopiar.atomo;
        this.descripcion = atomoAcopiar.descripcion;
        this.numero = atomoAcopiar.numero;
        this.esNulo = atomoAcopiar.esNulo;
    }

    public Atomo(String atomo) {

        try {
            Atomo atomoConUnNumero = new Atomo(Integer.parseInt(atomo));
            this.copiarAtomo(atomoConUnNumero);
            this.esNumero = true;
        } catch (NumberFormatException atomoNoEsNumero){
            try {
                Atomo atomoConUnNumero = new Atomo(Float.parseFloat(atomo));
                this.copiarAtomo(atomoConUnNumero);
                this.esNumero = true;
            } catch (NumberFormatException atomoNoEsFlotante){
                this.atomo = atomo;
                this.esNumero = false;
                this.esNulo = false;
                this.esLista = false;
            }

        }
    }


    public Atomo(int numero) {
        this.numero = numero;
        this.esNumero = true;
        this.esNulo = false;
        this.esLista = false;
        this.atomo = Integer.toString(numero);
    }

    public Atomo(float numero) {
        this.numero = numero;
        this.esNumero = true;
        this.esNulo = false;
        this.esLista = false;

        this.atomo = Float.toString(this.numero);
    }

    public Atomo(boolean esTrue) {
        if (esTrue){
            this.esBooleano = true;
            this.valorBooleano = true;
            this.esNumero = false;
            this.esNulo = false;
            this.esLista = false;
            this.atomo = "T";
        } else {
            this.esNulo = true;
            this.esLista = false;
            this.esNumero = false;
        }
    }

    public float getNumero(){
        if (!this.esNumero)
            return 0;

        return this.numero;
    }

    public boolean EsLista(){
        return this.esLista;
    }

    public String toString(){
        if (this.esNulo)
            return "NIL";

        if (this.EsLista())
            return this.lista.toString();
        else
            return this.atomo;
    }

    public boolean equals(Object objeto){
        Atomo otroAtomo = (Atomo)objeto;

        if ((this.esNulo) && (otroAtomo.esNulo))
            return true;

        if ((this.esNumero) && (otroAtomo.esNumero))
            return this.numero==otroAtomo.numero;

        if ((this.esLista) && (otroAtomo.esLista))
            return this.lista.equals(otroAtomo.lista);

        return this.atomo.compareTo(otroAtomo.atomo)==0;
    }

    public boolean comienzaCon(String substring){
        if ((substring.length()<=this.atomo.length()) && (!this.esLista)){
            return this.atomo.substring(0, substring.length()).compareTo(substring)==0;
        }

        return false;
    }

    public boolean EsListaConOperacion() {
        if (this.EsLista())
            return (this.lista.esOperacion);

        return false;
    }

    public boolean esNumero(){
        return this.esNumero;
    }

    public boolean esEntero() {
        if (!this.esNumero)
            return false;

        try {
            int numero = Integer.parseInt(this.atomo);
            return true;
        } catch (NumberFormatException noEsEntero) {
            return false;
        }
    }


    public String getTipo() {
        if (this.esLista)
            return "Nil";
        else if (this.esNumero)
            return "T";
        else if (this.esNulo)
            return "NULL";
        else
            return "T";

    }
}
