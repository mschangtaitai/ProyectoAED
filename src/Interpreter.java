import java.util.Hashtable;
import java.util.StringTokenizer;

public class Interpreter {
	Prompt prompt;
	Lista listaDeOperacionesImplementadas;
	Lista listaDePredicados;
	Hashtable<String,Atomo> variablesDelUsuario = new Hashtable<String,Atomo>();
	Hashtable<String,Atomo> funcionesDelUsuario = new Hashtable<String,Atomo>();

	public Interpreter(){
		definirOperacionesImplementadas();
		definirPredicados();
	}

	private void definirPredicados() {
		this.listaDePredicados = new Lista();
		this.listaDePredicados.AgregarAlFinal(new Atomo("="));
		this.listaDePredicados.AgregarAlFinal(new Atomo("/="));
		this.listaDePredicados.AgregarAlFinal(new Atomo("<"));
		this.listaDePredicados.AgregarAlFinal(new Atomo("<="));
		this.listaDePredicados.AgregarAlFinal(new Atomo(">"));
		this.listaDePredicados.AgregarAlFinal(new Atomo(">="));
	}


	private void definirOperacionesImplementadas() {

		this.listaDeOperacionesImplementadas = new Lista();


		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("car"));


		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cdr"));


		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cons"));


		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("defun"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("list-length"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("setq"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("+"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("*"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("-"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("/"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("equal"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("atom"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cond"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("list"));

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("/="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("<"));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("<="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo(">"));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo(">="));
	}

	public Atomo parsearExpresion(String expresionAParsear,boolean estaDefiniendoUnaLista,boolean esClausula){

		expresionAParsear = expresionAParsear.trim();

		if (expresionAParsear.compareTo("")==0)
			return new Atomo();

		if (!this.estaBienBalanceada(expresionAParsear))
			return new Atomo();

		Atomo atomoDeRespuesta = new Atomo();

		StringTokenizer separador = new StringTokenizer(expresionAParsear);
		
		String primerToken = separador.nextToken();
		Atomo atomoIngresado = new Atomo(primerToken);

		if ((separador.countTokens()==0)){

			if ((this.variablesDelUsuario.containsKey(primerToken)) && (!estaDefiniendoUnaLista))
				return this.variablesDelUsuario.get(primerToken);
			
			if ((primerToken.compareTo("'()")==0) || ((primerToken.compareTo("()")==0)))
				return new Atomo();

			if (primerToken.charAt(0)=='\''){
				if ((primerToken.charAt(1)!='(') && (primerToken.charAt(primerToken.length()-1)!=')'))
					return new Atomo(primerToken.substring(1));
			}
		}

		if ((new Atomo(primerToken).comienzaCon("'("))){
			estaDefiniendoUnaLista = true;
			atomoDeRespuesta = new Atomo(new Lista());
			atomoDeRespuesta.lista.esOperacion = false;

			int desdeDondeCortar = expresionAParsear.indexOf('(') + 1;
			int hastaDondeCortar = this.obtenerIndiceDelParentesisQueCierraAlPrimeroEn(expresionAParsear);

			if ((hastaDondeCortar==expresionAParsear.lastIndexOf(')')) && (!(hastaDondeCortar==expresionAParsear.length()-1)))
				return new Atomo();
			
			expresionAParsear = expresionAParsear.substring(desdeDondeCortar, hastaDondeCortar);			
		} else if (primerToken.charAt(0)=='('){

			int desdeDondeCortar = expresionAParsear.indexOf('(') + 1;
			int hastaDondeCortar = this.obtenerIndiceDelParentesisQueCierraAlPrimeroEn(expresionAParsear);
			
			expresionAParsear = expresionAParsear.substring(desdeDondeCortar,hastaDondeCortar);
			atomoDeRespuesta = new Atomo(new Lista());
			
			
			separador = new StringTokenizer(expresionAParsear);
			String primerTokenDeLaLista = separador.nextToken();
			
			boolean esFuncion = (esOperacionImplementada(primerTokenDeLaLista)) || this.funcionesDelUsuario.containsKey(primerTokenDeLaLista);
			
			if ((!estaDefiniendoUnaLista) && (!esFuncion)){

				return new Atomo();
			}	
			
			if (estaDefiniendoUnaLista){
				if (!esClausula)
					atomoDeRespuesta.lista.esOperacion = false;
				else
					atomoDeRespuesta.lista.esOperacion = true;
			}else {
				atomoDeRespuesta.lista.esOperacion = true;
			}
		}		

		if (!atomoDeRespuesta.EsLista())
			return new Atomo(expresionAParsear);
		
		separador = new StringTokenizer(expresionAParsear);
		
		while (separador.hasMoreTokens()) {
			Atomo atomoActual = new Atomo(separador.nextToken());

			if ((atomoActual.comienzaCon("'(")) || (atomoActual.comienzaCon("("))){

				int desdeDondeCortar = expresionAParsear.indexOf(atomoActual.toString());
				expresionAParsear = expresionAParsear.substring(desdeDondeCortar);
				int hastaDondeCortar = this.obtenerIndiceDelParentesisQueCierraAlPrimeroEn(expresionAParsear);
				String expresionDeLaListaInterna = expresionAParsear.substring(0, hastaDondeCortar+1);
				Atomo atomoConLaListaInterna = new Atomo();
				
				boolean esDefun = false;
				boolean esCOND = false;
				
				if (atomoActual.comienzaCon("'("))
					atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, true, esClausula);
				else {
					if (atomoDeRespuesta.lista.size() > 0){
						if (atomoDeRespuesta.EsListaConOperacion()){
							String operacionDeLaLista = atomoDeRespuesta.lista.getOperacion().toString();

							if ((operacionDeLaLista.compareToIgnoreCase("defun")==0) && (atomoDeRespuesta.lista.size()==2)){
								atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, true,false);
								esDefun = true;
							}

							if (operacionDeLaLista.compareToIgnoreCase("cond")==0){
								esCOND = true;
								atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, true,true);
							}
						}
					}
					
					if ((!esDefun) && (!esCOND))
						atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, estaDefiniendoUnaLista, esClausula);
					
				}
				
				atomoDeRespuesta.lista.AgregarAlFinal(atomoConLaListaInterna);
				String expresionDespuesDeLaListaInterna = expresionAParsear.substring(hastaDondeCortar + 1);
				expresionAParsear = expresionDespuesDeLaListaInterna;
				
				if (!esDefun){

					separador = new StringTokenizer(expresionDespuesDeLaListaInterna);
				} else {

					String nombreDeLaFuncion = atomoDeRespuesta.lista.getAtomoEn(1).toString();
					this.funcionesDelUsuario.put(nombreDeLaFuncion,new Atomo());
					
					Atomo atomoConLaOperacion = this.parsearExpresion(expresionAParsear.trim(), false,false);
					atomoDeRespuesta.lista.AgregarAlFinal(atomoConLaOperacion);
					separador = new StringTokenizer("");
					
					this.funcionesDelUsuario.remove(nombreDeLaFuncion);
				}
			} else{

				int desdeDondeCortar = expresionAParsear.indexOf(atomoActual.toString());
				expresionAParsear = expresionAParsear.substring(desdeDondeCortar);
				
				if (atomoActual.comienzaCon("'"))

					atomoActual = new Atomo(atomoActual.toString().substring(1));
					
				atomoDeRespuesta.lista.AgregarAlFinal(atomoActual);
			}
		}

		return atomoDeRespuesta;
	}

	private boolean esOperacionImplementada(String posibleOperacion) {
		return this.listaDeOperacionesImplementadas.existe(new Atomo(posibleOperacion));
	}

	public boolean estaBienBalanceada(String expresion) {
			int parentesisAbiertos = 0;
			int parentesisCerrados = 0;

			for (int i=0 ; i < expresion.length() ; i++){
				if (expresion.charAt(i) == '(')
					parentesisAbiertos++;
				else if (expresion.charAt(i) == ')')
					parentesisCerrados++;
			}
			
			return parentesisAbiertos == parentesisCerrados;
		}
 

	public Atomo evaluar(Atomo atomoAEvaluar){
		Atomo AtomoDeRespuesta = new Atomo();

		if (atomoAEvaluar.esNulo)
			return new Atomo();
		
		if (atomoAEvaluar.EsLista()){

			Lista listaAEvaluar = atomoAEvaluar.lista;
			
			if (listaAEvaluar.esOperacion){

				String operacionDeLaLista = listaAEvaluar.getOperacion().toString(); 
				if ((operacionDeLaLista.compareToIgnoreCase("defun")!=0) && (operacionDeLaLista.compareToIgnoreCase("cond")!=0)) 
					for (int i=0 ; i < listaAEvaluar.size() ; i++){
						if (listaAEvaluar.getAtomoEn(i).EsListaConOperacion()){
							Atomo listaEvaluada = this.evaluar(listaAEvaluar.getAtomoEn(i));
							listaAEvaluar.remplazarEn_Por(i, listaEvaluada);
						} else if (this.variablesDelUsuario.containsKey(listaAEvaluar.getAtomoEn(i).toString())){
							Atomo valorDelAtomo = this.variablesDelUsuario.get(listaAEvaluar.getAtomoEn(i).toString());						

							if (listaAEvaluar.esOperacion){
								if (!((i==1) && (listaAEvaluar.getOperacion().toString().compareTo("setq")==0)))
									listaAEvaluar.remplazarEn_Por(i, valorDelAtomo);
							} else 
								listaAEvaluar.remplazarEn_Por(i, valorDelAtomo);	
						}
					}								
			} else {
				return new Atomo(listaAEvaluar);
			}

			String operacion = listaAEvaluar.getOperacion().toString();
			if (this.esPredicado(operacion)){
				return this.evaluarPredicado(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("cdr")==0){
				return this.cdr(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("car")==0){
				return this.car(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("list-length")==0){
				return this.list_length(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("cons")==0){
				return this.cons(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("+")==0){
				return this.sumar(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("*")==0){
				return this.multiplicar(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("-")==0){
				return this.restar(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("/")==0){
				return this.dividir(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("defun")==0){
				return this.defun(listaAEvaluar);
			} else if (this.funcionesDelUsuario.containsKey(operacion)){
				return this.operarFuncionDelUsuario(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("equal")==0){
				return this.equal(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("atom")==0){
				return this.type_of(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("cond")==0){
				return this.cond(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("list")==0){
				return this.list(listaAEvaluar);
			}
		} else{

			if (this.variablesDelUsuario.containsKey(atomoAEvaluar.toString()))
				AtomoDeRespuesta = this.variablesDelUsuario.get(atomoAEvaluar.toString());
			else

				AtomoDeRespuesta = atomoAEvaluar;	
		}
		
		return AtomoDeRespuesta;
	}

	private Atomo list(Lista listaAEvaluar) {
		Lista lista = new Lista();
		
		for(int i = 1; i < listaAEvaluar.size(); i++) {
			lista.AgregarAlFinal(this.evaluar(listaAEvaluar.getAtomoEn(i)));
		}
		
		return new Atomo(lista);
	}

	private Atomo evaluarPredicado(Lista listaAEvaluar){
		Atomo atomoDeRespuesta = new Atomo();
		
		String predicadoAEvaluar = listaAEvaluar.getAtomoEn(0).toString();
		if (!this.esPredicado(predicadoAEvaluar))
            return new Atomo();
		if (predicadoAEvaluar.compareTo("=")==0){
			if (!(listaAEvaluar.size() > 1))
                return new Atomo();
			boolean sonIguales = true;
			for (int i = 1 ; i < listaAEvaluar.size()-1 ; i++){
				sonIguales = listaAEvaluar.getAtomoEn(i).equals(listaAEvaluar.getAtomoEn(i+1));
				
				if (!sonIguales)
					return new Atomo(sonIguales);
					
			}

			return new Atomo(sonIguales);
		} else if (predicadoAEvaluar.compareTo("/=")==0){
			if (!(listaAEvaluar.size() > 1))
				return new Atomo();
			
			boolean sonDiferentes = true;
			
			for (int i = 1 ; i < listaAEvaluar.size()-1 ; i++){
				sonDiferentes = !listaAEvaluar.getAtomoEn(i).equals(listaAEvaluar.getAtomoEn(i+1));
				
				if (!sonDiferentes)
					return new Atomo(false);
			}
			return new Atomo(sonDiferentes);
		} else if (predicadoAEvaluar.compareTo("<")==0){
			if (!(listaAEvaluar.size() > 1))
				return new Atomo();
			
			boolean esMenorQue = true;
			
			for (int i = 1; i <listaAEvaluar.size()-1 ; i++){
				
				if (!(listaAEvaluar.getAtomoEn(i).esNumero()))
					return new Atomo();
				
				esMenorQue = listaAEvaluar.getAtomoEn(i).getNumero() < listaAEvaluar.getAtomoEn(i+1).getNumero();
				
				if (!esMenorQue)
					return new Atomo (false);
			}
			
			return new Atomo(esMenorQue);
		} else if (predicadoAEvaluar.compareTo("<=")==0){
			if (!(listaAEvaluar.size() > 1))
				return new Atomo();
			
			boolean esMenorIgualQue = true;
			
			for (int i = 1; i <listaAEvaluar.size()-1 ; i++){
				if (!(listaAEvaluar.getAtomoEn(i).esNumero()))
                    return new Atomo();
				
				esMenorIgualQue = listaAEvaluar.getAtomoEn(i).getNumero() <= listaAEvaluar.getAtomoEn(i+1).getNumero();
				if (!esMenorIgualQue)
					return new Atomo (false);
			}
			return new Atomo(esMenorIgualQue);
		} else if (predicadoAEvaluar.compareTo(">")==0){
			if (!(listaAEvaluar.size() > 1))
                return new Atomo();
			
			boolean esMayorQue = true;
			
			for (int i = 1; i <listaAEvaluar.size()-1 ; i++){
				
				if (!(listaAEvaluar.getAtomoEn(i).esNumero()))
                    return new Atomo();
				
				esMayorQue = listaAEvaluar.getAtomoEn(i).getNumero() > listaAEvaluar.getAtomoEn(i+1).getNumero();
				
				if (!esMayorQue)
					return new Atomo (false);
			}
			
			return new Atomo(esMayorQue);
		} else if (predicadoAEvaluar.compareTo(">=")==0){
			if (!(listaAEvaluar.size() > 1))
                return new Atomo();
			
			boolean esMayorIgualQue = true;
			
			for (int i = 1; i <listaAEvaluar.size()-1 ; i++){
				if (!(listaAEvaluar.getAtomoEn(i).esNumero()))
                    return new Atomo();
				
				esMayorIgualQue = listaAEvaluar.getAtomoEn(i).getNumero() >= listaAEvaluar.getAtomoEn(i+1).getNumero();
				if (!esMayorIgualQue)
					return new Atomo (false);
			}
			return new Atomo(esMayorIgualQue);
		}

        return new Atomo();
	}

	private boolean esPredicado(String operacion) {	
		return this.listaDePredicados.existe(new Atomo(operacion));
	}


	private Atomo cond(Lista listaAEvaluar){
		if (listaAEvaluar.size()<2)
			return new Atomo();

		int revisandoSubListaIndex = 1;
		boolean haEncontradoRespuesta = false;
		Atomo atomoDeRespuesta = new Atomo();
		
		while ((revisandoSubListaIndex < listaAEvaluar.size()) && (!haEncontradoRespuesta)){

			if (!listaAEvaluar.getAtomoEn(revisandoSubListaIndex).EsLista()) 
				return new Atomo();
			else {
				/*
				 * Revisar que no sea una lista NIL
				 */
				
			}
			
			Lista evaluandoSubLista = listaAEvaluar.getAtomoEn(revisandoSubListaIndex).lista;

			Atomo primerAtomo = evaluandoSubLista.getAtomoEn(0);

			if (!((atomoDeRespuesta.esNulo) && (revisandoSubListaIndex==listaAEvaluar.size()-1))) {
				if ((!primerAtomo.EsLista()) && (!this.variablesDelUsuario.containsKey(primerAtomo.toString())))
					return new Atomo();
			}
			
			Atomo primerAtomoEvaluado = this.evaluar(primerAtomo);

			if (!primerAtomoEvaluado.esNulo){
				haEncontradoRespuesta = true;
					
				if (evaluandoSubLista.size() > 1)

					atomoDeRespuesta = this.evaluar(evaluandoSubLista.getAtomoEn(evaluandoSubLista.size()-1));
				else

					atomoDeRespuesta = primerAtomoEvaluado; 
			} 
			
			revisandoSubListaIndex ++;
		}
		
		
		return atomoDeRespuesta;
	}

	private Atomo type_of(Lista listaAEvaluar){
		if (listaAEvaluar.size()!=2)
			return new Atomo();
		
		return new Atomo(listaAEvaluar.getAtomoEn(1).getTipo());
	}

	private Atomo equal(Lista listaAEvaluar){

		if (listaAEvaluar.size()!=3)
			return new Atomo();

		return new Atomo(listaAEvaluar.getAtomoEn(1).toString().compareTo(listaAEvaluar.getAtomoEn(2).toString())==0);
	}


	private Atomo operarFuncionDelUsuario(Lista listaAEvaluar){

		int numeroDeParametrosQueIngreso = listaAEvaluar.size()-1;
		
		Lista listaDeLosParametros = this.funcionesDelUsuario.get(listaAEvaluar.getOperacion().toString()).lista.getAtomoEn(0).lista;
		
		int numeroDeParametrosDeLaFuncion = listaDeLosParametros.size();
		
		if (numeroDeParametrosDeLaFuncion!=numeroDeParametrosQueIngreso)
			return new Atomo();

		Hashtable<String,Atomo> mapaDeParametrosYValores = new Hashtable<String,Atomo>();
		
		for (int i =1 ; i <= numeroDeParametrosQueIngreso ; i++){
			mapaDeParametrosYValores.put(listaDeLosParametros.getAtomoEn(i-1).toString(), listaAEvaluar.getAtomoEn(i));
		}

		Atomo atomoDeLaOperacion = this.funcionesDelUsuario.get(listaAEvaluar.getOperacion().toString()).lista.getAtomoEn(1);
		
		if (atomoDeLaOperacion.EsLista()){
			Atomo listaAEvaluarConParametrosMapeados = this.mapearParametrosEn(mapaDeParametrosYValores, new Atomo(atomoDeLaOperacion.lista)); 
			return this.evaluar(listaAEvaluarConParametrosMapeados);
		} else
			return atomoDeLaOperacion;
		
	}


	private Atomo mapearParametrosEn(Hashtable<String, Atomo> mapaDeParametrosYValores, Atomo atomoEnDondeMapear){

		Lista listaAEvaluar = new Lista(atomoEnDondeMapear.lista);
		listaAEvaluar.esOperacion = atomoEnDondeMapear.lista.esOperacion;
		

		for (int i= 0 ; i < listaAEvaluar.size(); i++){
			Atomo atomoActual = listaAEvaluar.getAtomoEn(i);
			
			if (atomoActual.EsLista()){
				listaAEvaluar.remplazarEn_Por(i,this.mapearParametrosEn(mapaDeParametrosYValores, atomoActual));
			} else{
				if (mapaDeParametrosYValores.containsKey(atomoActual.toString()))
					listaAEvaluar.remplazarEn_Por(i,mapaDeParametrosYValores.get(atomoActual.toString()));
			}
		}
		
		return new Atomo(listaAEvaluar);
	}


	private Atomo defun(Lista listaAEvaluar){

		if (listaAEvaluar.size()!=4)
			return new Atomo();
		else{
			if (listaAEvaluar.getAtomoEn(1).EsLista())
				return new Atomo();

			if (!listaAEvaluar.getAtomoEn(2).EsLista())
				return new Atomo();
			
			
		}
		String nombreDeLaFuncion = listaAEvaluar.getAtomoEn(1).toString();
		if (!esNombreDeFuncionValida(nombreDeLaFuncion))
			return new Atomo();

		Atomo atomoDeLaFuncion = new Atomo(new Lista(listaAEvaluar.subList(2, 4)));
		this.funcionesDelUsuario.put(nombreDeLaFuncion, new Atomo(new Lista(listaAEvaluar.subList(2, 4))));
		
		return new Atomo(nombreDeLaFuncion);
	}

	private boolean esNombreDeFuncionValida(String nombreDeLaFuncion){

		if (esOperacionImplementada(nombreDeLaFuncion))
			return false;

		String primerCaracter = Character.toString(nombreDeLaFuncion.charAt(0)); 
		try {
			int posibleNumero = Integer.parseInt(primerCaracter);
			return false;
		} catch(NumberFormatException noEmpiezaConNumero){
			return true;
		}
	}

	private Atomo dividir(Lista listaAEvaluar) {
		float division;
		if (listaAEvaluar.getAtomoEn(1).esNumero())
			division = listaAEvaluar.getAtomoEn(1).getNumero();
		else
			return new Atomo();
		
		for (int i=2; i < listaAEvaluar.size() ; i++){
			if (listaAEvaluar.getAtomoEn(i).esNumero()){
			
				division /= listaAEvaluar.getAtomoEn(i).getNumero();
			} else
			    return new Atomo();
		}

		return new Atomo(division);
	}
	

	private Atomo restar(Lista listaAEvaluar){
		float resta;
		if (listaAEvaluar.getAtomoEn(1).esNumero())
			resta = listaAEvaluar.getAtomoEn(1).getNumero();
		else
			return new Atomo();

		boolean todosSonEnteros = true;
		
		for (int i=2; i < listaAEvaluar.size() ; i++){
			if (listaAEvaluar.getAtomoEn(i).esNumero()){
				if (!listaAEvaluar.getAtomoEn(i).esEntero())
					todosSonEnteros = false;
				
				resta -= listaAEvaluar.getAtomoEn(i).getNumero();
			} else
				return new Atomo();
		}
		
		if (todosSonEnteros) 
			return new Atomo((int) resta);
		else 
			return new Atomo(resta);
	}

	private Atomo sumar(Lista listaAEvaluar) {
		float suma = 0;
		boolean todosSonEnteros = true;
		
		for (int i=1; i < listaAEvaluar.size() ; i++){
			if (listaAEvaluar.getAtomoEn(i).esNumero()){
				if (!listaAEvaluar.getAtomoEn(i).esEntero())
					todosSonEnteros = false;
				
				suma += listaAEvaluar.getAtomoEn(i).getNumero();
			} else
                return new Atomo();
		}
		
		if (todosSonEnteros) 
			return new Atomo((int) suma);
		else 
			return new Atomo(suma);
	}

	private Atomo multiplicar(Lista listaAEvaluar){
		float Multiplicacion = 1;
		boolean todosSonEnteros = true;
		
		for (int i=1; i < listaAEvaluar.size() ; i++){
			if (listaAEvaluar.getAtomoEn(i).esNumero()){
				if (!listaAEvaluar.getAtomoEn(i).esEntero())
					todosSonEnteros = false;
			
				Multiplicacion *= listaAEvaluar.getAtomoEn(i).getNumero();
			} else
                return new Atomo();
		}
		
		if (todosSonEnteros)
			return new Atomo((int) Multiplicacion);
		else
			return new Atomo(Multiplicacion);
	}

	public Atomo cons(Lista listaAEvaluar){
		if (listaAEvaluar.size() != 3)
            return new Atomo();
		
		Lista listaConstruida = new Lista();
		listaConstruida.AgregarAlFinal(listaAEvaluar.getAtomoEn(1));

		if (listaAEvaluar.getAtomoEn(2).EsLista()){
			for (int i = 0; i < listaAEvaluar.getAtomoEn(2).lista.size(); i++){
				if (!listaAEvaluar.getAtomoEn(2).lista.getAtomoEn(i).esNulo)
					listaConstruida.AgregarAlFinal(listaAEvaluar.getAtomoEn(2).lista.getAtomoEn(i));
			}
		} else
			listaConstruida.AgregarAlFinal(listaAEvaluar.getAtomoEn(2));
		
		return new Atomo(listaConstruida);
	}

	public Atomo setq(Lista listaAEvaluar) {
		
		if ((listaAEvaluar.size() != 3))
            return new Atomo();

		if (((Atomo)listaAEvaluar.get(1)).EsLista())
            return new Atomo();

		String variableDeAsignacion = ((Atomo) listaAEvaluar.get(1)).toString(); 
		Atomo atomoAsignado = ((Atomo) listaAEvaluar.get(2));

		atomoAsignado = this.evaluar(atomoAsignado);

		this.variablesDelUsuario.put(variableDeAsignacion, atomoAsignado);
		return atomoAsignado;
	}


	public Atomo list_length(Lista listaAEvaluar) {
		
		if (!this.operandoEsLista(listaAEvaluar))
            return new Atomo();
		
		Atomo listaOperanda = this.evaluar(listaAEvaluar.getAtomoEn(1));
		return new Atomo(listaOperanda.lista.size());
	}


	public Atomo car(Lista listaAEvaluar){
		if (!this.operandoEsLista(listaAEvaluar))
            return new Atomo();
		
		Lista listaOperanda = this.evaluar(listaAEvaluar.getAtomoEn(1)).lista;
		
		if (listaOperanda.estaVacia())
			return new Atomo();
		
		Atomo primerAtomo = listaOperanda.getAtomoEn(0);
		
		if (primerAtomo.EsListaConOperacion())
			primerAtomo = this.evaluar(primerAtomo);
		
		return primerAtomo;
	}


	public Atomo cdr(Lista listaAEvaluar){
		
		if (!this.operandoEsLista(listaAEvaluar))
            return new Atomo();
		
		Lista listaOperanda = this.evaluar(listaAEvaluar.getAtomoEn(1)).lista;
		
		if (listaOperanda.size()<2)
			return new Atomo();
		
		Lista subLista = new Lista(listaOperanda.subList(1, listaOperanda.size()));
		
		return new Atomo(subLista);
	}

	public boolean operandoEsLista(Lista listaAEvaluar){
		if (!listaAEvaluar.esOperacion)
			return false;
		
		if (listaAEvaluar.size()>2)
			return false;

		if (this.variablesDelUsuario.containsKey(listaAEvaluar.getAtomoEn(1).toString()))
			return true;
			
		if (!listaAEvaluar.getAtomoEn(1).EsLista())
			return false;
		
		return listaAEvaluar.esOperacion;
	}
	
	

	public int obtenerIndiceDelParentesisQueCierraAlPrimeroEn(String expresion){
		expresion = expresion.trim();
		
		if (!((new Atomo(expresion).comienzaCon("'("))  ||  (new Atomo(expresion).comienzaCon("("))))
			return 0;


		int indice = expresion.indexOf('(')+1;
		int numeroDeParentesisAbiertosPorCerrar = 1;
		
		while (indice < expresion.length()){
			if (expresion.charAt(indice)=='('){
				numeroDeParentesisAbiertosPorCerrar++;
			} else if (expresion.charAt(indice)==')'){
				numeroDeParentesisAbiertosPorCerrar--;
				if (numeroDeParentesisAbiertosPorCerrar==0)
					return indice;
			}
			indice ++;
		}

		return 0;
	}
}
