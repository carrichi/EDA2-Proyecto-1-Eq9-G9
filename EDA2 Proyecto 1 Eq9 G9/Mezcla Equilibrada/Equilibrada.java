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
System.out.println("TERMINÓ LA SEPARACION DE DATOS.");
System.out.println("Se añadió una interrupción de FIN en LOS AUXILIARES.");

		// Se añadirán interrupciones finales en los dos archivos auxiliares.
		añadir(ultimoLista,1,-1);
		añadir(ultimoLista,2,-1);

		/***************************************************************************/
		/*                                                                         */
		/*              INICIA FASE 2 - UNIÓN DE AUXILIARES                        */
		/*                                                                         */
		/***************************************************************************/

        	ArrayList<Alumno> listaf1 = new ArrayList<>();
        	ArrayList<Alumno> listaf2 = new ArrayList<>();
           //Crear un objeto BufferedReader al que se le pasa 
           //   un objeto FileReader con el nombre del fichero
           BufferedReader br = new BufferedReader(new FileReader("./pruebas.txt"));
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();
           //Repetir mientras no se llegue al final del fichero
        //    int interrupcion=1; /*Los archivos comienzan en la línea 1*/
        //    while(texto != null)
        //    {
        //         if(texto.charAt(0) == '>'){
        //             System.out.println("\tINTERRUPCIÓN en linea "+interrupcion);
        //         }else{
        //            System.out.println(texto);
        //            String[] alumno1 = texto.split(",");
        //            System.out.println(alumno1.length);
        //            listaf1.add(new Alumno(alumno1[0],alumno1[1],alumno1[2]));
        //         }
        //         texto = br.readLine();
        //         interrupcion+=1;
        //    }
        //    br.close();

        //    for (Alumno alumno: listaf1 ) {
        //         alumno.info();
        //    } 

		}
	}

	public void añadir(Alumno alumno, int archivo, int interrupcion) throws FileNotFoundException, IOException{
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
					f0.write(nombre+", "+apellido+ ", " +cuenta+"\n");
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
					// Se añadirá el alumno al archivo auxiliar 1.
					f1.write("/////////////////////////////////////////\n");
				break;
				case 2:
					// Se añadirá el alumno al archivo auxiliar 2.
					f2.write("/////////////////////////////////////////\n");
				break;
			}
		}
		f0.close();
		f1.close();
		f2.close();
	}	


	public void sortApellidos(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{}
	
	

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

   // UN PODEROSÍSIMO BUBBLESORT PARA CUANDO SE ORDENEN LOS VALORES.
   // void bubbleSort(int arr[]) 
   //  		{ 
   //      	int n = arr.length; 
   //      	for (int i = 0; i < n-1; i++) 
   //      	    for (int j = 0; j < n-i-1; j++) 
   //      	        if (arr[j] > arr[j+1]) 
   //      	        { 
   //      	            // swap arr[j+1] and arr[j] 
   //      	            int temp = arr[j]; 
   //      	            arr[j] = arr[j+1]; 
   //      	            arr[j+1] = temp; 
   //      	        } 
   //  		} 	
}