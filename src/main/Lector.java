package main;

public class Lector extends Thread {
	DatosBufferNoMonitor d;
	//DatosBuffer d;
	public Lector(String nombre,DatosBuffer d){
		super(nombre);
		this.d=d;
	}
	public Lector(String nombre,DatosBufferNoMonitor d){
		super(nombre);
		this.d=d;
	}
	
	public void run(){
			System.out.println("soy el lector "+this.getName());
			while(true){
				
				d.leer();
			}
		}
	
}
