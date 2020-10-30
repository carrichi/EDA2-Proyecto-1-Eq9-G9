/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author Arturo Gonzalez
 */
public class Main {
    
    public static void main (String[] args) throws IOException{
    // Indicar dirección de datos dada su ruta relativa.     
    Path datos = Paths.get("./Claves.txt");
    ArrayList<Alumno> listadatos = new ArrayList<Alumno>(); 
    
    BufferedReader  brDatos = Files.newBufferedReader(datos);
    
    // Obtenemos los datos de las lineas
    Stream <String> lineasDatos = brDatos.lines();
    
    // Imprime los datos del archivo
    // lineasDatos.forEach(System.out::println);
    

    Stream <String> datosAlumnos = brDatos.lines();
    
    datosAlumnos.forEach(l -> {
        System.out.println(l+"<----");
        String[] linea = l.split(",");
        Alumno alumno = new Alumno(linea[0], linea[1], linea[2]);
        listadatos.add(alumno);
    });


    System.out.println(listadatos.toString());

    // Esto es para imprimir la lista cuando ya este separada 
       
    for(Alumno datosAlumno: listadatos){
       System.out.println("Apellido: "+ datosAlumno.getApellido());
       System.out.println("Nombre: "+ datosAlumno.getNombre());
       System.out.println("NoCuenta: "+ datosAlumno.getNoCuenta());
    }
   
    }
}