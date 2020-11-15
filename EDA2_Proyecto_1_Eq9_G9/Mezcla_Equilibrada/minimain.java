import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.stream.Stream;

public class minimain{
	public static void main(String[] args) throws IOException{
		Equilibrada mezcla = new Equilibrada();

	    ArrayList<Alumno> listadatos = new ArrayList<Alumno>(); 

		// Indicar dirección de datos dada su ruta relativa.     
        Path datos = Paths.get("./claves.txt");
        // Path datos = Paths.get("./claves.txt");
        BufferedReader brDatos = Files.newBufferedReader(datos);
        
        // Obtenermos los datos guardados en el buffer
        Stream <String> datosAlumnos = brDatos.lines();
        
        datosAlumnos.forEach(l -> {
            String[] linea = l.split("\\,");
            Alumno alumno = new Alumno(linea[0], linea[1], linea[2]);
            listadatos.add(alumno);
        });

        // El 0 hace referencia a que quiere ordenarlos por nombre.
        mezcla.sortApellidos(listadatos,0,0,0);
	}
}