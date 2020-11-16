import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws IOException{
		String reset="\033[0m";
		String negrita="\033[1m";
		String amarillo="\033[38;5;11m";
		String azul="\033[1;34m";
		String blanco="\033[1;36m";
		String azulclaro="\033[1;95m";
		String morado="\033[0;35m";
		String verde="\033[1;32m";
		String rojo="\033[0;31m";
		String cyan="\033[0;36m";

		// Instancias de apoyo para el proyecto
		Util opcion = new Util();

		// Inicio del programa
		System.out.println(blanco+"\n\n##################################################################");
		System.out.println("############"+negrita+azulclaro+" BIENVENIDO A LOS ORDENAMIENTOS EXTERNOS"+blanco+" #############");
		System.out.println("##################################################################"+reset);
		System.out.println();
		do{
			// Solicitar información del archivo que se quiere ordenar.
			System.out.printf(blanco+" \n Ingresa la ruta a la carpeta de su archivo: (puede ser "+negrita+amarillo+"relativa o absoluta"+blanco+")\n > "+amarillo);
			Scanner leer = new Scanner(System.in);
			String ruta = leer.nextLine();
	        
			System.out.printf(blanco+" \nIngrese el nombre de su archivo ("+negrita+amarillo+"sin tipo de formato"+blanco+")\n > "+amarillo);
			String nombre = leer.nextLine();
			
			// Si se utiliza Windows, descomenta las siguientes dos líneas.
			
			// Path datos = Paths.get(ruta+"\\"+nombre+".txt");
			// System.out.println(blanco+"\n La ruta ingresada es: "+negrita+azulclaro+datos.toString()+reset);
			// Se se utiliza Linux, se utilizan las siguientes dos líneas.
			ruta = ruta+"/"+nombre+".txt";
			System.out.println(blanco+"\n La ruta ingresada es: "+negrita+azulclaro+ruta+reset);

			File archivo = new File(ruta);
			if (!archivo.exists()) {
			    System.out.println("\n\t"+negrita+amarillo+" ¡¡No existe el archivo !!");
			    System.out.println(blanco+" Empecemos otra vez... ");
			    opcion.setChar('S');
			}else{
				System.out.println("\t"+morado+"Si existe"+blanco+", podemos continuar.");
				
				Ordenamientos ordenamiento = new Ordenamientos(ruta);

				System.out.println("¿Qué tipo de ordenamiento quieres implementar?");
				System.out.println(" "+azulclaro+"1"+blanco+". Ordenamiento por nombres ");
				System.out.println(" "+azulclaro+"2"+blanco+". Ordenamiento por apellidos ");
				System.out.println(" "+azulclaro+"3"+blanco+". Ordenamiento por números de cuenta ");
				System.out.println(" "+azulclaro+"4"+blanco+". Salir");
				System.out.printf("\nElige tu opción con el número.\n > "+amarillo);
				opcion.setChar();
				switch (opcion.getChar()){
					case '1':
					    System.out.println("\n "+blanco+"##################################################################");
						System.out.println(" "+blanco+"#             HAS ELEGIDO ORDENAR MEDIANTE: "+morado+"NOMBRES   "+blanco+"           #");
						System.out.println(" "+blanco+"##################################################################");
						System.out.printf("\n "+blanco+"Mediante que método deseas acomodar los nombres?\n "+azulclaro+"1."+blanco+" Polifase \n "+azulclaro+"2."+blanco+" Mezcla Equilibrada\n > ");
						opcion.setInt();
						switch (opcion.getInt()) {
							case 1:
								System.out.println("");
								ordenamiento.polifase(nombre,'1');
							break;
							case 2:
								System.out.println("");
								ordenamiento.mezclaEquilibrada(nombre,1);
							break;
						}
					break;
					case '2':
						System.out.println("\n "+blanco+"##################################################################");
						System.out.println(" "+blanco+"#            HAS ELIGIDO ORDENAR MEDIANTE: "+morado+"APELLIDOS"+blanco+"             #");
						System.out.println(" "+blanco+"##################################################################");
						System.out.printf("\n Mediante que método deseas acomodar los nombres?\n "+azulclaro+"1."+blanco+" Polifase \n "+azulclaro+"2."+blanco+" Mezcla Equilibrada\n > ");
						opcion.setInt();
						switch (opcion.getInt()) {
							case 1:
								System.out.println("");
								ordenamiento.polifase(nombre,'2');
								break;
							case 2:
								System.out.println("");
								ordenamiento.mezclaEquilibrada(nombre,2);
							break;
						}
					break;
					case '3':
						System.out.println("\n "+blanco+"##################################################################");
						System.out.println(" "+blanco+"#         HAS ELIGIDO ORDENAR MEDIANTE: "+morado+"NÚMERO DE CUENTA"+blanco+"         #");
						System.out.println(" "+blanco+"##################################################################");
						// Ya que se eligió por número de cuenta, se ordenará directamente por RadixSort
						System.out.println("");
						ordenamiento.radixSort(ruta);
					break;
					case '4':
						opcion.exit();
					break;
					default:
						System.out.printf("\n      "+rojo+"¡¡¡"+blanco+"  NO HAS ELEGIDO CORRECTAMENTE. Intenta de nuevo.  "+rojo+"!!!"+blanco+"  ");
						// Se asigna una opción que hará que se pueden repetir las opciones del menú nuevamente.
						opcion.setChar('S');
						System.out.println();
					break;
				}
			}
			if(opcion.getChar()=='1'||opcion.getChar()=='2'||opcion.getChar()=='3'){
				System.out.printf("\n\n"+blanco+"Por el momento ya has terminado, ¿quieres probar otro método de ordenamiento externo? ("+amarillo+"S"+blanco+"/n)\n > ");
				opcion.setChar();
				while(opcion.getChar()!='S'&&opcion.getChar()!='n'){
					System.out.printf("\n"+amarillo+"¡Vaya! "+blanco+"No has elegido ninguna opción válida, intenta otra vez.\n > ");
					opcion.setChar();
				}
			}
			System.out.println();
		}while(opcion.getChar()=='S');

		// El usuario ha decidido salir del programa.
		System.out.println("\t-> "+azulclaro+"HAS ELEGIDO SALIR");
		System.out.println();
		System.out.println("\t "+blanco+"_________________________________________________");
		System.out.println("\t"+blanco+"| Gracias por utilizar el programa.               |");
		System.out.println("\t"+blanco+"|                                                 |");
		System.out.println("\t"+blanco+"| Colaboradores del proyecto:                     |");
		System.out.println("\t"+blanco+"|    "+amarillo+"-> "+morado+"Carrichi de la Cruz, Roberto Carlos "+blanco+"      |");
		System.out.println("\t"+blanco+"|    "+amarillo+"-> "+morado+"Gonzalez Cuellar, Arturo"+blanco+"                  |");
		System.out.println("\t"+blanco+"|    "+amarillo+"-> "+morado+"Miranda Bueno, Fátima Yolanda "+blanco+"            |");
		System.out.println("\t"+blanco+"|                                                 |");
		System.out.println("\t"+blanco+"| Estudiantes de la Facultad de Ingeniería, "+amarillo+"UNAM."+blanco+" |");
		System.out.println("\t"+blanco+"|_________________________________________________|");
		System.out.println();
	}	
}
