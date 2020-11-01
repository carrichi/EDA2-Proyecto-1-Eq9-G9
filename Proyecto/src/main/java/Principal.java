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
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.stream.Stream;


/**
 *
 * @author Arturo Gonzalez
 */
public class Principal {

    //C:\Users\Arturo Gonzalez\Desktop\Practicas EDa\nombre.txt
    public static void main (String[] args) throws IOException{
    //C:\\Users\\Arturo Gonzalez\\Desktop\\Practicas EDa\\nombre.txt
    
    //Codigo para pedir la ruta del archivo
        String ruta,nombre;
        System.out.println("Ingrese la ruta de su archivo");
        Scanner leer = new Scanner(System.in);
        ruta= leer.nextLine();
        System.out.println("Ingrese el nombre de su archivo");
        nombre = leer.nextLine();
    
        // Se crean los metodos los cuales nos perminten leer el archivo y se crea la lista de datos 
        Path datos = Paths.get(ruta+"\\"+nombre+".txt");
        ArrayList<ficheros> listadatos = new ArrayList<>(); 
        BufferedReader  brDatos = Files.newBufferedReader(datos);
        EscribirEnArcihvo ca = new EscribirEnArcihvo();
    
        //Obtenemos los datos de las lineas
        Stream <String> lineasDatos = brDatos.lines();

        //Se hace un split para separar el archivo en nombre, apellido y No. Cuenta
        lineasDatos.forEach(l -> {
            
            String[] linea = l.split("\\,");  
            listadatos.add(new ficheros(linea[0],linea[1],linea[2]));  
        }); 
    
        // Instancias de apoyo para el proyecto
		Util opcion = new Util();
                Polifase ordenamientopol = new Polifase();
                 RadixSort op = new RadixSort(listadatos);
		System.out.println("##################################################################");
		System.out.println("############ BIENVENIDO A LOS ORDENAMIENTOS EXTERNOS #############");
		System.out.println("##################################################################");
		System.out.println();
		do{
			System.out.println("¿Qué tipo de ordenamiento quieres implementar?");
			System.out.println("1. Ordenamiento por Polifase. ");
			System.out.println("2. Ordenamiento por Mezcla equilibrada. ");
			System.out.println("3. Ordenamiento por Radix. ");
			System.out.println("4. Salir.");
			System.out.printf("\nElige tu opción con el número.\n > ");
			opcion.setChar();
			switch (opcion.getChar()){
				case '1':
					System.out.println("\n##################################################################");
					System.out.println("#             HAS ELEGIDO ORDENAR MEDIANTE: Polifase             #");
					System.out.println("##################################################################");
					ordenamientopol.sort(listadatos);
				break;
				case '2':
					System.out.println("\n##################################################################");
					System.out.println("#        HAS ELIGIDO ORDENAR MEDIANTE: Mezcla equilibrada        #");
					System.out.println("##################################################################");
				break;
				case '3':
					System.out.println("\n##################################################################");
					System.out.println("#               HAS ELIGIDO ORDENAR MEDIANTE: Radix              #");
					System.out.println("##################################################################");
                    op.OrdenarporNC(listadatos,ruta);                    
				break;
				case '4':
					opcion.exit();
				break;
				default:
					System.out.printf("\n      ¡¡¡  NO HAS ELEGIDO CORRECTAMENTE. Intenta de nuevo.  !!!  ");
					// Se asigna una opción que hará que se pueden repetir las opciones del menú nuevamente.
					opcion.setChar('S');
					System.out.println();
				break;
			}
			if(opcion.getChar()=='1'||opcion.getChar()=='2'||opcion.getChar()=='3'){
				System.out.printf("\n\nPor el momento ya has terminado, ¿quieres probar otro método de ordenamiento externo? (S/n) Por defecto es 'S'.\n > ");
				opcion.setChar();
				while(opcion.getChar()!='S'&&opcion.getChar()!='n'){
					System.out.printf("\n¡Vaya! No has elegido ninguna opción válida, intenta otra vez.\n > ");
					opcion.setChar();
				}
			}
			System.out.println();
		}while(opcion.getChar()=='S');

		// El usuario ha decidido salir del programa.
		System.out.println("\t-> HAS ELEGIDO SALIR");
		System.out.println();
		System.out.println("\t _________________________________________________");
		System.out.println("\t| Gracias por utilizar el programa.               |");
		System.out.println("\t|                                                 |");
		System.out.println("\t| Colaboradores del proyecto:                     |");
		System.out.println("\t|    -> Carrichi de la Cruz, Roberto Carlos       |");
		System.out.println("\t|    -> Gonzalez Cuellar, Arturo                  |");
		System.out.println("\t|    -> Miranda Bueno, Fátima Yolanda             |");
		System.out.println("\t|                                                 |");
		System.out.println("\t| Estudiantes de la Facultad de Ingeniería, UNAM. |");
		System.out.println("\t|_________________________________________________|");
		System.out.println();
    }
}   
    