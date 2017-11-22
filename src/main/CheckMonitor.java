package main;

public class CheckMonitor {
	static DatosBuffer datosBuffer=new DatosBuffer();
	//static DatosBufferNoMonitor datosBuffer=new DatosBufferNoMonitor();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Escritor("escritor",datosBuffer).start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<5;i++){
		new Lector("lector"+i,datosBuffer).start();
		}
	}

}
