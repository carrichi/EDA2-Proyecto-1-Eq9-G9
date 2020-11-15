import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Equilibrada{
	public boolean verificar(ArrayList<Alumno> alumnos){
		// Si está ordenada devolverá un TRUE
		int i;
		for(i=0;i<alumnos.size()-1;i++){
			Alumno anterior = alumnos.get(i);
			Alumno alumno = alumnos.get(i+1);
			if(prioridad(anterior.getNombre(),0)==prioridad(alumno.getNombre(),0)){
				int prioridad = resolverColision(anterior.getNombre(),alumno.getNombre());
				if(prioridad==2){
					// Si el segundo valor tiene mejor prioridad ante el primer valor
					// quiere decir que no está ordenada.
					return false;
				}
			}
			if(prioridad(anterior.getNombre(),0)>prioridad(alumno.getNombre(),0)){
				return false;
			}
		}
		return true;
	}
	public int resolverColision(String nombre1, String nombre2){
		// Se sabe que al menos en la primera letra (del nombre o apellido) ES IGUAL
		// Por lo que "prioridad1" y "prioridad2" SON IGUALES.
		int prioridad1=prioridad(nombre1,0);
		int prioridad2=prioridad(nombre2,0);
		int caracter=1; /*Indicará indice del próximo caracter que se analizará por prioridad.*/
		do{
			prioridad1=prioridad(nombre1,caracter);
			prioridad2=prioridad(nombre2,caracter);
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
	public void sortNombres(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{
		// Primero se verificará si están ordenados los datos que llegaron.
		if(verificar(alumnos)){
			System.out.println("¡Ya está ordenada!");
		}else{
			// separarArchivo(alumnos);
			mezclarArchivo();
		}
	}
	
	public void mezclarArchivo() throws FileNotFoundException, IOException{

		/***************************************************************************/
		/*                                                                         */
		/*              INICIA FASE 2 - UNIÓN DE AUXILIARES                        */
		/*                                                                         */
		/***************************************************************************/

		// Se crearán dos listas que guardarán temporalmente los datos hasta que 
		// encuentren cierta cantidad de interrupciones. 
	    ArrayList<Alumno> listaf1 = new ArrayList<>();
	    ArrayList<Alumno> listaf2 = new ArrayList<>();
	          
	    // Guardamos todo el contenido del archivo en el buffer del primer archivo
	    // auxiliar.
        BufferedReader f1 = new BufferedReader(new FileReader("./Auxiliar1.txt"));
        BufferedReader f2 = new BufferedReader(new FileReader("./Auxiliar2.txt"));
        
        // Se almacena todo el contenido del archivo en una variable para después
        // separarla.
        String contenidoA1 = f1.readLine();
        String contenidoA2 = f2.readLine();

        int interrupcionesA1=0;
        int interrupcionesA2=0;

        int mezclas=1; /*Es la primera mezcla que se realiza.*/
        for (int i=0;i<mezclas;i++) {
        // while (contenidoA2.charAt(0)!='/'){
        		
        	// Llenar la lista 1 hasta que encuentre una interrupción.
	        while (contenidoA1.charAt(0) != '>'){
        		String[] datos = contenidoA1.split(",");
        		listaf1.add(new Alumno(datos[0],datos[1],datos[2]));
	        	contenidoA1 = f1.readLine();
	        	// Verifica si la nueva línea que acaba de leer es una interrupción.
	        	if(contenidoA1.charAt(0) == '>'){
	        		// Ya que se trata de una interrupción, se añade al contador del
	        		// archivo en cuestión.
        			interrupcionesA1+=1;
	        	}
	        }

	        while (contenidoA2.charAt(0) != '>'){
        		String[] datos = contenidoA2.split(",");
        		listaf2.add(new Alumno(datos[0],datos[1],datos[2]));
	        	contenidoA2 = f2.readLine();
	        	if(contenidoA2.charAt(0) == '>'){
        			interrupcionesA2+=1;
	        	}
	        }

	        // Ya que se tienen las dos listas hasta la primera interrupción, 
	        // se mezclan y se ordenan, el resultado se imprime en archivo final
	        // en este caso será en un archivo FINAL.txt, para que no se sobreescriban
	        // las claves.

	        ArrayList<Alumno> listaf0 = new ArrayList<>();
	        for (Alumno alumno : listaf1) {
	        	listaf0.add(alumno);
	        }
	        for (Alumno alumno : listaf2) {
	        	listaf0.add(alumno);
	        }

	        // YA SE MEZCLARON LAS LISTAS, FALTA ORDENARLAS
	        ArrayList<Alumno> listaOrdenada = ordenar(listaf0);
	        // Ya que se ordenaron, deben añadirse al archivo final.
	        for (Alumno alumno : listaOrdenada) {
	        	añadir(alumno, 0, 0);
	        	/*       ^     ^  ^
	        	         |     |  |
	        	         |     | Indica que se añadirá SIN INTERRUPCIÓN
	        	         |     |  
	        	         |   Indica que se añadirá en el archivo FINAL 
	        	         |       
	        	        Indica que alumno se añadirá.
	        	*/
	        }

	        // Ya que se se mezclaron, comenzará la mezcla número 2
	        // mezclas+=1; 
        }
// Cantidad de interrupciones alcanzadas.
System.out.println("A1="+interrupcionesA1);
System.out.println("A2="+interrupcionesA2);
System.out.println("Mezclas="+mezclas);
	    f1.close();
	    f2.close();
	}
	public ArrayList<Alumno> ordenar(ArrayList<Alumno> alumnos){
       	for (int i = 0; i < alumnos.size()-1; i++) {
       	    for (int j = 0; j < alumnos.size()-i-1; j++) {
       	    	int prioridadAlu1 = prioridad(alumnos.get(j).getNombre(),0);
       	    	int prioridadAlu2 = prioridad(alumnos.get(j+1).getNombre(),0);
       	        if(prioridadAlu1==prioridadAlu2){
       	        	// Si tienen la misma prioridad hay una COLISIÓN.
       	        	int ganador = resolverColision(alumnos.get(j).getNombre(),alumnos.get(j+1).getNombre());
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
	public void printInt(int[] prioridades){
		System.out.println();
		for (int i = 0;i<prioridades.length ;i++ ) {
			System.out.printf("[ "+prioridades[i]+" ]");
		}
		System.out.println();
	}
	public void print(ArrayList<Alumno> alumnos){
		for (Alumno alumno : alumnos) {
			alumno.info();
		}
	}

	// Este método servirá para poder identificar si se están leyendo los valores en orden
	// y posteriormente para poder ordenarlos.
	public int prioridad(String cadena, int indice){
		try{
			// La prioridad de elección se realizará mediante el primer caracter del nombre
			// y se devolverá el índice, entre menor sea el índice, hay mayor prioridad.
			char mayusculas[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			char minusculas[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
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
					// La prioridad será siempre menor a las mayúsculas.
					return i+26;
				}				
			}
			return 0;
		}catch(StringIndexOutOfBoundsException excepcion){
			// Si llega a tratar de buscarse fuera de la cadena...
			return -100;
		}
	}


	public void separarArchivo(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{

		/***************************************************************************/
		/*                                                                         */
		/*              INICIA FASE 1 - SEPARACIÓN EN AUXILIARES                   */
		/*                                                                         */
		/***************************************************************************/

		// El archivo donde se comenzará a añadir será en f1
		int archivo = 1;
		int i;
		for(i=0;i<alumnos.size()-1;i++){
			Alumno anterior = alumnos.get(i);
			Alumno alumno = alumnos.get(i+1);
			if(prioridad(anterior.getNombre(),0)==prioridad(alumno.getNombre(),0)){
				int prioridad = resolverColision(anterior.getNombre(),alumno.getNombre());
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
			}
			else if(prioridad(anterior.getNombre(),0)<prioridad(alumno.getNombre(),0)){
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

	public void añadir(Alumno alumno, int archivo, int interrupcion) throws FileNotFoundException, IOException{
		// El valor del tercer argumento indica que tipo de interrupción se añadirá en
		// el archivo.
		// 0 -> No solicita interrupción, todo continúa con normalidad.
		// 1 -> Solicita una interrupción normal >>>>>>>>
		// 2 -> Solicita una interrupción de FIN DE ITERACIÓN. //////
		File archivoFinal = new File("./FINAL.txt");
		File auxiliar1 = new File("./Auxiliar1.txt");
		File auxiliar2 = new File("./Auxiliar2.txt");
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
					f0.write(nombre+", "+apellido+ ", " +cuenta+"\n");
				break;
				case 1:
					// Se añadirá el alumno al archivo auxiliar 1.
					f1.write(nombre+", "+apellido+ ", " +cuenta+"\n");
				break;
				case 2:
					// Se añadirá el alumno al archivo auxiliar 2.
					f2.write(nombre+", "+apellido+ ", " +cuenta+"\n");
				break;
			}
		}else{
			switch (archivo){
				case 0:
					f0.write("/////////////////////////////////////////\n");
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

	public void sortApellidos(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{}
 	
}