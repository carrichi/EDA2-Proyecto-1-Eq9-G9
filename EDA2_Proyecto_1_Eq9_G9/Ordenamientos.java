
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.util.stream.Stream;

public class Ordenamientos{
	String ruta;

	public Ordenamientos(String ruta){
		this.ruta = ruta;
	}

	public ArrayList<Alumno> datos(String ruta)  throws FileNotFoundException, IOException{	
	    ArrayList<Alumno> listadatos = new ArrayList<Alumno>(); 

		// Indicar dirección de datos dada su ruta relativa.     
        Path datos = Paths.get(ruta);
        
        BufferedReader brDatos = Files.newBufferedReader(datos);
        
        // Obtenemos los datos de las lineas
        Stream <String> lineasDatos = brDatos.lines();
        
        // Imprime los datos del archivo
        // lineasDatos.forEach(System.out::println);

        Stream <String> datosAlumnos = brDatos.lines();
        
        datosAlumnos.forEach(l -> {
            String[] linea = l.split("\\,");
            Alumno alumno = new Alumno(linea[0], linea[1], linea[2]);
            listadatos.add(alumno);
        });

        return listadatos;
	}

	public void polifase() throws FileNotFoundException, IOException{
		ArrayList<Alumno> listadatos = datos(this.ruta);
	    Polifase polifase = new Polifase();
		polifase.sort(listadatos);
	}
	public void radixSort() throws FileNotFoundException, IOException{
		ArrayList<Alumno> listadatos = datos(this.ruta);
	}
	public void mezclaEquilibrada(int modo)throws FileNotFoundException, IOException{
		
		ArrayList<Alumno> listadatos = datos(this.ruta);
// print(listadatos);
		MezclaEquilibrada mezcla = new MezclaEquilibrada();
		switch (modo) {
			case 1:
				mezcla.sortNombres(listadatos,0,0,0);
			/*						    ^     ^ ^ ^ 
										|     | | |	
										|     | | Indica la línea donde iniciará a leer en el archivo FINAL.	
										|     | Indica la línea por donde iniciará a leer el segundo archivo auxiliar.	
										|     Indica la línea donde se iniciará a leer en el primer archivo auxiliar.
										En esta campo se ingresa la lista que deberá ser ingresada.
			*/
			break;
			case 2:
				mezcla.sortApellidos(listadatos,0,0,0);
			break;
		}

	}
	public void print(ArrayList<Alumno> alumnos){
		for (Alumno alumno: alumnos) {
			alumno.info();
		}
	}
}

