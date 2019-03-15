import java.util.ArrayList;
import java.util.List;

public class Lista extends ArrayList implements List{
	public boolean esOperacion;

	public Lista() {
		super();
	}

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
