
// Bibliotecas de apoyo
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Polifase{
	public void sort(ArrayList<Alumno> listadatos,String nombre){
		
		// Arreglo que almacena de forma temporal los bloques de n=7 para ordenarlos con Algoritmo de Ordenamiento Interno
		ArrayList<Alumno> AOI = new ArrayList<Alumno>(); 
		
		// Los archivos seran listas
		ArrayList<Alumno> f0 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f1 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f2 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f3 = new ArrayList<Alumno>();
		Scanner scan=new Scanner(System.in);
		
		try{
			System.out.println("\nOrdenar por:\n1. Apellidos \n2. Nombre");
			System.out.print("> ");
			int opc=scan.nextInt();
			scan.nextLine();
			FileWriter iteraciones;
			if(opc==1){
				iteraciones=new FileWriter("./iteracionesPolifase_Apellido_"+nombre+".txt");
				System.out.println("\n\t\t\t****FIN****\n\nLas iteraciones se encuantran en la carpeta actual, en el archivo: \n\titeracionesPolifase_Apellido_"+nombre+".txt");
			}else if(opc==2){
				iteraciones=new FileWriter("./iteracionesPolifase_Nombre_"+nombre+".txt");
				System.out.println("\n\t\t\t****FIN****\n\nLas iteraciones se encuantran en la carpeta actual, en el archivo: \n\titeracionesPolifase_Nombre_"+nombre+".txt");
				}else
					iteraciones=new FileWriter("./ERROR.txt");
			f0=listadatos;
			int i=0,k,n=7,j=0;
			//Alumno index;
			iteraciones.write("***Fase 1***\n");
		    //******FASE 1, leer archivo original "f0", y dividir en bloques de n=7, ordenar cada bloque y colocar uno y uno en f1 y f2
		
			while(f0.isEmpty()!=true){
				i=0;
				insertionSort(f0,AOI,i,n);
				iteraciones.write("F1\n");
				for(Alumno alu:AOI){
					f1.add(alu);
					iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
				}
				AOI.clear();
				i=0;
				insertionSort(f0,AOI,i,n);
				iteraciones.write("F2\n");
				for(Alumno alu:AOI){
					f2.add(alu);
					iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
				}
				AOI.clear();
			}//fin fase 1
			iteraciones.write("\nFase 1/F1\n\n");
			imprimirDocumento(f1,iteraciones);
	
			iteraciones.write("\nFase 1/F2\n\n");
			imprimirDocumento(f2,iteraciones);
			
			//*****FASE 2*******
			iteraciones.write("\n\n***Fase 2***\n");
			while((f1.isEmpty()!=true&&f2.isEmpty()!=true)||(f0.isEmpty()!=true&&f3.isEmpty()!=true)){
				iteraciones.write("\nF1-F2 --> F0-F3\n");
				while(f1.isEmpty()!=true||f2.isEmpty()!=true){
					noSe(f0,f1,f2,n);
					iteraciones.write("\nF0\n");
					imprimirDocumento(f0,iteraciones);
					noSe(f3,f1,f2,n);
					iteraciones.write("\nF3\n");
					imprimirDocumento(f3,iteraciones);
				}
				n*=2;
				iteraciones.write("\nF0-F3 --> F1-F2\n");
				//pasar a f1 y f2 de f0 y f3
				while(f0.isEmpty()!=true||f3.isEmpty()!=true){
					noSe(f1,f0,f3,n);
					iteraciones.write("\nF1\n");
					imprimirDocumento(f1,iteraciones);
					
					noSe(f2,f0,f3,n);
					iteraciones.write("\nF2\n");
					imprimirDocumento(f2,iteraciones);
				}
				n*=2;
			}
		
			try{
				FileWriter archivoFinal;
				if(opc==1){
					archivoFinal=new FileWriter("./archivoFinalPolifase_Apellido_"+nombre+".txt");
					System.out.println("\nTu archivo final (ordenado) está en:\n\tarchivoFinal_Apellido_"+nombre+".txt");
				}else if(opc==2){
					archivoFinal=new FileWriter("./archivoFinalPolifase_Nombre_"+nombre+".txt");
					System.out.println("\nTu archivo final (ordenado) está en:\n\tarchivoFinal_Nombre_"+nombre+".txt");
					}else
						archivoFinal=new FileWriter("./ERROR.txt");
				archivoFinal.write("\t***Polifase***\n");
				if(f0.isEmpty()!=true)
					imprimirDocumento(f0,archivoFinal);
				else
					imprimirDocumento(f1,archivoFinal);
				archivoFinal.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		
			iteraciones.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	void insertionSort(ArrayList<Alumno> f,ArrayList<Alumno> AOI,int i,int n){
		Alumno index;
		int k;
		while(f.isEmpty()!=true&&i<n){
			AOI.add(f.remove(0));
			index=AOI.get(i);
			k=i-1;
			while(k>-1&&(AOI.get(k).getNombre().compareTo(index.getNombre()))>0){
				AOI.set(k+1,AOI.get(k));
				k--;
			}
			AOI.set(k+1,index);
			i++;
		}
	}	
	
	void noSe(ArrayList<Alumno> f,ArrayList<Alumno> fl,ArrayList<Alumno> fr,int n){
		int i=0;
		int j=0;
		while((fl.isEmpty()!=true||fr.isEmpty()!=true)&&(i<n||j<n)){
			if(fl.isEmpty()!=true&&fr.isEmpty()!=true&&i<n&&j<n){
				Alumno F1=fl.get(0);
				Alumno F2=fr.get(0);
				if(F1.getNombre().compareTo(F2.getNombre())>0){
					f.add(fr.remove(0));
					j++;
				}else{
					f.add(fl.remove(0));
					i++;
				}
			}else if((fl.isEmpty()==true)||(i>=n)){
				f.add(fr.remove(0));
				j++;
				}else{
					f.add(fl.remove(0));
					i++;
				}
		}
	}
	
	void imprimirDocumento(ArrayList<Alumno> f,FileWriter iteraciones){
		for(Alumno alu:f){
			try{
				iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
