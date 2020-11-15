
import java.util.Scanner;
public class Util{
	// Clases de apoyo
	Scanner sc = new Scanner(System.in);

	// Atributos de instancia
	private char letra;
	private String cadena;
	private int entero;
	
	// GETTERS Y SETTERS
		// 
		// Todos los setters tendrán una función para una lectura de datos
		// y validaciones para obtenter valores por defecto en caso que
		// el usuario no escriba nada al momento de elegir.
		// 
	public void setChar(){
		String aux = sc.nextLine();
		// Validación, si no añade algo a su elección toma un valor
		// por defecto.
		if(aux.isEmpty()){
			this.letra = 'S';
		}else if(aux.charAt(0) == 's'){
			this.letra = 'S';
		}else{
			this.letra = aux.charAt(0);
		}
	}
	public void setChar(char letra){
			this.letra = letra;
	}
	public char getChar(){
		return this.letra;			
	}
	public void setInt(){
		String eleccionFinal;
		String eleccion = sc.nextLine();
		while(eleccion.isEmpty()){
			System.out.printf("\n Lo sentimos, no pudo ser procesada tu solicitud, has escrito una opción incorrecta.\n Ingresa un número: \n > ");
			eleccion = sc.nextLine(); 	
		}
		if(eleccion.charAt(0)=='1'||eleccion.charAt(0)=='2') {
			eleccionFinal = eleccion.substring(0,1);
			this.entero = Integer.parseInt(eleccionFinal);
		}else{
			while(eleccion.charAt(0)!='1'&&eleccion.charAt(0)!='2'){
				System.out.printf("\n Lo sentimos, no pudo ser procesada tu solicitud, has escrito número incorrecto.\n Intenta de nuevo: \n > ");
				eleccion = sc.nextLine(); 	
			}
			eleccionFinal = eleccion.substring(0,1);
			this.entero = Integer.parseInt(eleccionFinal);
		}
	}
	public int getInt(){
		return this.entero;
	}
	public void exit(){
		this.letra='n';
		this.entero=-1;
	}
}