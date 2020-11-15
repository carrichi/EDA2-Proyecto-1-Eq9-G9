package EDA2_Proyecto_1_Eq9_G9;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Menu{
	public static void main(String[] args) throws IOException{

		// Instancias de apoyo para el proyecto
		Util opcion = new Util();
		Ordenamientos ordenamiento = new Ordenamientos();
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
					ordenamiento.polifase();
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
		System.out.println("\t|    -> Miranda Bueno, Fátima Yolanda             |");
		System.out.println("\t|    -> Páez López, Didier Marcelo                |");
		System.out.println("\t|                                                 |");
		System.out.println("\t| Estudiantes de la Facultad de Ingeniería, UNAM. |");
		System.out.println("\t|_________________________________________________|");
		System.out.println();
	}	
}