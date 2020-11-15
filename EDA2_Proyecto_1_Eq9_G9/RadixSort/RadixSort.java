package RadixSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arturo Gonzalez
 */
public class RadixSort {
    private ArrayList<Alumno> listadatos;
    
    public RadixSort(ArrayList<Alumno> listadatos){
        this.listadatos = listadatos;
    }

    /**
     * @return the listadatos
     */
    public ArrayList<Alumno> getListadatos() {
        return listadatos;
    }

    /**
     * @param listadatos the listadatos to set
     */
    public void setListadatos(ArrayList<Alumno> listadatos) {
        this.listadatos = listadatos;
    }
      
    public ArrayList<Alumno> OrdenarporNC(ArrayList<Alumno> listadatos, String ruta){
        
        //Se crean las sublistas para almecenar los numeros con sus respectivos valores significativos 
        List<Alumno> lista0 = new LinkedList<>();
        List<Alumno> lista1 = new LinkedList<>();
        List<Alumno> lista2 = new LinkedList<>();
        List<Alumno> lista3 = new LinkedList<>();
        List<Alumno> lista4 = new LinkedList<>();
        List<Alumno> lista5 = new LinkedList<>();
        List<Alumno> lista6 = new LinkedList<>();
        List<Alumno> lista7 = new LinkedList<>();
        List<Alumno> lista8 = new LinkedList<>();
        List<Alumno> lista9 = new LinkedList<>();
        ArrayList<Alumno> listads = new ArrayList<>(); 
      
        int l=0;
        char indice;
        // Se crea un string el cual guardara el elemento a comparar
        String[] aux = new String[1];
        
        System.out.println("Lista por parametro: ");
        
        // Se clona la lista original, en esta lista se realizaran los cambios de ordenamiento
        // para no alterar la lista inicial.
        listads = (ArrayList<Alumno>) listadatos.clone();
      
       //Se crean los ciclos for anidados, el primero recorre los 6 digitos del numero
       //El segundo recorre los elementos de la lista 
        for(int i=6; i>0; i--){
            for(int j=0; j<listads.size(); j++){
                
                //Se almancena el numero a comparar en la cadena auxiliar 
                aux[0] = listads.get(j).getNoCuenta();
                //elementoaguardar = listads.get(j).getNoCuenta();
                // Con el metodo chrAt nos retorna el digito significativo que se esta comparando
                indice = aux[0].charAt(i);
                //System.out.println("valor significativo: "+ indice);
                
                // El numero se almacena en su cola correspondiente 
                if (indice == '0'){
                    lista0.add(listads.get(j));
                }
                if (indice == '1'){
                    lista1.add(listads.get(j));
                }
                if (indice == '2'){
                    lista2.add(listads.get(j));
                   }
                if (indice == '3'){
                    lista3.add(listads.get(j));
                }
                if (indice == '4'){
                    lista4.add(listads.get(j));
                }
                if (indice == '5'){
                    lista5.add(listads.get(j));
                }
                if (indice == '6'){
                    lista6.add(listads.get(j));
                }
                if (indice == '7'){
                    lista7.add(listads.get(j));
                }
                if (indice == '8'){
                    lista8.add(listads.get(j));
                }
                if (indice == '9'){
                    lista9.add(listads.get(j));
                }  
            }//Termina de añadir los valores a la cola
            
            // Se borran los elementos de la lista para que los elementos no se vuelvan 
            //a escribir, y se repitan 
           
            listads.clear();
 
            for (int m = 0; m<lista0.size(); m++){
                 listads.add(lista0.get(m));
             }//Termina de vaciar cola 0
            
            for (int m = 0; m<lista1.size(); m++){
                 listads.add(lista1.get(m));
             }//Termina de vaciar cola 1
            
            for (int m = 0; m<lista2.size(); m++){
                 listads.add(lista2.get(m));
             }//Termina de vaciar cola 2
            
            for (int m = 0; m<lista3.size(); m++){
                 listads.add(lista3.get(m));
             }//Termina de vaciar cola 3
            
            for (int m = 0; m<lista4.size(); m++){
                 listads.add(lista4.get(m));
             }//Termina de vaciar cola 4
            
            for (int m = 0; m<lista5.size(); m++){
                 listads.add(lista5.get(m));
             }//Termina de vaciar cola 5
            
            for (int m = 0; m<lista6.size(); m++){
                 listads.add(lista6.get(m));
             }//Termina de vaciar cola 6
            
            for (int m = 0; m<lista7.size(); m++){
                 listads.add(lista7.get(m));
             }//Termina de vaciar cola 7
            
            for (int m = 0; m<lista8.size(); m++){
                 listads.add(lista8.get(m));
             }//Termina de vaciar cola 8
        
            for (int m = 0; m<lista9.size(); m++){
                 listads.add(lista9.get(m));
             }//Termina de vaciar cola 9
            
            lista0.clear();
            lista1.clear();
            lista2.clear();
            lista3.clear();
            lista4.clear();
            lista5.clear();
            lista6.clear();
            lista7.clear();
            lista8.clear();
            lista9.clear();
            
            System.out.println("Termina la iteracion: "+l);
            l++; 
        }
        
        //Se imprime la lista ordenada 
        System.out.println("\n\tOrdenamiento creado con exito: ");
        System.out.println("\tLa lista ordenada queda como: \n");
        for(Alumno datos1: listads){
            //System.out.println("Apellido: "+ datos1.getApellido());
            //System.out.println("Nombre: "+ datos1.getNombre());
            //System.out.println("NoCuenta: "+ datos1.getNoCuenta());
            System.out.println(datos1.getApellido()+","+datos1.getNombre()+","+datos1.getNoCuenta());
        }

        return listadatos;
    }
}
