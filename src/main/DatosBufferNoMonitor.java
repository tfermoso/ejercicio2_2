package main;

public class DatosBufferNoMonitor {

	static Integer[] buffer=new Integer[10000];
	static boolean listo=false;
	
	public int escribir(int i){
		
		for(int j=0; j<buffer.length-1;j++){
			buffer[j]=i;
		}
		System.out.println("Valor escrito "+i);
		return i;
	}
	
	public boolean leer(){
		boolean iguales=true;

		int valorLeido=buffer[0];
		for(int i=0;i<buffer.length-1;i++){
			if(valorLeido!=buffer[i]){
				iguales=false;
			}
		}
		if(iguales){
			System.out.println("Valor leido "+buffer[0]);
		}else{
			System.out.println("Error al leer");
		}
		return iguales;
		
	}
}

