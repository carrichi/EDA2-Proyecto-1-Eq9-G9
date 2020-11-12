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
	public void sortNombres(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{
		// El archivo donde se comenzará a añadir será en f1
		int archivo = 1;
		for(int i=1;i<alumnos.size()-1;i++){
			Alumno anterior = alumnos.get(i-1);
			Alumno alumno = alumnos.get(i);
			Alumno siguiente = alumnos.get(i+1);
			System.out.println("Act: "+prioridad(alumno.getNombre())+" Sig: "+prioridad(siguiente.getNombre()));
			if(prioridad(alumno.getNombre())<=prioridad(siguiente.getNombre())){
				// Ya que el valor siguiente matiene un orden ascendente se guarda
				// en el archivo que corresponde en ese momento.
				System.out.println(alumno.getNombre()+ " se añade a f"+archivo);
				// añadir(alumno,archivo,0);
			}else{
				// Quiere decir que el alumno siguiente rompe el orden ascendente por lo que
				// el usuario se añadirá al archivo contrario.

				// Antes de hacer el cambio de archivo se imprime una interrupción en
				// el archivo actual, para que se sepa que hubo una ruptura en el orden.
				// El número 1 indica que fue solicitada una interrupción.
				// añadir(alumno,archivo,1);
				System.out.println("Se añadió una interrupción a f"+archivo);
				// Si el anterior estado era el archivo f1 se cambia, si no, se mantiene.
				if(archivo==1){
					archivo=2;
				}else{
					archivo=1;
				}
				System.out.println(alumno.getNombre()+ " se añade a f"+archivo);
				// añadir(alumno,archivo,0);
				System.out.println(alumno.getNombre()+" se añadió en "+archivo);
			}
		}
	}

	public void añadir(Alumno alumno, int archivo, int interrupcion) throws FileNotFoundException, IOException{

		// try{
		
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
					f0.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}else if (archivo == 1){
				// Imprimir interrupcion en el archivo 1.
					f1.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			}else{
				// Imprimir interrupcion en el archivo 2.
					f2.write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
			}
			}else{
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
			}
			f0.close();
			f1.close();
			f2.close();
		// }catch(Exception e){
		//     e.printStackTrace();
		// }
	}	






	public void sortApellidos(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{}
	public int prioridad(String cadena){
		// La prioridad de elección se realizará mediante el primer caracter del nombre
		// y se devolverá el índice, entre menor sea el índice, hay mayor prioridad.
		String mayusculas[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String minusculas[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		char letra = cadena.charAt(0);
		for(int i=0;i<mayusculas.length;i++){
			char aux = mayusculas[i].charAt(0);
			if(letra == aux){
				// Retorna la prioridad dada por el índice.
				return i;
			}
		}
		for (int i=0;i<minusculas.length;i++) {
			char aux = mayusculas[i].charAt(0);
			if(letra == aux){
				// La prioridad será siempre menor a las mayúsculas.
				return i+26;
			}				
		}
		return 0;
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