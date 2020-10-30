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
public class Proyecto {
    
    public static void main (String[] args) throws IOException{
    //ahi pones la ruta de tu archivo     
    Path datos = Paths.get("./EDA2 Proyecto 1 Eq9 G9/Claves.txt");
    ArrayList<ficheros> listadatos = new ArrayList<>(); 
    
    BufferedReader  brDatos = Files.newBufferedReader(datos);
    
    //Obtenemos los datos de las lineas
    Stream <String> lineasDatos = brDatos.lines();
    //Imprime los datos del archivo
    lineasDatos.forEach(System.out::println);
   
    
    
    
    lineasDatos.forEach(l -> {
        String[] linea = l.split(",");
        listadatos.add(new ficheros(linea[0], linea[1], linea[2]));
    });
    
    //Esto es para imprimir la lista cuando ya este separada 
       
    for(ficheros datos1: listadatos){
       System.out.println("Apellido: "+ datos1.getApellido());
       System.out.println("Nombre: "+ datos1.getNombre());
       System.out.println("NoCuenta: "+ datos1.getNoCuenta());
    }
   
    }
}