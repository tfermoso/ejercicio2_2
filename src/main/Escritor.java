package main;

public class Escritor extends Thread{
	DatosBufferNoMonitor d;
	//DatosBuffer d;
	public Escritor(String nombre,DatosBuffer d){
		super(nombre);
		this.d=d;
	}
	public Escritor(String nombre,DatosBufferNoMonitor d){
		super(nombre);
		this.d=d;
	}
	
	public void run(){
	
			System.out.println("soy el escritor "+this.getName());			
			int i=0;
			while(true){
				
				d.escribir(i);
				i++;
			}
		}
}
