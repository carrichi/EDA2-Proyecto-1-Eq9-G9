/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author Arturo Gonzalez
 */
public class Util {
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
	public void setString(){
		String aux = sc.nextLine();
		// Verificar que no se trate de una cadena vacía, 
		// continuará el programa hasta que se añada algo correcto.
		while(aux.isEmpty()){
			System.out.println("Lo sentimos, no pudo ser procesada tu solicitud ya que enviaste una cadena vacía.");
			System.out.printf("\nInténtalo de nuevo: \n > ");
			aux = sc.nextLine();
		}
		this.cadena=sc.nextLine();
	}
	public String getString(){
		return this.cadena;
	}
	public void setInt(){
		int aux = sc.nextInt();
		while(aux<=0){
			System.out.println("Lo sentimos, no pudo ser procesada tu solicitud ya que enviaste una cadena vacía.");
			
		}
		this.entero = aux;
	}
	public int getInt(){
		return this.entero;
	}
	public void exit(){
		this.letra='n';
		this.cadena="exit";
		this.entero=-1;
	}
}
