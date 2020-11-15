import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MezclaEquilibrada{
	
	private String nombreArchivo;
	private String orden;

	public MezclaEquilibrada(String nombreArchivo){
		// Se recibirá siempre el nombre del archivo, para generar archivos
		// que no generen conflictos entre distintas ejecuciones.
		this.nombreArchivo = nombreArchivo;
	}

	// Setter para que se reconozca el tipo de orden que tendrá
	// esta ejecución.
	public void setOrden(String orden){
		this.orden=orden;
	}

	public void sortNombres(ArrayList<Alumno> alumnos,int inicioF1, int inicioF2, int inicioFinal) throws FileNotFoundException, IOException{
		// Primero se verificará si están ordenados los datos que llegaron.
		if(verificar(alumnos,1)){
			System.out.println("¡Ya está ordenada!");
		}else{
			// System.out.println("No está ordenada, se intentará de nuevo.");
			separarArchivo(alumnos,1);
			int[] finales = mezclarArchivo(inicioF1,inicioF2,1);

			// Esta última referencia se encuentra en el último elemento de una nueva
			// lista.
	    	ArrayList<Alumno> nuevaListaAlumnos = nuevaLista(inicioFinal);

	    	// Se almacena el último elemento para encontrar la línea final, siendo esta
	    	// un "número de cuenta".
	    	Alumno ultima = nuevaListaAlumnos.remove(nuevaListaAlumnos.size()-1);

	    	// Ya que se ha eliminado el elemento que no pertenece a la línea original,
	    	// se procede a verificar.
	    	if(verificar(nuevaListaAlumnos,1)){
	    		System.out.println("\t \033[1m\033[1;34m¡Ya se ordenó!\033[1;36m");
    			System.out.println("Los archivos que deberás revisar son los siguientes:");
				System.out.println("\033[0;35m-> \033[1;36mME_Auxiliar1_"+this.nombreArchivo+"_"+this.orden+".txt");
				System.out.println("\033[0;35m-> \033[1;36mME_Auxiliar2_"+this.nombreArchivo+"_"+this.orden+".txt");
				System.out.println("\033[0;35m-> \033[4;1m\033[38;5;11mME_FINAL_"+this.nombreArchivo+"_"+this.orden+".txt\033[0m");
	    	}else{
	    		// System.out.println("Aún no se ordena, lo intentaremos de nuevo.):");
				// Se convierte la última referencia del archivo final en un entero
				int ultimaInt = Integer.parseInt(ultima.getNoCuenta());
				sortNombres(nuevaListaAlumnos,finales[0],finales[1],ultimaInt);
	    	}
		}
	}

	public void sortApellidos(ArrayList<Alumno> alumnos,int inicioF1, int inicioF2, int inicioFinal) throws FileNotFoundException, IOException{
		// Primero se verificará si están ordenados los datos que llegaron.
		if(verificar(alumnos,2)){
			System.out.println("¡Ya está ordenada!");
		}else{
			// System.out.println("No está ordenada, se intentará de nuevo.");
			separarArchivo(alumnos,2);
			int[] finales = mezclarArchivo(inicioF1,inicioF2,2);

			// Esta última referencia se encuentra en el último elemento de una nueva
			// lista.
	    	ArrayList<Alumno> nuevaListaAlumnos = nuevaLista(inicioFinal);

	    	// Se almacena el último elemento para encontrar la línea final, siendo esta
	    	// un "número de cuenta".
	    	Alumno ultima = nuevaListaAlumnos.remove(nuevaListaAlumnos.size()-1);

	    	// Ya que se ha eliminado el elemento que no pertenece a la línea original,
	    	// se procede a verificar.
	    	if(verificar(nuevaListaAlumnos,2)){
	    		System.out.println("\t \033[1m\033[1;34m¡Ya se ordenó!\033[1;36m");
	    		System.out.println("Los archivos que deberás revisar son los siguientes:");
				System.out.println("\033[0;35m-> \033[1;36mME_Auxiliar1_"+this.nombreArchivo+"_"+this.orden+".txt");
				System.out.println("\033[0;35m-> \033[1;36mME_Auxiliar2_"+this.nombreArchivo+"_"+this.orden+".txt");
				System.out.println("\033[0;35m-> \033[4;1m\033[38;5;11mME_FINAL_"+this.nombreArchivo+"_"+this.orden+".txt\033[0m");
	    	}else{
	    		// System.out.println("Aún no se ordena, lo intentaremos de nuevo.):");
				// Se convierte la última referencia del archivo final en un entero
				int ultimaInt = Integer.parseInt(ultima.getNoCuenta());
				sortApellidos(nuevaListaAlumnos,finales[0],finales[1],ultimaInt);
	    	}
		}
	}

	public int[] mezclarArchivo(int inicio1,int inicio2, int modo) throws FileNotFoundException, IOException{

		/***************************************************************************/
		/*                                                                         */
		/*              INICIA FASE 2 - UNIÓN DE AUXILIARES                        */
		/*                                                                         */
		/***************************************************************************/
  		int inicioF1=inicio1;
  		int inicioF2=inicio2;

	    // Guardamos todo el contenido del archivo en el buffer del primer archivo
	    // auxiliar.
        BufferedReader f1 = new BufferedReader(new FileReader("./ME_Auxiliar1_"+this.nombreArchivo+"_"+this.orden+".txt"));
        BufferedReader f2 = new BufferedReader(new FileReader("./ME_Auxiliar2_"+this.nombreArchivo+"_"+this.orden+".txt"));
        
        // Se LA PRIMERA LÍNEA de cada archivo.
        String contenidoA1 = f1.readLine();
        String contenidoA2 = f2.readLine();

        if(inicioF1!=0){
        	for (int i=1;i<=inicioF1;i++ ) {
				contenidoA1 = f1.readLine();
        	}
        }
        if(inicioF2!=0){
	        for (int i=1;i<=inicioF2;i++ ) {
				contenidoA2 = f2.readLine();
	    	}
        }
        
        // Las líneas de los archivos ya se acomodaron a donde deben comenzar.

        // Se recorrerá el archivo auxiliar 2 hasta que encuentre el final de iteración.
        while (contenidoA2.charAt(0) != '/'){
       		// Se crearán dos listas que guardarán temporalmente los datos hasta que 
       		// se encuentren con una interrupción. 
       	    ArrayList<Alumno> listaf1 = new ArrayList<>();
       	    ArrayList<Alumno> listaf2 = new ArrayList<>();

	        // Esta es la lista que almacenará el contenido de las otras dos listas
	        // para que después esta se ordene.
	        ArrayList<Alumno> listaf0 = new ArrayList<>();
	        if(contenidoA1.charAt(0) == '>'){
	        	contenidoA1=f1.readLine();
				inicioF1+=1;
	        }
        	// Si la ínea no es una interrupción, se guardará en la lista 1
        	// los datos de los alumnos hasta que encuentre la siguiete interrupción
        	// o el final del archivo.
	        if(contenidoA1.charAt(0) != '/'){
	        	do{
		        	String[] datos = contenidoA1.split(",");
		        	listaf1.add(new Alumno(datos[0],datos[1],datos[2]));
			        contenidoA1 = f1.readLine();
					inicioF1+=1;
		        }while (contenidoA1.charAt(0) != '>' && contenidoA1.charAt(0) != '/');
				// Los datos añadidos a la lista 1 se añadirán a la lista que contenga todos.
				for (Alumno alumno : listaf1) {
				  	listaf0.add(alumno);
				}
			}

	        if(contenidoA2.charAt(0) == '>'){
	        	contenidoA2=f2.readLine();
				inicioF2+=1;
	        }
	        if(contenidoA2.charAt(0) != '/'){
	        	do{
	        		String[] datos = contenidoA2.split(",");
	        		listaf2.add(new Alumno(datos[0],datos[1],datos[2]));
		        	contenidoA2 = f2.readLine();
					inicioF2+=1;
		    	}while (contenidoA2.charAt(0) != '>' && contenidoA2.charAt(0) != '/');
		        for (Alumno alumno : listaf2) {
		        	listaf0.add(alumno);
		        }
	        }

	        if( ! listaf0.isEmpty() ){
		        // Ya que fueron agregados elementos se deben ordenar y después
		        // añadir al archivo inicial (que en este caso será FINAL.txt)
		        ArrayList<Alumno> listaOrdenada;
		        if(modo == 1){
		        	listaOrdenada = ordenar(listaf0,1);
		        }else{
		        	listaOrdenada = ordenar(listaf0,2);
		        }
		        for (Alumno alumno : listaOrdenada) {
		         	añadir(alumno, 0, 0);
		            /*       ^     ^  ^
		          	         |     |  |
	        	             |     | Indica que se añadirá SIN INTERRUPCIÓN
	         	             |     |  
	        	             |   Indica que se añadirá en el archivo FINAL 
	         	             |       
	         	           Indican los datos que se añadirán.
		            */
		        }
	        }

        }
        // Dado que el límite de iteraciones lo pone el ARCHIVO AUXILIAR 2
        // debe verificarse si quedó un elemento libre en el archivo auxiliar 1
        // y ya que se dividen en pares, solo hay posibilidad que un elemento haya quedado
        // libre, el contenido de esa última línea ya se encuentra en contenidoA1
        
        // Si se dividió en pares, las variables contenidoA1 y contenidoA2, tendrían un
        // "///////////////////////", SI ES IMPAR la cantidad de elementos, el valor de
        // contenidoA1 será un ">>>>>>>>>>>>>>>>>>"

        if(contenidoA1.charAt(0)=='>'){
        	// Si se trata de una interrupción, es que hay un registro extra EN LA 
        	// SIGUIENTE LÍNEA, por lo tanto, se debe leer la siguiente.
        	contenidoA1 = f1.readLine();
			inicioF1+=1;
		}
		if(contenidoA1.charAt(0)!='/'){
		    String[] datos = contenidoA1.split(",");
		    añadir(new Alumno(datos[0],datos[1],datos[2]),0,0);
		    // La última línea del archivo, será hasta ahorita la siguiente.
		    contenidoA1 = f1.readLine();    		
			inicioF1+=1;
        }

		// Se añade una interrupción de FINAL DE ITERACIÓN AL ARCHIVO FINAL.
		añadir(new Alumno("Final","Final","final"),0,-1);
		
	    f1.close();
	    f2.close();

	    int[] finales ={inicioF1+1,inicioF2+1};
	    return finales;
	}
	
	public void separarArchivo(ArrayList<Alumno> alumnos, int modo) throws FileNotFoundException, IOException{

		/***************************************************************************/
		/*                                                                         */
		/*              INICIA FASE 1 - SEPARACIÓN EN AUXILIARES                   */
		/*                                                                         */
		/***************************************************************************/

		// EL "modo", funciona para identificar si se está solicitando separar por
		// NOMBRES (1) o por APELLIDO (2)


		// El archivo donde se comenzará a añadir será en f1
		int archivo = 1;
		int i;
		for(i=0;i<alumnos.size()-1;i++){
			Alumno anterior = alumnos.get(i);
			Alumno alumno = alumnos.get(i+1);
			int prioridadAnterior;
			int prioridadAlumno;
			if(modo == 1){
				prioridadAnterior = prioridad(anterior.getNombre(),0,1);
				prioridadAlumno = prioridad(alumno.getNombre(),0,1);
			}else{
				prioridadAnterior = prioridad(anterior.getApellido(),0,2);
				prioridadAlumno = prioridad(alumno.getApellido(),0,2);
			}
			if(prioridadAnterior==prioridadAlumno){
				int prioridad;
				if(modo == 1){
					prioridad = resolverColision(anterior.getNombre(),alumno.getNombre(),1);
				}else{
					prioridad = resolverColision(anterior.getApellido(),alumno.getApellido(),2);
				}
				// Si tiene más prioridad el alumno enviado en el primer argumento.
				if(prioridad==0||prioridad==1){
					// El cero representará que SON IGUALES LOS NOMBRES.
					// EL uno representará que tiene más prioridad el "alumno anterior".
					añadir(anterior,archivo,0);
				}else{
					añadir(anterior,archivo,0);

					if( i!=alumnos.size()-2){
						añadir(anterior,archivo,1);
					}	
					// Si el anterior estado era el archivo f1 se cambia, si no, se mantiene.
					if(archivo==1){
						archivo=2;
					}else{
						archivo=1;
					}
				}
			}else if(prioridadAnterior<prioridadAlumno){
				// Ya que el valor siguiente matiene un orden ascendente se guarda
				// en el archivo que corresponde en ese momento.
				añadir(anterior,archivo,0);
			}else{
				// Quiere decir que el alumno siguiente rompe el orden ascendente por lo que
				// el anterior será el último alumno en agregarse al archivo antes de la
				// interrupción.
				añadir(anterior,archivo,0);

				// Antes de hacer el cambio de archivo se imprime una interrupción en
				// el archivo actual, para que se sepa que hubo una ruptura en el orden.
				// El número 1 indica que fue solicitada una interrupción.
				if( i!=alumnos.size()-2){
					añadir(anterior,archivo,1);
				}
				
				// Si el anterior estado era el archivo f1 se cambia, si no, se mantiene.
				if(archivo==1){
					archivo=2;
				}else{
					archivo=1;
				}
			}
		}

		// EL CICLO TERMINARÁ Y FALTARÁ UNA ITERACIÓN
		// La viariable 'archivo' tiene ahora el archivo donde se AÑADIRÁ.
		// La variable 'i' tiene actualmente el último INDICE al que se llegó.
		
		Alumno ultimoLista = alumnos.get(alumnos.size()-1);
		
		// Dado que la referencia al archivo que le corresponde ya está guardada solo
		// se añade a archivo correspondiente.
		añadir(ultimoLista,archivo,0);

		/***************************************************************************/
		/*          TERMINÓ LA SEPARACION DE DATOS.                                */
		/***************************************************************************/

		// Se añadirán interrupciones finales en los dos archivos auxiliares.
		añadir(ultimoLista,1,-1);
		añadir(ultimoLista,2,-1);
	}

	public ArrayList<Alumno> ordenar(ArrayList<Alumno> alumnos, int modo){
		
		// EL segundo argumento indicará por que MODO se organizarán
		// si por NOMBRE (1) o por APELLIDO (2)

       	for (int i = 0; i < alumnos.size()-1; i++) {
       	    for (int j = 0; j < alumnos.size()-i-1; j++) {
       	    	int prioridadAlu1;
       	    	int prioridadAlu2;
       	    	if(modo == 1 ){
       	    		prioridadAlu1 = prioridad(alumnos.get(j).getNombre(),0,1);
       	    		prioridadAlu2 = prioridad(alumnos.get(j+1).getNombre(),0,1);
       	    	}else{
       	    		prioridadAlu1 = prioridad(alumnos.get(j).getApellido(),0,2);
       	    		prioridadAlu2 = prioridad(alumnos.get(j+1).getApellido(),0,2);
       	    	}
       	        
       	        if(prioridadAlu1==prioridadAlu2){
       	        	// Si tienen la misma prioridad hay una COLISIÓN.
       	        	int ganador;
       	        	if(modo == 1){
       	        		ganador = resolverColision(alumnos.get(j).getNombre(),alumnos.get(j+1).getNombre(),1);
       	        	}else{
       	        		ganador = resolverColision(alumnos.get(j).getApellido(),alumnos.get(j+1).getApellido(),2);
       	        	}
       	        	// Ahora que se sabe quien tiene mejor probabilidad se ordenará 
       	        	if(ganador==2){
       	        		// Si el ganador es el segundo, quiere decir que el segundo 
       	        		// VA ANTES, así que se realiza un intercambio.
       	        		Alumno temp = alumnos.get(j);
       	        		alumnos.set(j,alumnos.get(j+1));
       	        		alumnos.set(j+1,temp);
       	        	}
       	        // Si la prioridad del primer alumno es mayor, quiere decir que 
       	        // alfabéticamente va después, por lo que debe haber un intercambio.
       	        }else if (prioridadAlu1 > prioridadAlu2){ 
       	            Alumno temp = alumnos.get(j);
       	            alumnos.set(j,alumnos.get(j+1));
       	            alumnos.set(j+1,temp);
       	        } 
       	    }
       	}
       	return alumnos;
	}


	public void añadir(Alumno alumno, int archivo, int interrupcion) throws FileNotFoundException, IOException{
		// El valor del tercer argumento indica que tipo de interrupción se añadirá en
		// el archivo.
		// 0 -> No solicita interrupción, todo continúa con normalidad.
		// 1 -> Solicita una interrupción normal >>>>>>>>
		// 2 -> Solicita una interrupción de FIN DE ITERACIÓN. //////
		File archivoFinal = new File("./ME_FINAL_"+this.nombreArchivo+"_"+this.orden+".txt");
		File auxiliar1 = new File("./ME_Auxiliar1_"+this.nombreArchivo+"_"+this.orden+".txt");
		File auxiliar2 = new File("./ME_Auxiliar2_"+this.nombreArchivo+"_"+this.orden+".txt");
		FileWriter f0 = new FileWriter(archivoFinal,true);
		FileWriter f1 = new FileWriter(auxiliar1,true);
		FileWriter f2 = new FileWriter(auxiliar2,true);
		
		String nombre = alumno.getNombre();		
		String apellido = alumno.getApellido();		
		String cuenta = alumno.getNoCuenta();		

		// Se verifica si se solició una interrupción.
		if(interrupcion == 1){
			// Ya que si la solicitó, buscará el archivo en donde colocarla.
			if(archivo == 0){
				// Imprimir interrupción en el archivo principal.
					f0.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			}else if (archivo == 1){
				// Imprimir interrupcion en el archivo 1.
					f1.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			}else{
				// Imprimir interrupcion en el archivo 2.
					f2.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			}
		}else if(interrupcion == 0){
			switch (archivo){
				case 0:
					// Se añadirá el alumno en el archivo FINAL.
					f0.write(nombre.trim()+", "+apellido.trim()+ ", "+cuenta.trim()+"\n");
				break;
				case 1:
					// Se añadirá el alumno al archivo auxiliar 1.
					f1.write(nombre.trim()+", "+apellido.trim()+ ", "+cuenta.trim()+"\n");
				break;
				case 2:
					// Se añadirá el alumno al archivo auxiliar 2.
					f2.write(nombre.trim()+", "+apellido.trim()+ ", "+cuenta.trim()+"\n");
				break;
			}
		}else{
			switch (archivo){
				case 0:
					f0.write("/////////////////////////////////////////\n");
				break;
				case 1:
					f1.write("/////////////////////////////////////////\n");
				break;
				case 2:
					f2.write("/////////////////////////////////////////\n");
				break;
			}
		}
		f0.close();
		f1.close();
		f2.close();
	}	

	// Este método servirá para poder identificar si se están leyendo los valores en orden
	// y posteriormente para poder ordenarlos.
	public int prioridad(String cadena, int indice, int modo){
		// Mediante el modo se identificará en en base a que se definirá la prioridad
		// (1) para NOMBRES
		// (2) para APELLIDOS
		try{
			// La prioridad de elección se realizará mediante el primer caracter del nombre
			// y se devolverá el índice, entre menor sea el índice, hay mayor prioridad.
			char mayusculas[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			char minusculas[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
			char acentos[]={'Á','É','Í','Ó','Ú','á','é','í','ó','ú'};
			char letra = cadena.charAt(indice);
			for(int i=0;i<mayusculas.length;i++){
				char aux = mayusculas[i];
				if(letra == aux){
					// Retorna la prioridad dada por el índice.
					return i;
				}
			}
			for (int i=0;i<minusculas.length;i++) {
				char aux = minusculas[i];
				if(letra == aux){
					// Si el usuario quiere organizarlo por nombres, importa que sean
					// mayúsculas, por lo que si se toman en cuenta.
						return i;
				}

			}
			// Si llega aquí es que se trata de una letra con acento, estas deben
			// la mismas prioridades que una vocal normal.
			for (int i=0;i<acentos.length;i++) {
				char aux = acentos[i];
				if(letra == aux){
					// Si se encuentra una vocal con acento, su prioridad dependerá
					// de su vocal.
					switch (letra){
						case 'á':
							i=0;
						break;
						case 'é':
							i=4;
						break;
						case 'í':
							i=8;
						break;
						case 'ó':
							i=15;
						break;
						case 'ú':
							i=21;
						break;
						case 'Á':
							i=0;
						break;
						case 'É':
							i=4;
						break;
						case 'Í':
							i=8;
						break;
						case 'Ó':
							i=15;
						break;
						case 'Ú':
							i=21;
						break;
					}
					return i;
				}
			}
			// Solo llegaría aquí si no se encontró, por lo que sería un un caractér
			// fuera del abecedario.
			return 0;
		}catch(StringIndexOutOfBoundsException excepcion){
			// Si llega a tratar de buscarse fuera de la cadena...
			return -100;
		}
	}

	public ArrayList<Alumno> nuevaLista(int ultimaInt) throws FileNotFoundException, IOException{
		
		// Este método generará una nueva lista para que que contribuya a 
		// saber si el archivo ya está terminado.
		ArrayList<Alumno> nueva = new ArrayList<>();

		// Se abre el archivo donde tiene el NUEVO ORDEN DE LOS DATOS.
		File archivoFinal = new File("./ME_FINAL_"+this.nombreArchivo+"_"+this.orden+".txt");
        FileReader contenidoFinal = new FileReader(archivoFinal);
        
        // Se requiere pasar el contenido al buffer para que pueda leerse 
        // línea por línea.
        BufferedReader buffer = new BufferedReader(contenidoFinal);

        // Se leerá la primera línea del archivo final.
        String linea = buffer.readLine();

        int lineaFin=1; /*En los archivos se comienza desde la línea 1.*/
        // Este ciclo saltará las líneas necesarias hasta que pase al siguiente "///"
        if(ultimaInt!=0){
	        for(int i=1;i<=ultimaInt;i++){
	        	linea = buffer.readLine();
	        	lineaFin+=1;
	        }
        }
        while(linea.charAt(0)!='/'){
        	lineaFin+=1;
            String[] datosAlumno = linea.split(",");
            nueva.add(new Alumno(datosAlumno[0],datosAlumno[1],datosAlumno[2]));
           	linea = buffer.readLine();
        }
		nueva.add(new Alumno("La última iteración","fue en ",""+lineaFin));
		return nueva;       
	}
	
	public boolean verificar(ArrayList<Alumno> alumnos, int modo){
		// Si está ordenada devolverá un TRUE
		// Si se espera acomodar por NOMBRES es (1), si es por APELLIDOS es (2)
		int i;
		for(i=0;i<alumnos.size()-1;i++){
			Alumno anterior = alumnos.get(i);
			Alumno alumno = alumnos.get(i+1);
			int prioridadAnterior;
			int prioridadAlumno;
			if(modo == 1){
				prioridadAnterior = prioridad(anterior.getNombre(),0,1);
				prioridadAlumno = prioridad(alumno.getNombre(),0,1);
			}else{
				prioridadAnterior = prioridad(anterior.getApellido(),0,2);
				prioridadAlumno = prioridad(alumno.getApellido(),0,2);
			}
			if(prioridadAnterior==prioridadAlumno){
				int prioridad;
				if(modo == 1){
					prioridad = resolverColision(anterior.getNombre(),alumno.getNombre(),1);
				}else{
					prioridad = resolverColision(anterior.getApellido(),alumno.getApellido(),2);
				}
				if(prioridad==2){
					// Si el segundo valor tiene mejor prioridad ante el primer valor
					// quiere decir que no está ordenada.
					return false;
				}
			}
			if(prioridadAnterior>prioridadAlumno){
				return false;
			}
		}
		return true;
	}
	
	public int resolverColision(String cadena1, String cadena2,int modo){
		// Se sabe que al menos en la primera letra (del nombre o apellido) ES IGUAL
		// Por lo que "prioridad1" y "prioridad2" SON IGUALES.

		// El modo indicará si es por NOMBRE (1) o por APELLIDO (2).
		int prioridad1;
		int prioridad2;
		if(modo == 1){
			prioridad1=prioridad(cadena1,0,1);
			prioridad2=prioridad(cadena2,0,1);
		}else{
			prioridad1=prioridad(cadena1,0,2);
			prioridad2=prioridad(cadena2,0,2);
		}

		int caracter=1; /*Indicará indice del próximo caracter que se analizará por prioridad.*/
		do{
			if(modo == 1){
				prioridad1=prioridad(cadena1,caracter,1);
				prioridad2=prioridad(cadena2,caracter,1);
			}else{
				prioridad1=prioridad(cadena1,caracter,2);
				prioridad2=prioridad(cadena2,caracter,2);
			}
			if(prioridad1<prioridad2){
				// Si el primer nombre tiene menos prioridad que el segundo
				// quiere decir que alfabéticamente va antes, por lo tanto, es el
				// ganador.
				return 1;
			}else if(prioridad1>prioridad2){
				// Si la prioridad del primer nombre es mayor quiere decir que
				// el segundo nombre va alfabéticamente antes que el otro valor.
				return 2;
			}
			if(prioridad1==-100||prioridad2==-100){
				// Si hay un momento donde se genera una excepción quiere decir que
				// jamás se encontraron diferencias y se llegó a una posición de la 
				// cadena que no existe, por lo tanto... SON IGUALES
				return 0;
			}
			caracter+=1;
		}while(prioridad1==prioridad2);
		return 0;
	}	
}