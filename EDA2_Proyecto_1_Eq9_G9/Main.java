package EDA2_Proyecto_1_Eq9_G9;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws IOException{

		// Instancias de apoyo para el proyecto
		Util opcion = new Util();
		Ordenamientos ordenamiento = new Ordenamientos();

		// Inicio del programa
		System.out.println("##################################################################");
		System.out.println("############ BIENVENIDO A LOS ORDENAMIENTOS EXTERNOS #############");
		System.out.println("##################################################################");
		System.out.println();
		do{
			// Solicitar información del archivo que se quiere ordenar.
			String ruta, nombre;
			System.out.println("Ingrese la ruta de su archivo");
			Scanner leer = new Scanner(System.in);
			ruta = leer.nextLine();
	        
			System.out.println("Ingrese el nombre de su archivo (sin tipo de formato).");
			nombre = leer.nextLine();
			Path datos = Paths.get(ruta+"\\"+nombre+".txt");
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
					System.out.println("#             HAS ELEGIDO ORDENAR MEDIANTE: Nombres              #");
					System.out.println("##################################################################");
					ordenamiento.polifase();
				break;
				case '2':
					System.out.println("\n##################################################################");
					System.out.println("#            HAS ELIGIDO ORDENAR MEDIANTE: Apellidos             #");
					System.out.println("##################################################################");
				break;
				case '3':
					System.out.println("\n##################################################################");
					System.out.println("#         HAS ELIGIDO ORDENAR MEDIANTE: Número de cuenta         #");
					System.out.println("##################################################################");
					// Ya que se eligió por número de cuenta, se ordenará directamente por RadixSort
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
