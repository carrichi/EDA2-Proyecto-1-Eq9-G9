package EDA2_Proyecto_1_Eq9_G9;

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
	public ArrayList<Alumno> datos() throws IOException{	
	    ArrayList<Alumno> listadatos = new ArrayList<Alumno>(); 

		// Indicar dirección de datos dada su ruta relativa.     
        Path datos = Paths.get("./claves.txt");
        
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

	public void polifase() throws IOException{
		ArrayList<Alumno> listadatos = datos();
	    Polifase polifase = new Polifase();
		polifase.sort(listadatos);
	}
	public void radixSort() throws IOException{
		ArrayList<Alumno> listadatos = datos();
	}
	public void mezclaEquilibrada(){
        

	}
}

