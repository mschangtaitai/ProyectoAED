import java.util.Hashtable;
import java.util.StringTokenizer;

public class GtLisp {
	Prompt prompt;
	Lista listaDeOperacionesImplementadas;
	Lista listaDePredicados;
	Hashtable<String,Atomo> variablesDelUsuario = new Hashtable<String,Atomo>();
	Hashtable<String,Atomo> funcionesDelUsuario = new Hashtable<String,Atomo>();

	public GtLisp(){
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
		/*
		 * Ordenadas alfabeticamente
		 */
		String textoDeAyuda = "";
		
		//textoDeAyuda = "";
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("atom"));
		
		textoDeAyuda = "car <lista>\n" +
				"Retorna el \'car\' de una lista, que es el primer atomo de una lista\n" +
				"Ejemplo 1: (car '(1 2 3)) => 1\n" +
				"Ejemplo 2: (car '((1 2 3) 4 5)) => (1 2 3)\n";

		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("car",textoDeAyuda));
		
		textoDeAyuda = "cdr <lista>\n" +
		"Retorna el \'cdr\' de una lista, que es una sublista una lista partiendo desde el segundo atomo\n" +
		"Ejemplo 1: (cdr '(1 2 3)) => (2 3)\n" +
		"Ejemplo 2: (cdr '(1 2 3 (cdr '(4 5 6)))) => (2 3 (5 6))\n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cdr",textoDeAyuda));
		
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cond"));
		
		textoDeAyuda = "cons <atomo 1> <atomo 2> \n" +
		"Es la funcion primitiva para crear un nuevo cons, cuyo \"car\" es <atomo 1> y cuyo \"cdr\" es <atomo 2>\n" +
		"Ejemplo 1: (cons 'a 'b) => (a b)\n" +
		"Ejemplo 2: (cons 'a (cons 'b (cons 'c '()))) => (a b c)\n" +
		"Ejemplo 3: (cons 'a '(b c d)) => (a b c d)\n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cons",textoDeAyuda));	
		
		textoDeAyuda = "Define una nueva funcion llamada <nombre de la funcion> en el ambito global. \n" +
		"Puede usarse para definir una funcion, corregir una o redefinirla. \n" +
		"Ejemplo 1: (defun alCuadrado (x) (* x x)) => alCuadrado\n"+
		"Como usarlo: (alCuadrado 2) => 4 \n\n"+
		"Ejemplo 2: (defun factorial (x) \n" +
		"\t (cond\n" +
        "\t\t((> x 0)\n" + 
        "\t\t\t(* x (factorial (- x 1)))\n"+
        "\t\t)\n" + 
        "\t\t(t\n" + 
        "\t\t\t1\n" +
        "\t\t)\n" +
        "\t)\n" +
        ")\n" +
		"Como usarlo: (factorial 5) => 120\n";
		
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("defun",textoDeAyuda));
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("lisp"));
		
		textoDeAyuda = "list-length <lista>\n" +
		"Retorna el tamano de una lista\n" +
		"Ejemplo 1: (list-length '(1 2 3 4 5)) => 5\n" + 
		"Ejemplo 2: (list-length '(1 2 (car '(4 5)))) => 3\n";
		
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("list-length",textoDeAyuda));
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("mapcar"));
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("prog1"));
		//this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("progn"));
		
		textoDeAyuda = "setq <variable de asignacion> <valor asignado>\n" +
		"Asocia un <valor asignado> a una <variable de asignacion>\n" +
		"Ejemplo 1: (setq lista '(1 2 3))\n" +
		"Ejemplo 2: (setq primerAtomo (car lista))\n";
			
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("setq",textoDeAyuda));
		
		textoDeAyuda = "+ <num 1> <num 2> .. <num n>\n" +
		"Suma dos o mas numeros \n" +
		"Ejemplo 1: (+ 1 2) => 3\n" +
		"Ejemplo 2: (+ 1 2 (+ 3 4)) => 10 \n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("+",textoDeAyuda));
		
		textoDeAyuda = "* <num 1> <num 2> .. <num n>\n" +
		"Multiplica dos o mas numeros \n" +
		"Ejemplo 1: (* 1 2) => 2\n" +
		"Ejemplo 2: (* 2 (+ 3 4)) => 14 \n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("*",textoDeAyuda));
		
		textoDeAyuda = "- <num 1> <num 2> .. <num n> \n" +
		"Resta dos o mas numeros \n" +
		"Ejemplo 1: (- 8 4) => 4\n" +
		"Ejemplo 2: (- 10 (+ 3 4)) => 3 \n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("-",textoDeAyuda));
		
		textoDeAyuda = "/ <num 1> <num 2> .. <num n> \n" +
		"Divide dos o mas numeros \n" +
		"Ejemplo 1: (/ 1 2) => 0.5\n" +
		"Ejemplo 2: (/ 5 (+ 1 1)) => 2.5 \n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("/",textoDeAyuda));
		
		textoDeAyuda = "equal <atomo 1> <atomo 2> \n" + 
		"Ejemplo 1: (equal 'a 'a) => T \n"+
		"Ejemplo 2: (equal (+ 1 1) (* 2 1))\n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("equal",textoDeAyuda));
		
		textoDeAyuda = "type-of <atomo 1>\n" +
		"Ejemplo 1: (type-of 'e) => SYMBOL\n" +
		"Ejemplo 2: (type-of 1.0) => SINGLE-FLOAT\n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("type-of",textoDeAyuda));
		
		textoDeAyuda = "cond <clausula 1> <clausula 2> .. <clausula n> \n" +
		"Ejemplo 1: (cond ((+ 3 4))) => 7 \n" +
		"Ejemplo 2: (cond ((= 1 2) 'a) ((= 1 1) 'b))\n";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("cond",textoDeAyuda));
		
		textoDeAyuda = "";
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("list",textoDeAyuda));

		/**
		 * Predicados
		 */
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("/="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("<"));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo("<="));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo(">"));
		this.listaDeOperacionesImplementadas.AgregarAlFinal(new Atomo(">="));
	}

	public Atomo parsearExpresion(String expresionAParsear,boolean estaDefiniendoUnaLista,boolean esClausula){
		//System.out.println("Expresion A Parsear: "+expresionAParsear+ " ,definiendoLista: "+estaDefiniendoUnaLista+" ,esClausula: "+esClausula);
		
		expresionAParsear = expresionAParsear.trim();
		
		/**
		 * Si lo que el usuario ingreso es "" (es decir, no hubieron tokens), parsear un atomo nulo
		 */
		if (expresionAParsear.compareTo("")==0)
			return new Atomo();
		
		/**
		 * Ver si est� balanceada
		 */
		if (!this.estaBienBalanceada(expresionAParsear))
			return new Atomo();

		Atomo atomoDeRespuesta = new Atomo();
		
		/**
		 * Instanciar un separador de tokens
		 */
		StringTokenizer separador = new StringTokenizer(expresionAParsear);
		
		String primerToken = separador.nextToken();
		Atomo atomoIngresado = new Atomo(primerToken);

		/**
		 * Si al separador le quedan 0 tokens, significa que ingreso toda la expresion en un solo token
		 * Puede ser una lista de la forma
		 * '(atomo)
		 * o una variable
		 */	
		if ((separador.countTokens()==0)){
			/**
			 * Ver si es una variable, y si hay que evaluarla
			 * Hay que evaluarla si y solo si, estaDefiniendoUnaLista es false
			 */
			if ((this.variablesDelUsuario.containsKey(primerToken)) && (!estaDefiniendoUnaLista))
				return this.variablesDelUsuario.get(primerToken);
			
			if ((primerToken.compareTo("'()")==0) || ((primerToken.compareTo("()")==0)))
				return new Atomo();

			if (primerToken.charAt(0)=='\''){
				if ((primerToken.charAt(1)!='(') && (primerToken.charAt(primerToken.length()-1)!=')'))
					return new Atomo(primerToken.substring(1));
			}
		}

		/**
		 * Si no retorno nada antes, puede ser que quiera parsearse una lista
		 *
		 *
		 * Si el primer simbolo del separador comienza con "'(", se esta definiendo una lista
		 * Si la expresion es '(algo '(1 2 3)), se esta defiendo una lista con dos elementos,
		 * OJO: tambien se puede estar definiendo la misma lista con '(algo (1 2 3))
		 * el primero es "algo" y el segundo es la lista (1 2 3). Por definici�n, cuando la lista
		 * se define con '(, es una lista y no una operacion. Para ingresar una operacion, habria que
		 * hacer (car '(1 2 3))
		 */
		if ((new Atomo(primerToken).comienzaCon("'("))){
			estaDefiniendoUnaLista = true;
			atomoDeRespuesta = new Atomo(new Lista());
			atomoDeRespuesta.lista.esOperacion = false;
			
			/**
			 * Entonces, quiere parsear una lista
			 * hay que cortar la expresi�n que esta dentro de esta..
			 * eso es, la expresi�n a partir del primer parentesis abierto (indice 1) hasta el parentesis que lo cierra
			 */
			
			int desdeDondeCortar = expresionAParsear.indexOf('(') + 1;
			int hastaDondeCortar = this.obtenerIndiceDelParentesisQueCierraAlPrimeroEn(expresionAParsear);
			
			/**
			 * Ver si no hay listas despues de esta lista.. es decir, que es la lista "mayor"
			 * es decir, la que encapsula a todas. De ser asi..
			 * no debe haber nada despues del ultimo parentesis
			 */
			if ((hastaDondeCortar==expresionAParsear.lastIndexOf(')')) && (!(hastaDondeCortar==expresionAParsear.length()-1)))
				return new Atomo();
			
			expresionAParsear = expresionAParsear.substring(desdeDondeCortar, hastaDondeCortar);			
		} else if (primerToken.charAt(0)=='('){
			/**
			 * Entonces, puede estar tratando de parsear una operacion
			 * o una lista
			 * 
			 * Esta parseando una operacion si: estaDefiniendoUnaLista es false
			 * Esta parseando una operacion si: esCOND es true
			 * Esta parseando una lista si: estaDefiniendoUnaLista es true
			 */
			
			int desdeDondeCortar = expresionAParsear.indexOf('(') + 1;
			int hastaDondeCortar = this.obtenerIndiceDelParentesisQueCierraAlPrimeroEn(expresionAParsear);
			
			expresionAParsear = expresionAParsear.substring(desdeDondeCortar,hastaDondeCortar);
			atomoDeRespuesta = new Atomo(new Lista());
			
			
			separador = new StringTokenizer(expresionAParsear);
			String primerTokenDeLaLista = separador.nextToken();
			
			boolean esFuncion = (esOperacionImplementada(primerTokenDeLaLista)) || this.funcionesDelUsuario.containsKey(primerTokenDeLaLista);
			
			if ((!estaDefiniendoUnaLista) && (!esFuncion)){
				/**
				 * Ver si se esta definiendo a ella misma
				 */
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
		
		/**
		 * Ahora, revisar si es una lista.. o no, si no era una lista, eso significa que 
		 * ingreso cualquier String, digamos "aasd asdf adsf sd saf"
		 */
		if (!atomoDeRespuesta.EsLista())
			return new Atomo(expresionAParsear);
		
		separador = new StringTokenizer(expresionAParsear);
		
		while (separador.hasMoreTokens()) {
			Atomo atomoActual = new Atomo(separador.nextToken());

			/**
			 * Si encuentra una lista nueva ya sea sin operacion (empezando con "'(") 
			 * o con operacion (empezando con "("), mandar a parsearla recursivamente
			 */
			if ((atomoActual.comienzaCon("'(")) || (atomoActual.comienzaCon("("))){
				/**
				 * Obtener la expresi�n de la lista
				 */
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
							
							/**
							 * Si es Defun y si atomoActual es el tercer atomo, parsearlo como lista
							 * ya que son los parametros de la funcion
							 */
							if ((operacionDeLaLista.compareToIgnoreCase("defun")==0) && (atomoDeRespuesta.lista.size()==2)){
								atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, true,false);
								esDefun = true;
							}
							
							/**
							 * Si es COND, parsearlo como lista
							 */
							if (operacionDeLaLista.compareToIgnoreCase("cond")==0){
								esCOND = true;
								atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, true,true);
							}
						}
					/*	if ((atomoDeRespuesta.lista.getAtomoEn(0).toString().compareToIgnoreCase("defun")==0) && (atomoDeRespuesta.lista.size()==2)){
							/**
							 * Obtener los parametros
							 */
					/*		atomoConLaListaInterna = this.parsearExpresion("'"+expresionDeLaListaInterna, estaDefiniendoUnaLista);
							esDefun = true;
						}*/
					}
					
					if ((!esDefun) && (!esCOND))
						atomoConLaListaInterna = this.parsearExpresion(expresionDeLaListaInterna, estaDefiniendoUnaLista, esClausula);
					
				}
				
				atomoDeRespuesta.lista.AgregarAlFinal(atomoConLaListaInterna);
				String expresionDespuesDeLaListaInterna = expresionAParsear.substring(hastaDondeCortar + 1);
				expresionAParsear = expresionDespuesDeLaListaInterna;
				
				if (!esDefun){
					/**
					 * Instanciar de nuevo el separador a partir
					 * de en donde termina la lista
					 */
					separador = new StringTokenizer(expresionDespuesDeLaListaInterna);
				} else {
					/**
					 * Crear la funcion, es decir, meter la operacion
					 * por si esta haciendo una funcion recursiva 
					 */
					String nombreDeLaFuncion = atomoDeRespuesta.lista.getAtomoEn(1).toString();
					this.funcionesDelUsuario.put(nombreDeLaFuncion,new Atomo());
					
					Atomo atomoConLaOperacion = this.parsearExpresion(expresionAParsear.trim(), false,false);
					atomoDeRespuesta.lista.AgregarAlFinal(atomoConLaOperacion);
					separador = new StringTokenizer("");
					
					this.funcionesDelUsuario.remove(nombreDeLaFuncion);
				}
			} else{
				/**
				 * Es un simple atomo, agregarlo a la lista
				 * Si es un atomo que tiene un valor asignado, ponerlo
				 */
				int desdeDondeCortar = expresionAParsear.indexOf(atomoActual.toString());
				expresionAParsear = expresionAParsear.substring(desdeDondeCortar);
				
				if (atomoActual.comienzaCon("'"))
					/**
					 * Ver si comienza con '
					 * o 'b
					 */
					atomoActual = new Atomo(atomoActual.toString().substring(1));
					
				atomoDeRespuesta.lista.AgregarAlFinal(atomoActual);
			}
		}
		
		//System.out.print("Parseo: "+atomoDeRespuesta.toString());
		//System.out.println(" Es lista con operacion?: "+atomoDeRespuesta.EsListaConOperacion());
		return atomoDeRespuesta;
	}
	
	/**
	 * Metodo que verifica si posibleOperacion esta dentro de la Lista listasOperacionesImplementadas
	 * @param posibleOperacion
	 * @return
	 */
	private boolean esOperacionImplementada(String posibleOperacion) {
		return this.listaDeOperacionesImplementadas.existe(new Atomo(posibleOperacion));
	}

	/**
	* Metodo que revisa si una expresion esta bien balanceada o no,
	* es decir, si tiene el mismo numero de parentesis cerrados que abiertos.
	* @return true si la expresion esta bien balanceada, false de lo contrario
	* @author kmels
	*/
	public boolean estaBienBalanceada(String expresion) {
			int parentesisAbiertos = 0;
			int parentesisCerrados = 0;
			/*
			 * Recorrer expresion
			 */
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
		
		//System.out.println("atomoAEvaluar: " +atomoAEvaluar);
		if (atomoAEvaluar.esNulo)
			return new Atomo();
		
		if (atomoAEvaluar.EsLista()){
			/**
			 * Si es una lista, puede ser que sea una simple lista, o una lista con operacion
			 * De ahora en adelante, se hablara de listaAEvaluar y no atomoAEvaluar
			 */
			Lista listaAEvaluar = atomoAEvaluar.lista;
			
			if (listaAEvaluar.esOperacion){
				//System.out.println("listaAEvaluar.esOperacion: " +listaAEvaluar.esOperacion);
				/**
				 * Evaluar todos los atomos que estan dentro 
				 */
				String operacionDeLaLista = listaAEvaluar.getOperacion().toString(); 
				if ((operacionDeLaLista.compareToIgnoreCase("defun")!=0) && (operacionDeLaLista.compareToIgnoreCase("cond")!=0)) 
					for (int i=0 ; i < listaAEvaluar.size() ; i++){
						if (listaAEvaluar.getAtomoEn(i).EsListaConOperacion()){
							Atomo listaEvaluada = this.evaluar(listaAEvaluar.getAtomoEn(i));
							listaAEvaluar.remplazarEn_Por(i, listaEvaluada);
						} else if (this.variablesDelUsuario.containsKey(listaAEvaluar.getAtomoEn(i).toString())){
							Atomo valorDelAtomo = this.variablesDelUsuario.get(listaAEvaluar.getAtomoEn(i).toString());						
							/**
							 * Si no la esta estableciendo, es decir
							 * si no esta haciendo 
							 * setq <atomo que se esta evaluando> <valor a asignar>
							 * Evaluarlo
							 */
							if (listaAEvaluar.esOperacion){
								if (!((i==1) && (listaAEvaluar.getOperacion().toString().compareTo("setq")==0)))
									listaAEvaluar.remplazarEn_Por(i, valorDelAtomo);
							} else 
								listaAEvaluar.remplazarEn_Por(i, valorDelAtomo);	
						}
					}								
			} else {
				//System.out.println("listaAEvaluar.esOperacion: " +listaAEvaluar.esOperacion);
				return new Atomo(listaAEvaluar);
			}
			
			/**
			 * Si es operacion, entonces hay que evaluarla
			 */			
			String operacion = listaAEvaluar.getOperacion().toString();
			if (this.esPredicado(operacion)){
				return this.evaluarPredicado(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("cdr")==0){
				return this.cdr(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("car")==0){
				return this.car(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("list-length")==0){
				return this.list_length(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("setq")==0){
				return this.setq(listaAEvaluar);
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
			} else if (operacion.compareToIgnoreCase("type-of")==0){
				return this.type_of(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("cond")==0){
				return this.cond(listaAEvaluar);
			} else if (operacion.compareToIgnoreCase("list")==0){
				return this.list(listaAEvaluar);
			}
		} else{
			/**
			 * No es una lista, puede ser una variable
			 */
			if (this.variablesDelUsuario.containsKey(atomoAEvaluar.toString()))
				AtomoDeRespuesta = this.variablesDelUsuario.get(atomoAEvaluar.toString());
			else
				/**
				 * Ya est� evaluado.
				 */
				AtomoDeRespuesta = atomoAEvaluar;	
		}
		
		return AtomoDeRespuesta;
	}

	private Atomo list(Lista listaAEvaluar) {
		// Declarar variables y objetos
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
		
		/**
		 * Ir a traves de todas las sublistas y ver si hay que ejecutarlas 
		 */
		int revisandoSubListaIndex = 1;
		boolean haEncontradoRespuesta = false;
		Atomo atomoDeRespuesta = new Atomo();
		
		while ((revisandoSubListaIndex < listaAEvaluar.size()) && (!haEncontradoRespuesta)){
			/**
			 * listaAEvaluar.getAtomoEn(i) tiene que ser una lista, de lo contrario, ingreso mal 
			 * los parametros de "cond"
			 */
			if (!listaAEvaluar.getAtomoEn(revisandoSubListaIndex).EsLista()) 
				return new Atomo();
			else {
				/*
				 * Revisar que no sea una lista NIL
				 */
				
			}
			
			Lista evaluandoSubLista = listaAEvaluar.getAtomoEn(revisandoSubListaIndex).lista;
			
			/**
			 * Evaluar su primer atomo, para ver si es TRUE
			 */
			Atomo primerAtomo = evaluandoSubLista.getAtomoEn(0);
			
			/**
			 * Si no es la ultima clausula, y todavia no hay respuesta, no validar
			 */
			if (!((atomoDeRespuesta.esNulo) && (revisandoSubListaIndex==listaAEvaluar.size()-1))) {
				if ((!primerAtomo.EsLista()) && (!this.variablesDelUsuario.containsKey(primerAtomo.toString())))
					return new Atomo();
			}
			
			Atomo primerAtomoEvaluado = this.evaluar(primerAtomo);
			
			
			/**
			 * Si el primer atomo de evaluandoSubLista no es NIL (es TRUE):
			 * 		Encontro respuesta
			 * 			Si hay un atomo de respuesta, retornar ese atomo de respuesta
			 * 			Si no, retornar el atomo evaluado (el que no es NIL)
			 * Si el primer atomo es NIL, atomoDeRespuesta = NIL (pero no ha encontrado respuesta, es decir, atomoDeRespueta es un valor parcial, o temporal)
			 *				atomoDeRespuesta = nil, no?
			 *
			 */
			
			if (!primerAtomoEvaluado.esNulo){
				haEncontradoRespuesta = true;
					
				if (evaluandoSubLista.size() > 1)
					/**
					 * Entonces, especifico una respuesta
					 */
					atomoDeRespuesta = this.evaluar(evaluandoSubLista.getAtomoEn(evaluandoSubLista.size()-1));
				else
					/**
					 * Retornar el valor evaluado
					 */
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
		/**
		 * Revisar que tenga dos parametros
		 * 
		 */
		if (listaAEvaluar.size()!=3)
			return new Atomo();
		
		/**
		 * Definici�n de equal:
		 * The equal predicate is true if its arguments are structurally similar (isomorphic) objects. 
		 * A rough rule of thumb is that two objects are equal if and only if their printed representations 
		 * are the same.
		 */
		return new Atomo(listaAEvaluar.getAtomoEn(1).toString().compareTo(listaAEvaluar.getAtomoEn(2).toString())==0);
	}


	private Atomo operarFuncionDelUsuario(Lista listaAEvaluar){
		/**
		 * Mapear parametros a valores 
		 */
		int numeroDeParametrosQueIngreso = listaAEvaluar.size()-1;
		
		Lista listaDeLosParametros = this.funcionesDelUsuario.get(listaAEvaluar.getOperacion().toString()).lista.getAtomoEn(0).lista;
		
		//System.out.println("Lista de parametros:" + listaDeLosParametros);
		int numeroDeParametrosDeLaFuncion = listaDeLosParametros.size();	
		
		if (numeroDeParametrosDeLaFuncion!=numeroDeParametrosQueIngreso)
			return new Atomo();

		Hashtable<String,Atomo> mapaDeParametrosYValores = new Hashtable<String,Atomo>();
		
		for (int i =1 ; i <= numeroDeParametrosQueIngreso ; i++){
			//System.out.println("Mapeando el parametro "+i);
			mapaDeParametrosYValores.put(listaDeLosParametros.getAtomoEn(i-1).toString(), listaAEvaluar.getAtomoEn(i));
		}
		
		//System.out.println("mapaDeParametrosYValores"+mapaDeParametrosYValores);
		
		Atomo atomoDeLaOperacion = this.funcionesDelUsuario.get(listaAEvaluar.getOperacion().toString()).lista.getAtomoEn(1);
		
		if (atomoDeLaOperacion.EsLista()){
			Atomo listaAEvaluarConParametrosMapeados = this.mapearParametrosEn(mapaDeParametrosYValores, new Atomo(atomoDeLaOperacion.lista)); 
			return this.evaluar(listaAEvaluarConParametrosMapeados);
		} else
			return atomoDeLaOperacion;
		
	}


	private Atomo mapearParametrosEn(Hashtable<String, Atomo> mapaDeParametrosYValores, Atomo atomoEnDondeMapear){

		/**
		 * Hay que copiar la lista, por que de otro modo se estaria sobre escribiendo 
		 * la funcion
		 */
		Lista listaAEvaluar = new Lista(atomoEnDondeMapear.lista);
		listaAEvaluar.esOperacion = atomoEnDondeMapear.lista.esOperacion;
		
		//System.out.println("Antes de mapear: "+listaAEvaluar);
		
		/**
		 * Ir a travez de cada uno de los elementos de la lista a evaluar, y mapear los
		 * parametros a sus valores 
		 */
		for (int i= 0 ; i < listaAEvaluar.size(); i++){
			Atomo atomoActual = listaAEvaluar.getAtomoEn(i);
			
			if (atomoActual.EsLista()){
				listaAEvaluar.remplazarEn_Por(i,this.mapearParametrosEn(mapaDeParametrosYValores, atomoActual));
			} else{
				if (mapaDeParametrosYValores.containsKey(atomoActual.toString()))
					listaAEvaluar.remplazarEn_Por(i,mapaDeParametrosYValores.get(atomoActual.toString()));
			}
		}
		
		//System.out.println("Ya mapeado: "+ listaAEvaluar);
		return new Atomo(listaAEvaluar);
	}


	private Atomo defun(Lista listaAEvaluar){
		/**
		 * validar que hayan 4 elementos en la lista:
		 * defun (ya verificado), nombre de la lista, lista con parametros, operacion parseada
		 */
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
		
		/**
		 * Mapear la funcion nombreDeLaFuncion
		 * a su lista de parametros, y a su operacion
		 */
		Atomo atomoDeLaFuncion = new Atomo(new Lista(listaAEvaluar.subList(2, 4)));
		this.funcionesDelUsuario.put(nombreDeLaFuncion, new Atomo(new Lista(listaAEvaluar.subList(2, 4))));
		
		return new Atomo(nombreDeLaFuncion);
	}

	private boolean esNombreDeFuncionValida(String nombreDeLaFuncion){
		
		/**
		 * Ver si es una funcion ya implementada por GtLisp
		 */
		if (esOperacionImplementada(nombreDeLaFuncion))
			return false;
		
		/**
		 * Ver que no empiece con un numero
		 */
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
				//if (!listaAEvaluar.getAtomoEn(i).esEntero())
				//	todosSonEnteros = false;
			
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
		
		/**
		 * Si el segundo atomo es una lista, insertar cada uno de los atomos
		 */
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
		
		/**
		 * OJO: solo estamos seguros que es un String, pero no sabemos si es un numero
		 * FALTA!!
		 * @author kmels
		 */
		String variableDeAsignacion = ((Atomo) listaAEvaluar.get(1)).toString(); 
		Atomo atomoAsignado = ((Atomo) listaAEvaluar.get(2));
		
		/**
		 * Evaluar el atomo asignado en caso de que sea una operacion
		 * o una variable, o una lista con variables..
		 */ 
		atomoAsignado = this.evaluar(atomoAsignado);

		this.variablesDelUsuario.put(variableDeAsignacion, atomoAsignado);
		return atomoAsignado;
	}


	public Atomo list_length(Lista listaAEvaluar) {
		/**
		 * list-length: devuelve el tamano de una lista
		 * Operandos: una lista
		 * Ejemplo: (lisg-length '(1 2 3)) 
		 */
		
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

		/**
		 * 	Ver si es una variable 
		 */
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
