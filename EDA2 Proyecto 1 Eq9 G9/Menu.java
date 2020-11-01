import java.util.Scanner;

public class Menu{
	public static void main(String[] args){
		// Limpia la pantalla 
			// Opción 1
		// try {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}catch(Exception e){}
			// OPcioón 2 (puede tener fallos)
		// final String aux= "\033[";
		// System.out.print(aux+ "2J"); 

		Util opcion = new Util();
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
					System.out.println("##################################################################");
					System.out.println("#             HAS ELEGIDO ORDENAR MEDIANTE: Polifase             #");
					System.out.println("##################################################################");
				break;
				case '2':
					System.out.println("##################################################################");
					System.out.println("#        HAS ELIGIDO ORDENAR MEDIANTE: Mezcla equilibrada        #");
					System.out.println("##################################################################");
				break;
				case '3':
					System.out.println("##################################################################");
					System.out.println("#               HAS ELIGIDO ORDENAR MEDIANTE: Radix              #");
					System.out.println("##################################################################");
				break;
				default:
					System.out.printf("\n      ¡¡¡  NO HAS ELEGIDO CORRECTAMENTE. Intenta de nuevo.  !!!   \n > ");
				break;
			}
			if(opcion.getChar()=='1'||opcion.getChar()=='2'||opcion.getChar()=='3'){
				System.out.printf("\n\nPor el momento ya has terminado, ¿quieres probar otro método de ordenamiento externo? (S/n) Por defecto es 'S'.\n > ");
			}
			opcion.setChar();
			if((opcion.getChar()!='S')&&(opcion.getChar()!='n')){
				opcion.setChar();
			}
			System.out.println();
		}while(opcion.getChar()=='S');
	}	
}