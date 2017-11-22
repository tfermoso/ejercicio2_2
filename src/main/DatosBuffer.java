package main;

public class DatosBuffer extends DatosBufferNoMonitor {
	static Integer[] buffer=new Integer[10000];
	static boolean listo=true;
	
	synchronized public int escribir(int i){
		while(listo==false){
			try {
				wait();
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
		listo=false;
		for(int j=0; j<buffer.length-1;j++){
			buffer[j]=i;
		}
		listo=true;
		notifyAll();
		return i;
		
	}
	
	synchronized public boolean leer(){
		boolean iguales=true;
		try {
			while(listo==false){
				wait();
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listo=false;
		int valorLeido=buffer[0];
		for(int i=0;i<buffer.length-1;i++){
			if(valorLeido!=buffer[i]){
				iguales=false;
			}
		}
		listo=true;
		notifyAll();		
		if(iguales){System.out.println("El lector leyó "+valorLeido);}
		else{
			System.out.println("Error al leer");
		}
		return iguales;
	}
}
