


/// EN CONSTRUCCION 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arturo Gonzalez
 */
public class EscribirEnArchivo {
    
    private String ruta;
    
    public EscribirEnArchivo() {
       
    }
    
    public String escribir(String ruta){
        File archivo;
        PrintWriter escribir; // Para escribir en el archivo
        
        archivo = new File(ruta+"\\OrdenacionPorNC.txt");
        
        if(!archivo.exists()){
            try {
                archivo.createNewFile();
            } catch (Exception e) {
            }
            
        }else{
            try {
                escribir = new PrintWriter(archivo,"utf-8");
                escribir.println("HOLA, como estas");
                escribir.close();
                
            } catch (Exception e) {
            }
            
        }
        return ruta;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    
}

/*  
//Codigo para crear el archivo final
     
      File archivo;
      PrintWriter escribir;
      archivo = new File(ruta+"\\OrdenacionPorNC.txt");
      
      if(!archivo.exists()){
            try {
                archivo.createNewFile();
            } catch (Exception e) {
            }
            
        }else{
            try {
                escribir = new PrintWriter(archivo,"utf-8");
                escribir.println("HOLA");
                escribir.close();
                
            } catch (Exception e) {
            }
            
        }
      System.out.println("ArchivoCreado");




*/