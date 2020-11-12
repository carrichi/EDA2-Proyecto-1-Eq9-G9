import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;

public class Equilibrada{
	public void sortNombres(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{
		FileWriter f1 = new FileWriter("./Auxiliar1");
		FileWriter f2 = new FileWriter("./Auxiliar2");
		
			// El archivo donde se comenzará a añadir será en f1
		int archivo = 1;
		for(int i=0;i<alumnos.size()-1;i++){
			Alumno alumno = alumnos.get(i);
			Alumno siguiente = alumnos.get(i+1);
			System.out.println("Act: "+prioridad(alumno.getNombre())+" Sig: "+prioridad(siguiente.getNombre()));
			if(prioridad(alumno.getNombre())<=prioridad(siguiente.getNombre())){
				System.out.println(alumno.getNombre()+ " se añade a f"+archivo);
			}else{
				System.out.println("Cambio de archivooooo.");
				
				// Si el anterior estado era el archivo f1
				if(archivo==1){
					archivo=2;
				}else{
					archivo=1;
				}
				System.out.println(alumno.getNombre()+ " se añade a f"+archivo);
			}
		}
	}
	public void sortApellidos(ArrayList<Alumno> alumnos) throws FileNotFoundException, IOException{
		FileWriter f1 = new FileWriter("./Auxiliar1");
		FileWriter f2 = new FileWriter("./Auxiliar2");
		for(Alumno alumno: alumnos){
			System.out.println("Soy "+alumno.getNombre());
		}
	}
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











	public void print(String ruta) throws FileNotFoundException, IOException{
		String line;
		FileReader archivo = new FileReader(ruta);
		BufferedReader buffer = new BufferedReader(archivo);
		int contador=1;
		while ((line = buffer.readLine())!=null){
			System.out.println(" "+contador+" "+line);
			contador+=1;
		}
		buffer.close();
	}


}