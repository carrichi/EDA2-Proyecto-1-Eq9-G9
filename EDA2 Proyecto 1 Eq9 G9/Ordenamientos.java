import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Ordenamientos{
	
	public void polifase(ArrayList<Alumno> listadatos){
		//1   Alumno[] AOI=new Alumno[7];
		ArrayList<Alumno> AOI = new ArrayList<Alumno>(); 
		
		ArrayList<Alumno> f0 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f1 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f2 = new ArrayList<Alumno>(); 
		ArrayList<Alumno> f3 = new ArrayList<Alumno>();
		
		try{
		FileWriter iteraciones=new FileWriter("./iteraciones.txt");

		f0=listadatos;
		int i=0,k,n=7;
		Alumno index;
		iteraciones.write("***Fase 1***\n");
		while(f0.isEmpty()!=true){
			i=0;
			while(f0.isEmpty()!=true&&i<n){
				//1   AOI[i]=f0.remove(0);
				AOI.add(f0.remove(0));
				//1   index=AOI[i]; //insertion sort
				index=AOI.get(i);
				k=i-1;
				//1   while(k>=0&&AOI[k].getNombre().compareTo(index.getNombre())>0){
				while(k>-1&&(AOI.get(k).getNombre().compareTo(index.getNombre()))>0){
					//1   AOI[k+1]=AOI[k];
					AOI.set(k+1,AOI.get(k));
					//System.out.println(AOI.get(k+1).getNombre()+","+AOI.get(k+1).getApellido()+","+AOI.get(k+1).getNoCuenta());
					k--;
				}
				//1   AOI[k+1]=index;
				AOI.set(k+1,index);
				i++;
			}
			iteraciones.write("F1\n");
			
			//1   for(int alu=0;alu<i;alu++){
			for(Alumno alu:AOI){
				//1  f1.add(AOI[alu]);
				f1.add(alu);
				//1  iteraciones.write(AOI[alu].getNombre()+", "+AOI[alu].getApellido()+", "+AOI[alu].getNoCuenta()+"\n");
				iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}
			//1   Arrays.fill(AOI,null);
			AOI.clear();
			i=0;
			while(f0.isEmpty()!=true&&i<n){
				//1 AOI[i]=f0.remove(0);
				AOI.add(f0.remove(0));
				//1 index=AOI[i];
				index=AOI.get(i);
				k=i-1;
				//1 while(k>=0&&AOI[k].getNombre().compareTo(index.getNombre())>0){
				// Si >0 entonces index va antes
				while(k>-1&&AOI.get(k).getNombre().compareTo(index.getNombre())>0){
					//1 AOI[k+1]=AOI[k];
					AOI.set(k+1,AOI.get(k));
					k=k-1;
				}
				//1 AOI[k+1]=index;
				AOI.set(k+1,index);
				i++;
			}
			iteraciones.write("F2\n");
			
			//for(int alu=0;alu<i;alu++){
			for(Alumno alu:AOI){
				//f2.add(AOI[alu]);
				f2.add(alu);
				//iteraciones.write(AOI[alu].getNombre()+", "+AOI[alu].getApellido()+", "+AOI[alu].getNoCuenta()+"\n");
				iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}
			//Arrays.fill(AOI,null);
			AOI.clear();
		}//fin fase 1
		iteraciones.write("\nFase 1/F1\n\n");
		for(Alumno alu:f1){
			iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
		}
		iteraciones.write("\nFase 1/F2\n\n");
		for(Alumno alu:f2){
			iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
		}
		
		iteraciones.write("\n\n***Fase 2***\n");
		
		while(f1.isEmpty()!=true||f2.isEmpty()!=true){
			i=0;
			while((f1.isEmpty()!=true||f2.isEmpty()!=true)&&i<(n*2)){
				if(f1.isEmpty()!=true&&f2.isEmpty()!=true){
					Alumno F1=f1.get(0);
					Alumno F2=f2.get(0);
					if(F1.getNombre().compareTo(F2.getNombre())>0){
						f0.add(f2.remove(0));
						
					}else{
						f0.add(f1.remove(0));
						
					}
				}else if(f1.isEmpty()==true){
					f0.add(f2.remove(0));
					
				}else{
					f0.add(f1.remove(0));
					
				}
				i++;
			}
			iteraciones.write("\nF0\n");
			for(Alumno alu:f0){
				iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}
			i=0;
			while(f1.isEmpty()!=true&&f2.isEmpty()!=true&&i<(n*2)){
				if(f1.isEmpty()!=true&&f2.isEmpty()!=true){
					Alumno F1=f1.get(0);
					Alumno F2=f2.get(0);
					if(F1.getNombre().compareTo(F2.getNombre())>0){
						f3.add(f2.remove(0));
						
					}else{
						f3.add(f1.remove(0));
						
					}
				}else if(f1.isEmpty()==true){
					f3.add(f2.remove(0));
					
				}else{
					f3.add(f1.remove(0));
					
				}
				i++;
			}
			iteraciones.write("\nF3\n");
			for(Alumno alu:f3){
				iteraciones.write(alu.getNombre()+", "+alu.getApellido()+", "+alu.getNoCuenta()+"\n");
			}
			
		}
		
		
		
		iteraciones.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	/*	System.out.println("F1");
		for(Alumno j:f1){
			System.out.println(j.getNombre());
			System.out.println(j.getApellido());
			System.out.println(j.getNoCuenta());
		}
		System.out.println("F2");
		for(Alumno j:f2){
			System.out.println(j.getNombre());
			System.out.println(j.getApellido());
			System.out.println(j.getNoCuenta());
		}*/
		
		
		

	
	/*	List<String> nombres=new LinkedList<>();
		List<String> f1=new LinkedList<>();
		List<String> f2=new LinkedList<>();
		List<String> f3=new LinkedList<>();
		System.out.println("polifase");
		for(Alumno i:listadatos){
			nombres.add(i.getNombre());
		}
	//  for(String i:nombres){
	//		System.out.println(i);
	//	}
		int n=7,i=0,k=0;
		String[] AOI=new String[7];
		while(nombres.isEmpty()!=true){
			
			while((nombres.isEmpty()!=true)&&(i<7)){
				//AOI[i]=nombres.get(0);
				AOI[i]=nombres.remove(0);
				index=AOI[i]
				int l=i-1;
				while(l>=0&&AOI[l]>index){
					AOI[l+1]=AOI[l];
					l=l-1;
				}
				AOI[l+1]=index;
				//f1.add(nombres.remove(0));
				i++;
			}
			
			for(String s:AOI)	
			
			
			i=0;
			while((nombres.isEmpty()!=true)&&(i<7)){
				f2.add(nombres.remove(0));
				i++;
			}
			i=0;
		}
		System.out.println("F1");
		for(String j:f1){
			System.out.println(j);
		}
		System.out.println("F2");
		for(String j:f2){
			System.out.println(j);
		}
		*/
	}
}
