import java.util.Scanner;

public class Menu{
	public static void main(String[] args){
		// Limpia la pantalla 
			// Opción 1
		// try {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}catch(Exception e){}
			// OPcioón 2 (puede tener fallos)
		// final String aux= "\033[";
		// System.out.print(aux+ "2J"); 

		Scanner sc = new Scanner(System.in);

		System.out.println("##################################################################");
		System.out.println("############ BIENVENIDO A LOS ORDENAMIENTOS EXTERNOS #############");
		System.out.println("##################################################################");
		System.out.println();
		System.out.println("¿Qué tipo de ordenamiento quieres implementar?");
		System.out.println("1. Ordenamiento por Polifase. ");
		System.out.println("2. Ordenamiento por Mezcla equilibrada. ");
		System.out.println("3. Ordenamiento por Radix. ");
		System.out.printf("\nElige tu opción con el número.\n > ");
		int opcion = sc.nextInt();
		switch (opcion){
			case 1:
				System.out.println("##################################################################");
				System.out.println("############# HAS ELEGIDO ORDENAR MEDIANTE: Polifase #############");
				System.out.println("##################################################################");
			break;
			case 2:
				System.out.println("##################################################################");
				System.out.println("HAS ELIGIDO ORDENAR MEDIANTE: Mezcla equilibrada");
				System.out.println("##################################################################");			break;
			case 3:
				System.out.println("##################################################################");
				System.out.println("HAS ELIGIDO ORDENAR MEDIANTE: Radix");
				System.out.println("##################################################################");
			break;
			default:
				System.out.println("##################################################################");
				System.out.println("NO HAS ELEGIDO CORRECTAMENTE. Intenta de nuevo.");
				System.out.println("##################################################################");
			break;
		}

	}	
}