import java.util.Scanner;
public class Util{
	// Clases de apoyo
	Scanner sc = new Scanner(System.in);

	// Atributos de instancia
	private char letra;
	private String cadena;
	private int entero;
	
	// GETTERS Y SETTERS
	public void setChar(){
		this.letra = sc.nextLine().charAt(0);
	}
	public char getChar(){
		return this.letra;
	}
	public void setString(){
		this.cadena=sc.nextLine();
	}
	public String getString(){
		return this.cadena;
	}
	public void setInt(){
		this.entero = sc.nextInt();
	}
	public int getInt(){
		return this.entero;
	}
}