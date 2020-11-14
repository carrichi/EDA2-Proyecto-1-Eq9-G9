
// Bibliotecas de apoyo
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polifase{
	// listadatos es la lista de los objetos tipo Alumno, con los atributos nombre, apellido y num de cuenta, que se leyeron del archivo original
	//nombre es el nombre del archivo original
	public void sort(ArrayList<Alumno> listadatos,String nombre){
		
		// Arreglo que almacena de forma temporal los bloques de n=7 
		// y los ordena con un Algoritmo de Ordenamiento Interno
		ArrayList<Alumno> AOI = new ArrayList<>(); 
		
		// Los archivos auxiliares seran listas
		ArrayList<Alumno> f0 = new ArrayList<>(); 
		ArrayList<Alumno> f1 = new ArrayList<>(); 
		ArrayList<Alumno> f2 = new ArrayList<>(); 
		ArrayList<Alumno> f3 = new ArrayList<>();
		Scanner scan=new Scanner(System.in);
		
		try{
			System.out.println("\nOrdenar por:\n1. Apellidos \n2. Nombre");
			System.out.print("> ");
			int opc=scan.nextInt();
			scan.nextLine();
			// en iteraciones se guardan las fases 1 y 2, y sus iteraciones
			FileWriter iteraciones;
			if(opc==1){
				iteraciones=new FileWriter("./iteracionesPolifase_Apellido_"+nombre+".txt");
				System.out.println("\n\t\t\t****FIN****\n\nLas iteraciones se encuentran en la carpeta actual, en el archivo: \n\titeracionesPolifase_Apellido_"+nombre+".txt");
			}else if(opc==2){
				iteraciones=new FileWriter("./iteracionesPolifase_Nombre_"+nombre+".txt");
				System.out.println("\n\t\t\t****FIN****\n\nLas iteraciones se encuentran en la carpeta actual, en el archivo: \n\titeracionesPolifase_Nombre_"+nombre+".txt");
				}else
					iteraciones=new FileWriter("./ERROR.txt");
			f0=listadatos;
			//  n es el tamaño de los bloques
			int n=7;
			iteraciones.write("***FASE 1***\n");
		  //******FASE 1*****
		  //leer archivo original "f0", y dividir en bloques de n=7, ordenar cada bloque y colocar uno a uno en f1 y f2
			while(f0.isEmpty()!=true){
				insertionSort(f0,AOI,n,opc);
				iteraciones.write("F1\n");
				// colocar bloque ordenado en lista f1
				for(Alumno alu:AOI){
					f1.add(alu);
					iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
				}
				AOI.clear();
				insertionSort(f0,AOI,n,opc);
				iteraciones.write("F2\n");
				for(Alumno alu:AOI){
					f2.add(alu);
					iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
				}
				AOI.clear();
			} //fin fase 1
			
			iteraciones.write("\nFase 1/F1\n\n");
			imprimirDocumento(f1,iteraciones);
	
			iteraciones.write("\nFase 1/F2\n\n");
			imprimirDocumento(f2,iteraciones);
			
		//*****FASE 2*******
			iteraciones.write("\n\n***FASE 2***\n");
			// vaciar f1 y f2 en f0 y f3, o al reves
			while((f1.isEmpty()!=true&&f2.isEmpty()!=true)||(f0.isEmpty()!=true&&f3.isEmpty()!=true)){
				iteraciones.write("\nF1-F2 --> F0-F3\n");
				// pasae a f0 y f3 claves de f1 y f2
				while(f1.isEmpty()!=true||f2.isEmpty()!=true){
					faseDos(f0,f1,f2,n,opc);
					iteraciones.write("\nF0\n");
					imprimirDocumento(f0,iteraciones);
					faseDos(f3,f1,f2,n,opc);
					iteraciones.write("\nF3\n");
					imprimirDocumento(f3,iteraciones);
				}
				n*=2; // aumenta el tamaño del bloque
				iteraciones.write("\nF0-F3 --> F1-F2\n");
				//pasar a f1 y f2 de f0 y f3
				while(f0.isEmpty()!=true||f3.isEmpty()!=true){
					faseDos(f1,f0,f3,n,opc);
					iteraciones.write("\nF1\n");
					imprimirDocumento(f1,iteraciones);
					faseDos(f2,f0,f3,n,opc);
					iteraciones.write("\nF2\n");
					imprimirDocumento(f2,iteraciones);
				}
				n*=2;
			}
		
			try{
				FileWriter archivoFinal;
				// crear archivo ordenado, por apellido
				if(opc==1){
					archivoFinal=new FileWriter("./archivoFinalPolifase_Apellido_"+nombre+".txt");
					System.out.println("\nTu archivo final (ordenado) está en:\n\tarchivoFinal_Apellido_"+nombre+".txt");
				// crear archivo ordenado, por nombre
				}else if(opc==2){
					archivoFinal=new FileWriter("./archivoFinalPolifase_Nombre_"+nombre+".txt");
					System.out.println("\nTu archivo final (ordenado) está en:\n\tarchivoFinal_Nombre_"+nombre+".txt");
					}else
						archivoFinal=new FileWriter("./ERROR.txt");
				archivoFinal.write("\t***Polifase***\n");
				// la lista completa puede quedar en f0 o f1
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
	
	// f lista por vaciar, AOI bloque a ordenar, n tamaño del bloque
	void insertionSort(ArrayList<Alumno> f,ArrayList<Alumno> AOI,int n,int opc){
		Alumno index;
		int k,i=0;
		while(f.isEmpty()!=true&&i<n){
			AOI.add(f.remove(0));
			index=AOI.get(i);
			k=i-1;
                        if(opc==1){
                            while(k>-1&&(AOI.get(k).getApellido().compareTo(index.getApellido()))>0){
				AOI.set(k+1,AOI.get(k));
				k--;
                            }
                        }else{
                            while(k>-1&&(AOI.get(k).getNombre().compareTo(index.getNombre()))>0){
				AOI.set(k+1,AOI.get(k));
				k--;
                            }
			}
			AOI.set(k+1,index);
			i++;
		}
	}	
	// f -> lista donde se colocarán los bloques, fl y fr listas con claves (n tamaño del bloque)
	void faseDos(ArrayList<Alumno> f,ArrayList<Alumno> fl,ArrayList<Alumno> fr,int n,int opc){
		// cantidades de veces que se añadieron claves a cada lista
		int i=0,j=0;
		// no añadir mas de n claves por lista           
		while((fl.isEmpty()!=true||fr.isEmpty()!=true)&&(i<n||j<n)){
			//cuando ambos tienen aun claves por ordenar y pasar
			if(fl.isEmpty()!=true&&fr.isEmpty()!=true&&i<n&&j<n){
				// "desencolar" el menor de los dos bloques
				Alumno Fl=fl.get(0);
				Alumno Fr=fr.get(0);
				// si >0 ent fr(0) va antes que fl(0), <0 fl(0) va antes
                                if(opc==1){
                                    if(Fl.getApellido().compareTo(Fr.getApellido())>0){
					f.add(fr.remove(0));
					j++;
                                    }else{
                                    	f.add(fl.remove(0));
                                    	i++;
                                    }
                                }else{
                                    if(Fl.getApellido().compareTo(Fr.getApellido())>0){
					f.add(fr.remove(0));
					j++;
                                    }else{
                                    	f.add(fl.remove(0));
                                    	i++;
                                    }
                                }
			// cuando alguna lista ya tiene sus n claves o esta vacia
			// la otra se vacia (ya sin comparar las claves con la otra lista)
			}else if((fl.isEmpty()==true)||(i>=n)){
				f.add(fr.remove(0));
				j++;
				}else{
					f.add(fl.remove(0));
					i++;
				}
		}
	}
	// escribe en el archivo (iteraciones o final) los datos (Alumno) de la lista deseada
	void imprimirDocumento(ArrayList<Alumno> f,FileWriter archivo){
		for(Alumno alu:f){
			try{
				archivo.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

