
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

		// Indicar dirección de datos dada su ruta relativa o absoluta.
        Path datos = Paths.get(ruta);
        
        // Todo el contenido del archivo en cuestión se almacenará
        // en el buffer.
        BufferedReader brDatos = Files.newBufferedReader(datos);
        
        // Se creará una secuencia de elementos "Stream" con los datos
        // leídos por el buffer.
        Stream <String> datosAlumnos = brDatos.lines();
        
        // Es una implementación de programación funcional.
        // Aquí se ejecuta una función anónima que en este caso separa
        // cada línea del archivo para dividirla y de la infomación
        // obtenida crear un objeto de tipo "Alumno", el cual se añadirá
        // a la lista que contenga a todos los alumnos.
        datosAlumnos.forEach(l -> {
            String[] linea = l.split("\\,");
            Alumno alumno = new Alumno(linea[0], linea[1], linea[2]);
            listadatos.add(alumno);
        });

        return listadatos;
	}

	public void polifase(String nombre, char opcion) throws FileNotFoundException, IOException{
		ArrayList<Alumno> listadatos = datos(this.ruta);
	    Polifase polifase = new Polifase();
		polifase.sort(listadatos,nombre,opcion);
	}

	public void radixSort() throws FileNotFoundException, IOException{
		ArrayList<Alumno> listadatos = datos(this.ruta);
	}

	public void mezclaEquilibrada(String nombreArchivo, int modo)throws FileNotFoundException, IOException{
		
		ArrayList<Alumno> listadatos = datos(this.ruta);
		MezclaEquilibrada mezcla = new MezclaEquilibrada(nombreArchivo);
		switch (modo) {
			case 1:
				// Antes de ordenar, se debe indicar que se quiere
				// ordenar por nombres. 
				mezcla.setOrden("Nombres");
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
				mezcla.setOrden("Apellidos");
				mezcla.sortApellidos(listadatos,0,0,0);
			break;
		}

	}
}

